package com.api.payload;

import java.util.Date;

public class ErrorDto {
    private String msg;
    private Date date;
    private String uri;

    public ErrorDto(Date date, String msg, String uri) {
        this.date = date;
        this.msg = msg;
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }

    public String getMsg() {
        return msg;
    }

    public Date getDate() {
        return date;
    }
}
