package com.augmentun.exam.exception;

import java.util.HashMap;
import java.util.Map;

public class ParamterException extends Exception {
    private static final long serialVersionUID = 1L;

    private Map<String, String> errorMessage = new HashMap<String, String>();

    public Map<String, String> getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(Map<String, String> errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void addErrorField (String field, String message) {
        errorMessage.put(field, message);
    }

    public boolean hasErrorMessage () {
        return !errorMessage.isEmpty();
    }

}
