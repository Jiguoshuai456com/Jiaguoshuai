package com.bw.movie.bean;

import java.util.List;


public class ComingSoonBean {


    private String message;
    private String status;
    private List<ComingSoonResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ComingSoonResultBean> getResult() {
        return result;
    }

    public void setResult(List<ComingSoonResultBean> result) {
        this.result = result;
    }

}
