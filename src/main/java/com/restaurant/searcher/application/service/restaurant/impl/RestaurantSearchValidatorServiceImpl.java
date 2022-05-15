package com.restaurant.searcher.application.service.restaurant.impl;

import com.restaurant.searcher.application.service.restaurant.RestaurantSearchValidatorService;
import com.restaurant.searcher.application.service.restaurant.util.IntervalValidator;
import com.restaurant.searcher.application.service.restaurant.util.TextValidator;
import com.restaurant.searcher.domain.constants.Constants;
import com.restaurant.searcher.domain.exceptions.badrequest.RequiredParameterException;
import com.restaurant.searcher.domain.model.vo.RestaurantSearchVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Slf4j
@AllArgsConstructor
public class RestaurantSearchValidatorServiceImpl implements RestaurantSearchValidatorService {

    public void validateRestaurantSearch(RestaurantSearchVO restaurantSearch){

        if(!Objects.nonNull(restaurantSearch)){
            log.error("Null searh");
            throw new RequiredParameterException();
        }
        var validResult =
        TextValidator.validate(restaurantSearch.getRestaurantName()) || 
        IntervalValidator.validate(restaurantSearch.getCustomerRating(), Constants.MIN_CUSTOMER_RATING, Constants.MAX_CUSTOMER_RATING) ||
        IntervalValidator.validate(restaurantSearch.getDistance().intValue(), Constants.MIN_DISTANCE, Constants.MAX_DISTANCE) ||
        IntervalValidator.validate(restaurantSearch.getPrice().intValue(), Constants.MIN_PRICE, Constants.MAX_PRICE) ||
        TextValidator.validate(restaurantSearch.getCuisineName());

        if(!validResult){
            log.error("At least one parameter should be provided");
            throw new RequiredParameterException();
        }
    }
}
