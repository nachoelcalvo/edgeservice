package com.personal.cloud;

import java.io.Serializable;

/**
 * Created by josgar on 07/06/2017.
 */
public class Greeting implements Serializable {

    private String message;

    public Greeting() {
    }

    public Greeting(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
