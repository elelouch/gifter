package com.gifter.app.auth.errors.dto;

import lombok.Data;

import java.util.Map;

@Data
public class GenericErrorDto {
    private Map<String, Object> reasons;
    private String status;
}
