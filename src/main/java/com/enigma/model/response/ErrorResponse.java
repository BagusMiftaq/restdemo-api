package com.enigma.model.response;

public class ErrorResponse extends CommonResponse {
    public ErrorResponse(String code, String message) {
        super.setCode(code);
        super.setStatus("ERROR");
        super.setMessage(message);
    }
}
