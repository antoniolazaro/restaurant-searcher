package com.restaurant.searcher.domain.exceptions.badrequest;

public final class OutsideIntervalException extends AbstractBadRequestException {

    public OutsideIntervalException(Integer min, Integer max, Integer value){
        super("The value %d is outside of valid interval (%d -> %d)".formatted(value, min, max));
    }
}
