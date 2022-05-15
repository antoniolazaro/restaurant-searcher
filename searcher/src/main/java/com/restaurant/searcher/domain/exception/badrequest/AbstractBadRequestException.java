package com.restaurant.searcher.domain.exception.badrequest;

public abstract class AbstractBadRequestException extends RuntimeException{

    public AbstractBadRequestException(String msg){
        super(msg);
    }
}
