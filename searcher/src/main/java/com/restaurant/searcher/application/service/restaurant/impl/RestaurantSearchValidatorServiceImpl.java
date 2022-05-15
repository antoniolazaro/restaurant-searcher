package com.restaurant.searcher.application.service.restaurant.impl;

import com.restaurant.searcher.application.service.restaurant.RestaurantSearchValidatorService;
import com.restaurant.searcher.domain.exceptions.badrequest.InvalidTextException;
import com.restaurant.searcher.domain.exceptions.badrequest.OutsideIntervalException;
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
            log.error("Null searh");
            throw new RequiredParameterException();
        }

        var atLeastOneParam = 0;
        var restaurantName = restaurantSearch.getRestaurantName();
        if (StringUtils.isNotBlank(restaurantName)) {
            if(restaurantName.length() < 2) {
                log.error("Restaurant name should has at least 2 character");
                throw new InvalidTextException(restaurantName);
            }
            atLeastOneParam += 1;
        }

        var customerRating = restaurantSearch.getCustomerRating();
        if (Objects.nonNull(customerRating)) {
            if (customerRating < 1 || customerRating > 5) {
                log.error("Customer rating should be between 1 and 5. Value informed: {} ",customerRating);
                throw new OutsideIntervalException(1, 5, customerRating);
            }
            atLeastOneParam += 1;
        }
        var distance = restaurantSearch.getDistance();
        if (Objects.nonNull(distance)) {
            if (distance < 1 || distance > 10) {
                log.error("Distance rating should be between 1 and 10. Value informed: {} ",distance);
                throw new OutsideIntervalException(1, 10, distance);
            }
            atLeastOneParam += 1;
        }
        var price = restaurantSearch.getPrice();
        if (Objects.nonNull(price)) {
            if (price < 10 || price > 50) {
                log.error("Distance rating should be between 10 and 50. Value informed: {} ",price);
                throw new OutsideIntervalException(10, 50, price);
            }
            atLeastOneParam += 1;
        }
        var cuisineName = restaurantSearch.getCuisineName();
        if (StringUtils.isNotBlank(cuisineName)) {
            if(cuisineName.length() < 2) {
                log.error("Cuisine name should has at least 2 character");
                throw new InvalidTextException(cuisineName);
            }
            atLeastOneParam += 1;
        }
        if(atLeastOneParam == 0){
            log.error("At least one parameter should be provided");
            throw new RequiredParameterException();
        }
    }
}
