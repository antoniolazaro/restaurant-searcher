package com.restaurant.searcher.application.service.restaurant.impl;

import com.restaurant.searcher.application.service.restaurant.RestaurantService;
import com.restaurant.searcher.domain.constants.Constants;
import com.restaurant.searcher.domain.model.vo.RestaurantSearchVO;
import com.restaurant.searcher.domain.model.vo.RestaurantVO;
import com.restaurant.searcher.application.service.dataloader.RestaurantDataLoaderService;
import com.restaurant.searcher.application.service.restaurant.RestaurantSearchValidatorService;
import com.restaurant.searcher.application.util.RestaurantPredicateUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
@Slf4j
@AllArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantDataLoaderService restaurantDataLoaderService;
    private final RestaurantSearchValidatorService restaurantSearchValidatorService;
    public List<RestaurantVO> search(RestaurantSearchVO restaurantSearch){
        log.debug("Validate data {} ", restaurantSearch);
        restaurantSearchValidatorService.validateRestaurantSearch(restaurantSearch);
        log.debug("data validated {} ", restaurantSearch);
        var restaurants = restaurantDataLoaderService.loadData();
        log.debug("restaurants loaded {} ", restaurants);
        if(!restaurants.isEmpty()){
            log.debug("restaurants has data {} ", restaurants);
            var predicate = RestaurantPredicateUtil.buildPredicateFilter(restaurantSearch);
            return restaurants
                    .stream()
                    .filter(predicate)
                    .sorted(buildOrdering())
                    .skip(0)
                    .limit(Constants.MAX_SEARCH_RESULT)
                    .toList();

        }
        return new ArrayList<>();
    }

    private Comparator<RestaurantVO> buildOrdering() {
        return Comparator
                .comparing(RestaurantVO::getDistance).reversed()
                .thenComparing(RestaurantVO::getCustomerRating)
                .thenComparing(RestaurantVO::getPrice).reversed()
                .thenComparing(RestaurantVO::getName);
    }

}
