package com.aivle12.book_backend.service;

import com.aivle12.book_backend.dto.BookCoverGenerateRequest;
import com.aivle12.book_backend.dto.BookCoverGenerateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
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

    public BookCoverGenerateResponse generateCovers(BookCoverGenerateRequest request) {
        String model = request.getModel();
        String quality = mapQuality(model, request.getQuality());
        String size = getSize(model);

        List<CompletableFuture<String>> futures = IntStream.range(0, IMAGE_COUNT)
                .mapToObj(i -> CompletableFuture.supplyAsync(
                        () -> callOpenAI(request.getPrompt(), model, quality, size)
                ))
                .collect(Collectors.toList());

        List<String> images = futures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());

        return new BookCoverGenerateResponse(images);
    }

    @SuppressWarnings("unchecked")
    private String callOpenAI(String prompt, String model, String quality, String size) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        Map<String, Object> body = Map.of(
                "model", model,
                "prompt", prompt,
                "n", 1,
                "size", size,
                "quality", quality,
                "response_format", "b64_json"
        );

        ResponseEntity<Map> response = restTemplate.exchange(
                OPENAI_URL,
                HttpMethod.POST,
                new HttpEntity<>(body, headers),
                Map.class
        );

        List<Map<String, Object>> data = (List<Map<String, Object>>) response.getBody().get("data");
        String b64 = (String) data.get(0).get("b64_json");
        return "data:image/png;base64," + b64;
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
