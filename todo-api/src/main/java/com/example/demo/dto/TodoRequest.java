package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;

public record TodoRequest(
    @NotBlank(message = "O título é obrigatório") 
    String title, 
    String description, 
    boolean completed
) {}