package com.br.springtesteautomatizado.exceptionHandlerController;

import java.util.Date;

public class ExceptionResponse {
    private Date timestamp;
    private String message;
    private String path;
    private String error;
    private Integer status;

    public ExceptionResponse() {
    }

    public ExceptionResponse(Date timestamp, String message, String details, String error, Integer status) {
        super();
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.path = details;
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }

    public Integer getStatus() {
        return status;
    }
}
