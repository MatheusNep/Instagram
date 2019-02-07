package com.parse.starter.useful;

import java.util.HashMap;

public class ParseError {
    private HashMap<Integer, String> error;

    public ParseError() {
        this.error = new HashMap<>();
        this.error.put(202, "User already exists, choose another username");
        this.error.put(201, "the password is missing or empty");
    }
    public String getError(int codError){
        return this.error.get(codError);
    }
}