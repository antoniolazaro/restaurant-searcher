package com.restaurant.searcher.domain.exception.badrequest;

public final class InvalidCustomerRatingException extends AbstractBadRequestException {

    public InvalidCustomerRatingException(){
        super("Customer rating should be a number beteween 1 and 5.");
    }
}
