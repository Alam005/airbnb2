package com.airbnb.airbin2.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class TokenResponseDto {
    private String type;
    private String token;
}
