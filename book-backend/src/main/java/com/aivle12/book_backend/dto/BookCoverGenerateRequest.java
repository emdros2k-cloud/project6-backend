package com.aivle12.book_backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookCoverGenerateRequest {

    @NotBlank
    private String model;       // gpt-image-2, gpt-image-1, dall-e-3

    private String quality;     // Low, Medium, High
    private String prompt;      // 추가 설명 (optional)
    private String title;
    private String genre;
    private String author;
    private String content;
    private String style;       // 수채화, 3D애니메이션, 유화, 미니멀리즘, 빈티지, 일러스트
    private String background;  // 베이지, 사이버틱, 화이트, 레트로, 자연, 추상
    private String lighting;    // 자연광, 대비광, 따뜻한광
    private String typography;  // 클래식 명조, 모던 고딕, 감성 손글씨
}
