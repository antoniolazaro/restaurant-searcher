package com.restaurant.searcher.domain.exceptions.internalserver;

public abstract class AbstractInternalServerErrorException extends RuntimeException{

    public AbstractInternalServerErrorException(String msg){
        super(msg);
    }
}
