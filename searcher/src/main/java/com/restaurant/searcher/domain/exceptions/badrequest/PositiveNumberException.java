package com.restaurant.searcher.domain.exceptions.badrequest;

public final class PositiveNumberException extends AbstractBadRequestException {

    public PositiveNumberException(Integer value){
        super("The value %d should be positive".formatted(value));
    }
}
