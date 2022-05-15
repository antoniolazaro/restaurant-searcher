package com.restaurant.searcher.domain.exceptions.internalserver;

public class GenericServerErrorException extends AbstractInternalServerErrorException{

    public GenericServerErrorException(String msg){
        super(msg);
    }
}
