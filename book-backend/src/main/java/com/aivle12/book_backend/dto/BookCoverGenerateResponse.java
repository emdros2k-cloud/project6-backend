package com.aivle12.book_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class BookCoverGenerateResponse {

    private List<String> images; // base64 data URL 3장
}
