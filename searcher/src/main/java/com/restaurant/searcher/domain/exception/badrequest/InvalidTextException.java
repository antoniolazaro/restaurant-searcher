package com.restaurant.searcher.domain.exception.badrequest;

public final class InvalidTextException extends AbstractBadRequestException {

    public InvalidTextException(String value){
        super("The value %s should have size greather than 1.".formatted(value));
    }
}
