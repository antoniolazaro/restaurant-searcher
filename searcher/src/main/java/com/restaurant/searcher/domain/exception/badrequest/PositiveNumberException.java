package com.restaurant.searcher.domain.exception.badrequest;

public final class PositiveNumberException extends AbstractBadRequestException {

    public PositiveNumberException(Integer value){
        super("The value %d should be positive".formatted(value));
    }
}
