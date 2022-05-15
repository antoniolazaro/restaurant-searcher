package com.restaurant.searcher.domain.exceptions.badrequest;

public abstract class AbstractBadRequestException extends RuntimeException{

    public AbstractBadRequestException(String msg){
        super(msg);
    }
}
