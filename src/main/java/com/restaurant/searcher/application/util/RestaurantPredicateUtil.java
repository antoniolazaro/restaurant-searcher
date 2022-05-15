package com.restaurant.searcher.application.util;

import com.restaurant.searcher.domain.model.vo.RestaurantSearchVO;
import com.restaurant.searcher.domain.model.vo.RestaurantVO;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;
import java.util.function.Predicate;

public final class RestaurantPredicateUtil {

    private static final Predicate<RestaurantVO> PREDICATE_BASE = (restaurantVO -> true);

    private RestaurantPredicateUtil() {
    }

    public static Predicate<RestaurantVO> buildPredicateFilter(RestaurantSearchVO restaurantSearch) {

        if(restaurantSearch != null) {
            return PREDICATE_BASE
                    .and(buildRestaurantPredicateName(restaurantSearch))
                    .and(buildPredicateCustomerRating(restaurantSearch))
                    .and(buildPredicateDistance(restaurantSearch))
                    .and(buildPredicatePrice(restaurantSearch))
                    .and(buildCuisinePredicateName(restaurantSearch));
        }
        return PREDICATE_BASE;

    }

    private static Predicate<RestaurantVO> buildRestaurantPredicateName(RestaurantSearchVO restaurantSearch) {
        if (StringUtils.isNotBlank(restaurantSearch.getRestaurantName())) {
            return (restaurantVO ->
                    compareText(restaurantVO.getName(), restaurantSearch.getRestaurantName().toUpperCase()));
        }
        return PREDICATE_BASE;
    }

    private static boolean compareText(String source, String target){
        return source
                .trim()
                .toUpperCase()
                .contains(target.toUpperCase().trim());
    }

    private static Predicate<RestaurantVO> buildPredicateCustomerRating(RestaurantSearchVO restaurantSearch) {
        if (Objects.nonNull(restaurantSearch.getCustomerRating())) {
            return (restaurantVO -> restaurantVO.getCustomerRating() >= restaurantSearch.getCustomerRating());
        }
        return PREDICATE_BASE;
    }

    private static Predicate<RestaurantVO> buildPredicateDistance(RestaurantSearchVO restaurantSearch) {
        if (Objects.nonNull(restaurantSearch.getDistance())) {
            return (restaurantVO -> restaurantVO.getDistance() <= restaurantSearch.getDistance());
        }
        return PREDICATE_BASE;
    }

    private static Predicate<RestaurantVO> buildPredicatePrice(RestaurantSearchVO restaurantSearch) {
        if (Objects.nonNull(restaurantSearch.getPrice())) {
            return (restaurantVO -> restaurantVO.getPrice() <= restaurantSearch.getPrice());
        }
        return PREDICATE_BASE;
    }

    private static Predicate<RestaurantVO> buildCuisinePredicateName(RestaurantSearchVO restaurantSearch) {
        if (StringUtils.isNotBlank(restaurantSearch.getCuisineName())) {
            return (restaurantVO ->
                    compareText(restaurantVO.getCuisine().getName(),
                    restaurantSearch.getCuisineName().toUpperCase()));

        }
        return PREDICATE_BASE;
    }
}
