package com.albatros.simspriser.session;

public class RegisterResponse {

    private long code;

    public RegisterResponse(long code) {
        this.code = code;
    }

    public RegisterResponse() {}

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }
}