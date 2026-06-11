package com.aivle12.book_backend.service;

import com.aivle12.book_backend.domain.Book;
import com.aivle12.book_backend.dto.BookCoverGenerateRequest;
import com.aivle12.book_backend.dto.BookCoverGenerateResponse;
import com.aivle12.book_backend.exception.BookNotFoundException;
import com.aivle12.book_backend.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class AiCoverService {

    private static final String OPENAI_URL = "https://api.openai.com/v1/images/generations";
    private static final int IMAGE_COUNT = 3;

    @Value("${openai.api-key}")
    private String apiKey;

    private final RestTemplate restTemplate;
    private final BookRepository bookRepository;

    public BookCoverGenerateResponse generateCovers(BookCoverGenerateRequest request) {
        String model = request.getModel();
        String quality = mapQuality(model, request.getQuality());
        String size = getSize(model);
        String prompt = buildPrompt(request);

        List<CompletableFuture<String>> futures = IntStream.range(0, IMAGE_COUNT)
                .mapToObj(i -> CompletableFuture.supplyAsync(
                        () -> callOpenAI(prompt, model, quality, size)
                ))
                .collect(Collectors.toList());

        return new BookCoverGenerateResponse(collectImages(futures));
    }

    @SuppressWarnings("unchecked")
    private String callOpenAI(String prompt, String model, String quality, String size) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        Map<String, Object> body = new HashMap<>();
        body.put("model", model);
        body.put("prompt", prompt);
        body.put("n", 1);
        body.put("size", size);
        body.put("quality", quality);
        if ("dall-e-3".equals(model)) {
            body.put("response_format", "b64_json");
        } else {
            body.put("output_format", "png");
        }

        try {
            ResponseEntity<Map> response = restTemplate.exchange(
                    OPENAI_URL,
                    HttpMethod.POST,
                    new HttpEntity<>(body, headers),
                    Map.class
            );

            if (response.getBody() == null) {
                throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "OpenAI 응답이 비어 있습니다.");
            }
            List<Map<String, Object>> data = (List<Map<String, Object>>) response.getBody().get("data");
            if (data == null || data.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "OpenAI 응답이 올바르지 않습니다.");
            }
            String b64 = (String) data.get(0).get("b64_json");
            if (b64 == null) {
                throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "OpenAI 응답에서 이미지를 찾을 수 없습니다.");
            }
            return "data:image/png;base64," + b64;
        } catch (RestClientException e) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "이미지 생성 중 오류가 발생했습니다.");
        }
    }

    private List<String> collectImages(List<CompletableFuture<String>> futures) {
        return futures.stream()
                .map(f -> {
                    try {
                        return f.join();
                    } catch (CompletionException e) {
                        Throwable cause = e.getCause();
                        if (cause instanceof ResponseStatusException rse) {
                            throw rse;
                        }
                        throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "이미지 생성 중 오류가 발생했습니다.");
                    }
                })
                .collect(Collectors.toList());
    }

    public BookCoverGenerateResponse generateCoversForBook(Long bookId, BookCoverGenerateRequest request) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));

        String model = request.getModel();
        String quality = mapQuality(model, request.getQuality());
        String size = getSize(model);
        String prompt = buildPromptFromBook(book, request);

        List<CompletableFuture<String>> futures = IntStream.range(0, IMAGE_COUNT)
                .mapToObj(i -> CompletableFuture.supplyAsync(
                        () -> callOpenAI(prompt, model, quality, size)
                ))
                .collect(Collectors.toList());

        return new BookCoverGenerateResponse(collectImages(futures));
    }

    private String buildPrompt(BookCoverGenerateRequest request) {
        StringBuilder sb = new StringBuilder();
        if (request.getTitle() != null && !request.getTitle().isBlank())
            sb.append(request.getTitle());
        if (request.getGenre() != null && !request.getGenre().isBlank())
            sb.append(", ").append(request.getGenre()).append(" genre");
        if (request.getAuthor() != null && !request.getAuthor().isBlank())
            sb.append(", written by ").append(request.getAuthor());
        if (request.getContent() != null && !request.getContent().isBlank())
            sb.append(", ").append(request.getContent());
        if (request.getPrompt() != null && !request.getPrompt().isBlank())
            sb.append(", ").append(request.getPrompt());
        appendOptions(sb, request);
        sb.append(", book cover");
        return sb.toString();
    }

    private String buildPromptFromBook(Book book, BookCoverGenerateRequest request) {
        StringBuilder sb = new StringBuilder();
        if (book.getTitle() != null) sb.append(book.getTitle());
        if (book.getGenre() != null) sb.append(", ").append(book.getGenre()).append(" genre");
        if (book.getAuthor() != null) sb.append(", written by ").append(book.getAuthor());
        if (book.getContent() != null && !book.getContent().isBlank())
            sb.append(", ").append(book.getContent());
        if (request.getPrompt() != null && !request.getPrompt().isBlank())
            sb.append(", ").append(request.getPrompt());
        appendOptions(sb, request);
        sb.append(", book cover");
        return sb.toString();
    }

    private void appendOptions(StringBuilder sb, BookCoverGenerateRequest request) {
        if (request.getStyle() != null && !request.getStyle().isBlank())
            sb.append(", ").append(request.getStyle()).append(" style");
        if (request.getBackground() != null && !request.getBackground().isBlank())
            sb.append(", ").append(request.getBackground()).append(" background");
        if (request.getLighting() != null && !request.getLighting().isBlank())
            sb.append(", ").append(request.getLighting());
        if (request.getTypography() != null && !request.getTypography().isBlank())
            sb.append(", ").append(request.getTypography()).append(" typography");
    }

    private String mapQuality(String model, String quality) {
        if ("dall-e-3".equals(model)) {
            return "High".equalsIgnoreCase(quality) ? "hd" : "standard";
        }
        return quality == null ? "medium" : quality.toLowerCase();
    }

    private String getSize(String model) {
        // dall-e-3는 1024x1536 미지원
        if ("dall-e-3".equals(model)) {
            return "1024x1792";
        }
        return "1024x1536";
    }
}
