package com.restaurant.searcher.application.service.restaurant;

import com.restaurant.searcher.domain.model.vo.RestaurantSearchVO;

public interface RestaurantSearchValidatorService {

    void validateRestaurantSearch(RestaurantSearchVO restaurantSearch);
}
