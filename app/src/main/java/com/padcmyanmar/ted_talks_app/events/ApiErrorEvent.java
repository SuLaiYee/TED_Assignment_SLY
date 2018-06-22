package com.padcmyanmar.ted_talks_app.events;

public class ApiErrorEvent {
    private String errorMsg;

    public ApiErrorEvent(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
