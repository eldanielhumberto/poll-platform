package com.poolplatform.features.option.adapters.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UpdateOptionDto {
    @NotNull
    @NotBlank
    private String optionText;

    public String getOptionText() {
        return optionText;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }

}
