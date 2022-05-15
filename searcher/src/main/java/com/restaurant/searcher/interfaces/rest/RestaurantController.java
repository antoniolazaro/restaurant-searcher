package com.restaurant.searcher.interfaces.rest;

import com.restaurant.searcher.domain.model.rest.response.RestaurantRestResponse;
import com.restaurant.searcher.domain.model.transform.RestaurantTransform;
import com.restaurant.searcher.domain.model.vo.RestaurantSearchVO;
import com.restaurant.searcher.application.service.restaurant.RestaurantService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final RestaurantTransform restaurantTransform;

    @GetMapping
    public List<RestaurantRestResponse> searchRestaurant(@RequestParam(required = false) String restaurantName,
                                                         @RequestParam(required = false) Integer customerRating,
                                                         @RequestParam(required = false) Integer distance,
                                                         @RequestParam(required = false) Integer price,
                                                         @RequestParam(required = false) String cuisineName) {
        var restaurantSearch = RestaurantSearchVO
                .builder()
                .restaurantName(restaurantName)
                .customerRating(customerRating)
                .distance(distance)
                .price(price)
                .cuisineName(cuisineName)
                .build();
        log.debug("Restaurant search {} ", restaurantSearch);
        var list = restaurantService.search(restaurantSearch);
        log.debug("Restaurant search return {} ", list);
        return restaurantTransform.toListRestaurantRestResponse(list);

    }

}
