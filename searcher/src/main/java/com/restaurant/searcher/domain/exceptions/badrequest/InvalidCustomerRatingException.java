package com.restaurant.searcher.domain.exceptions.badrequest;

public final class InvalidCustomerRatingException extends AbstractBadRequestException {

    public InvalidCustomerRatingException(){
        super("Customer rating should be a number beteween 1 and 5.");
    }
}
