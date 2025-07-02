package com.poolplatform.features.answer.domain.models;

public class SimpleOption {
    private String id;
    private String optionText;

    public SimpleOption() {
    }

    public SimpleOption(String id, String optionText) {
        this.id = id;
        this.optionText = optionText;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOptionText() {
        return optionText;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }

}
