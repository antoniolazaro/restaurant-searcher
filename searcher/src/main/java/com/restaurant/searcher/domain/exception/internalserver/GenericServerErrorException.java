package com.restaurant.searcher.domain.exception.internalserver;

public class GenericServerErrorException extends AbstractInternalServerErrorException{

    public GenericServerErrorException(String msg){
        super(msg);
    }
}
