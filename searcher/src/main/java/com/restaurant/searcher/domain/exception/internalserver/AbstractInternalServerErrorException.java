package com.restaurant.searcher.domain.exception.internalserver;

public abstract class AbstractInternalServerErrorException extends RuntimeException{

    public AbstractInternalServerErrorException(String msg){
        super(msg);
    }
}
