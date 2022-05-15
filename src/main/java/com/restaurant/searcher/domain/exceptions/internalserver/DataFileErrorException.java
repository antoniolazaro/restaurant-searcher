package com.restaurant.searcher.domain.exceptions.internalserver;

public class DataFileErrorException extends AbstractInternalServerErrorException{

    public DataFileErrorException(String value){
        super("Problem reading the data source %s ".formatted(value));
    }
}
