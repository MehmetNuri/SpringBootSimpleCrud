package com.mehmetnuri.simplecrud.exception;

public class CategoryAlreadyExistsException extends RuntimeException{
    public CategoryAlreadyExistsException(final String message) {
        super(message);
    }
}
