package com.rroca.encryptap.utils;

/**
 * Api Response default
 */
public class ApiResponse {
    private int status;
    private String message;
    private Object data;

    /**
     * @param status  the status
     * @param message the message
     * @param data    the data
     */
    public ApiResponse(int status, String message, Object data) {
        super();
        this.status = status;
        this.message = message;
        this.data = data;
    }

    /**
     * @return int
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return string
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return Object
     */
    public Object getData() {
        return data;
    }

    /**
     * @param data the data
     */
    public void setData(Object data) {
        this.data = data;
    }
}
