package com.restaurant.searcher.domain.exception.badrequest;

public final class RequiredParameterException extends AbstractBadRequestException {

    public RequiredParameterException(){
        super("At least one parameter should be used to search.");
    }
}
