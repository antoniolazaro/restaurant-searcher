package com.restaurant.searcher.application.service.restaurant;

import com.restaurant.searcher.domain.model.vo.RestaurantSearchVO;
import com.restaurant.searcher.domain.model.vo.RestaurantVO;

import java.util.List;

public interface RestaurantService {

    List<RestaurantVO> search(RestaurantSearchVO restaurantSearch);
}
