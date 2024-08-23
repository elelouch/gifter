package com.gifter.app.user.errors.dto;

import lombok.Data;

import java.util.Map;

@Data
public class GenericErrorDto {
    private Map<String, Object> reasons;
    private String status;
}
