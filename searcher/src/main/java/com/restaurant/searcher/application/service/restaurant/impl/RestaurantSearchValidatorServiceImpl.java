package com.restaurant.searcher.application.service.restaurant.impl;

import com.restaurant.searcher.application.service.restaurant.RestaurantSearchValidatorService;
import com.restaurant.searcher.domain.exceptions.badrequest.InvalidCustomerRatingException;
import com.restaurant.searcher.domain.exceptions.badrequest.InvalidTextException;
import com.restaurant.searcher.domain.exceptions.badrequest.PositiveNumberException;
import com.restaurant.searcher.domain.exceptions.badrequest.RequiredParameterException;
import com.restaurant.searcher.domain.model.vo.RestaurantSearchVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Slf4j
@AllArgsConstructor
public class RestaurantSearchValidatorServiceImpl implements RestaurantSearchValidatorService {

    public void validateRestaurantSearch(RestaurantSearchVO restaurantSearch){

        if(!Objects.nonNull(restaurantSearch)){
            throw new RequiredParameterException();
        }

        var atLeastOneParam = 0;
        if (StringUtils.isNotBlank(restaurantSearch.getRestaurantName())) {
            if(restaurantSearch.getRestaurantName().length() < 2) {
                throw new InvalidTextException(restaurantSearch.getRestaurantName());
            }
            atLeastOneParam += 1;
        }

        var customerRating = restaurantSearch.getCustomerRating();
        if (Objects.nonNull(customerRating)) {
            if (customerRating.intValue() < 1 || customerRating.intValue() > 5) {
                throw new InvalidCustomerRatingException();
            }
            atLeastOneParam += 1;
        }
        var distance = restaurantSearch.getDistance();
        if (Objects.nonNull(distance)) {
            if (distance < 1 || distance > 10) {
                throw new PositiveNumberException(restaurantSearch.getDistance());
            }
            atLeastOneParam += 1;
        }
        var price = restaurantSearch.getPrice();
        if (Objects.nonNull(price)) {
            if (price < 10 || price > 50) {
                throw new PositiveNumberException(restaurantSearch.getPrice());
            }
            atLeastOneParam += 1;
        }
        if (StringUtils.isNotBlank(restaurantSearch.getCuisineName())) {
            if(restaurantSearch.getCuisineName().length() < 2) {
                throw new InvalidTextException(restaurantSearch.getCuisineName());
            }
            atLeastOneParam += 1;
        }
        if(atLeastOneParam == 0){
            throw new RequiredParameterException();
        }
    }
}
