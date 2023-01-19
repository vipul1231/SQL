package com.example.perf.client.exception;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorDetail {

    private String code;

    private String message;
}
