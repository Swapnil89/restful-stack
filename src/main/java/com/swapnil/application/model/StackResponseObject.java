package com.swapnil.application.model;

import java.util.Calendar;
import java.util.Date;

public class StackResponseObject<E> {
    private static final String R_MSG_EMPTY = "";
    private static final String R_CODE_OK = "OK";

    private final String responseCode;
    private final Date execDt;
    private final String message;
    private E data;

    public StackResponseObject(final String code, final String message, final Date execDt, E data) {
        this.execDt = execDt;
        this.message = message;
        this.responseCode = code;
        this.data = data;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public Date getExecDt() {
        return execDt;
    }

    public String getMessage() {
        return message;
    }

    public E getData() {
        return data;
    }

    public static class Builder<E>{
        private String responseCode;
        private Date execDt;
        private String message;
        private E data;

        public Builder setResponseCode(String responseCode){
            this.responseCode = responseCode;
            return this;
        }

        public Builder setExecDt(Date execDt){
            this.execDt = execDt;
            return this;
        }

        public Builder setMessage(String message){
            this.message = message;
            return this;
        }

        public Builder setData(E data){
            this.data = data;
            return this;
        }

        public StackResponseObject build(){
            return new StackResponseObject(
                    responseCode==null? R_CODE_OK: this.responseCode,
                    message==null?R_MSG_EMPTY: this.message,
                    execDt==null?Calendar.getInstance().getTime():this.execDt,
                    data ==null?R_MSG_EMPTY: data.toString()
            );
        }
    }
}
