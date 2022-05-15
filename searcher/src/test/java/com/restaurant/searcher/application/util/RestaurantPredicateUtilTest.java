package com.restaurant.searcher.application.util;

import com.restaurant.searcher.domain.model.vo.CuisineVO;
import com.restaurant.searcher.domain.model.vo.RestaurantSearchVO;
import com.restaurant.searcher.domain.model.vo.RestaurantVO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public final class RestaurantPredicateUtilTest {

    @Test
    void testRestaurantSearchNull() {

        var predicate = RestaurantPredicateUtil.buildPredicateFilter(null);
        Assertions.assertNotNull(predicate, "not null");
        Assertions.assertTrue(predicate.test(RestaurantVO.builder().build()), "valid condition");
    }

    @Test
    void testRestaurantSearchEmptyFields() {

        var predicate = RestaurantPredicateUtil
                .buildPredicateFilter(RestaurantSearchVO.builder().build());
        Assertions.assertNotNull(predicate, "not null");
        Assertions.assertTrue(predicate.test(RestaurantVO.builder().build()), "valid condition");
    }

    @Test
    void testRestaurantNamePredicate() {

        var restaurantSearch = RestaurantSearchVO
                .builder()
                .restaurantName("123")
                .build();
        var predicate = RestaurantPredicateUtil.buildPredicateFilter(restaurantSearch);
        Assertions.assertNotNull(predicate, "not null");
        Assertions.assertTrue(predicate.test(RestaurantVO
                .builder()
                .name("123")
                .build()), "valid condition");
        Assertions.assertTrue(predicate.test(RestaurantVO
                .builder()
                .name("Restaurant 123")
                .build()), "valid condition");
        Assertions.assertTrue(predicate.test(RestaurantVO
                .builder()
                .name("Restaurant 123 Restaurant")
                .build()), "valid condition");
        Assertions.assertTrue(predicate.test(RestaurantVO
                .builder()
                .name("123 Restaurant")
                .build()), "valid condition");
        Assertions.assertFalse(predicate.test(RestaurantVO
                .builder()
                .name("12 Restaurant")
                .build()), "valid condition");
    }

    @Test
    void testCustomerRatingPredicate() {

        var restaurantSearch = RestaurantSearchVO
                .builder()
                .customerRating(3)
                .build();
        var predicate = RestaurantPredicateUtil.buildPredicateFilter(restaurantSearch);
        Assertions.assertNotNull(predicate, "not null");
        Assertions.assertTrue(predicate.test(RestaurantVO
                .builder()
                .customerRating(3)
                .build()), "valid condition");
        Assertions.assertTrue(predicate.test(RestaurantVO
                .builder()
                .customerRating(4)
                .build()), "valid condition");
        Assertions.assertTrue(predicate.test(RestaurantVO
                .builder()
                .customerRating(5)
                .build()), "valid condition");
        Assertions.assertFalse(predicate.test(RestaurantVO
                .builder()
                .customerRating(1)
                .build()), "valid condition");
        Assertions.assertFalse(predicate.test(RestaurantVO
                .builder()
                .customerRating(2)
                .build()), "valid condition");
    }

    @Test
    void testDistancePredicate() {

        var restaurantSearch = RestaurantSearchVO
                .builder()
                .distance(10)
                .build();
        var predicate = RestaurantPredicateUtil.buildPredicateFilter(restaurantSearch);
        Assertions.assertNotNull(predicate, "not null");
        Assertions.assertTrue(predicate.test(RestaurantVO
                .builder()
                .distance(10)
                .build()), "valid condition");
        Assertions.assertTrue(predicate.test(RestaurantVO
                .builder()
                .distance(1)
                .build()), "valid condition");
        Assertions.assertTrue(predicate.test(RestaurantVO
                .builder()
                .distance(3)
                .build()), "valid condition");
        Assertions.assertFalse(predicate.test(RestaurantVO
                .builder()
                .distance(11)
                .build()), "valid condition");
        Assertions.assertFalse(predicate.test(RestaurantVO
                .builder()
                .distance(100)
                .build()), "valid condition");
    }

    @Test
    void testPricePredicate() {

        var restaurantSearch = RestaurantSearchVO
                .builder()
                .price(10)
                .build();
        var predicate = RestaurantPredicateUtil.buildPredicateFilter(restaurantSearch);
        Assertions.assertNotNull(predicate, "not null");
        Assertions.assertTrue(predicate.test(RestaurantVO
                .builder()
                .price(10)
                .build()), "valid condition");
        Assertions.assertTrue(predicate.test(RestaurantVO
                .builder()
                .price(1)
                .build()), "valid condition");
        Assertions.assertTrue(predicate.test(RestaurantVO
                .builder()
                .price(3)
                .build()), "valid condition");
        Assertions.assertFalse(predicate.test(RestaurantVO
                .builder()
                .price(11)
                .build()), "valid condition");
        Assertions.assertFalse(predicate.test(RestaurantVO
                .builder()
                .price(100)
                .build()), "valid condition");
    }

    @Test
    void testCuisineNamePredicate() {

        var restaurantSearch = RestaurantSearchVO
                .builder()
                .cuisineName("123")
                .build();
        var predicate = RestaurantPredicateUtil.buildPredicateFilter(restaurantSearch);
        Assertions.assertNotNull(predicate, "not null");
        Assertions.assertTrue(predicate.test(RestaurantVO
                .builder()
                .cuisine(CuisineVO
                        .builder()
                        .name("123")
                        .build())
                .build()), "valid condition");
        Assertions.assertTrue(predicate.test(RestaurantVO
                .builder()
                .cuisine(CuisineVO
                        .builder()
                        .name("Restaurant 123")
                        .build())
                .build()), "valid condition");
        Assertions.assertTrue(predicate.test(RestaurantVO
                .builder()
                .cuisine(CuisineVO
                        .builder()
                        .name("Restaurant 123 Restaurant")
                        .build())
                .build()), "valid condition");
        Assertions.assertTrue(predicate.test(RestaurantVO
                .builder()
                .cuisine(CuisineVO
                        .builder()
                        .name("123 Restaurant")
                        .build())
                .build()), "valid condition");
        Assertions.assertFalse(predicate.test(RestaurantVO
                .builder()
                .cuisine(CuisineVO
                        .builder()
                        .name("12 Restaurant")
                        .build())
                .build()), "valid condition");
    }

    @Test
    void testRestaurantNameAndCustomerRatingPredicate() {

        var restaurantSearch = RestaurantSearchVO
                .builder()
                .customerRating(3)
                .restaurantName("123")
                .build();
        var predicate = RestaurantPredicateUtil.buildPredicateFilter(restaurantSearch);
        Assertions.assertNotNull(predicate, "not null");
        Assertions.assertTrue(predicate.test(RestaurantVO
                .builder()
                .customerRating(3)
                .name("123")
                .build()), "valid condition");
        Assertions.assertTrue(predicate.test(RestaurantVO
                .builder()
                .customerRating(4)
                .name("Restaurant 123")
                .build()), "valid condition");
        Assertions.assertFalse(predicate.test(RestaurantVO
                .builder()
                .customerRating(2)
                .name("Restaurant 123 Restaurant")
                .build()), "valid condition");
        Assertions.assertTrue(predicate.test(RestaurantVO
                .builder()
                .customerRating(3)
                .name("123 Restaurant")
                .build()), "valid condition");
        Assertions.assertFalse(predicate.test(RestaurantVO
                .builder()
                .customerRating(5)
                .name("12 Restaurant")
                .build()), "valid condition");
    }

    @Test
    void testRestaurantNameAndCustomerRatingAndDistancePredicate() {

        var restaurantSearch = RestaurantSearchVO
                .builder()
                .customerRating(3)
                .distance(10)
                .restaurantName("123")
                .build();
        var predicate = RestaurantPredicateUtil.buildPredicateFilter(restaurantSearch);
        Assertions.assertNotNull(predicate, "not null");
        Assertions.assertTrue(predicate.test(RestaurantVO
                .builder()
                .customerRating(3)
                .distance(10)
                .name("123")
                .build()), "valid condition");
        Assertions.assertTrue(predicate.test(RestaurantVO
                .builder()
                .customerRating(4)
                .distance(9)
                .name("Restaurant 123")
                .build()), "valid condition");
        Assertions.assertFalse(predicate.test(RestaurantVO
                .builder()
                .customerRating(3)
                .distance(20)
                .name("Restaurant 123 Restaurant")
                .build()), "valid condition");
        Assertions.assertTrue(predicate.test(RestaurantVO
                .builder()
                .customerRating(3)
                .distance(1)
                .name("123 Restaurant")
                .build()), "valid condition");
        Assertions.assertFalse(predicate.test(RestaurantVO
                .builder()
                .customerRating(5)
                .name("12 Restaurant")
                .build()), "valid condition");
    }

    @Test
    void testRestaurantNameAndCustomerRatingAndDistanceAndPricePredicate() {

        var restaurantSearch = RestaurantSearchVO
                .builder()
                .customerRating(3)
                .distance(10)
                .price(20)
                .restaurantName("123")
                .build();
        var predicate = RestaurantPredicateUtil.buildPredicateFilter(restaurantSearch);
        Assertions.assertNotNull(predicate, "not null");
        Assertions.assertTrue(predicate.test(RestaurantVO
                .builder()
                .customerRating(3)
                .distance(10)
                .price(20)
                .name("123")
                .build()), "valid condition");
        Assertions.assertTrue(predicate.test(RestaurantVO
                .builder()
                .customerRating(4)
                .distance(9)
                .price(10)
                .name("Restaurant 123")
                .build()), "valid condition");
        Assertions.assertFalse(predicate.test(RestaurantVO
                .builder()
                .customerRating(3)
                .distance(10)
                .price(30)
                .name("Restaurant 123 Restaurant")
                .build()), "valid condition");
        Assertions.assertTrue(predicate.test(RestaurantVO
                .builder()
                .customerRating(3)
                .distance(10)
                .price(1)
                .name("123 Restaurant")
                .build()), "valid condition");
        Assertions.assertFalse(predicate.test(RestaurantVO
                .builder()
                .customerRating(5)
                .distance(1)
                .name("12 Restaurant")
                .build()), "valid condition");
    }

    @Test
    void testRestaurantNameAndCustomerRatingAndDistanceAndPriceAndCuisineNamePredicate() {

        var restaurantSearch = RestaurantSearchVO
                .builder()
                .customerRating(3)
                .distance(10)
                .price(20)
                .restaurantName("123")
                .cuisineName("456")
                .build();
        var predicate = RestaurantPredicateUtil.buildPredicateFilter(restaurantSearch);
        Assertions.assertNotNull(predicate, "not null");
        Assertions.assertTrue(predicate.test(RestaurantVO
                .builder()
                .customerRating(3)
                .distance(10)
                .price(20)
                .name("123")
                .cuisine(CuisineVO
                        .builder()
                        .name("456")
                        .build())
                .build()), "valid condition");
        Assertions.assertTrue(predicate.test(RestaurantVO
                .builder()
                .customerRating(4)
                .distance(9)
                .price(10)
                .name("Restaurant 123")
                .cuisine(CuisineVO
                        .builder()
                        .name("Cuisine 456")
                        .build())
                .build()), "valid condition");
        Assertions.assertFalse(predicate.test(RestaurantVO
                .builder()
                .customerRating(3)
                .distance(10)
                .price(20)
                .cuisine(CuisineVO
                        .builder()
                        .name("Cuisine 45")
                        .build())
                .name("Restaurant 123 Restaurant")
                .build()), "valid condition");
        Assertions.assertTrue(predicate.test(RestaurantVO
                .builder()
                .customerRating(3)
                .distance(10)
                .price(1)
                .name("123 Restaurant")
                .cuisine(CuisineVO
                        .builder()
                        .name("Cuisine 456 Cuisine")
                        .build())
                .build()), "valid condition");
        Assertions.assertFalse(predicate.test(RestaurantVO
                .builder()
                .customerRating(5)
                .distance(1)
                .name("12 Restaurant")
                .build()), "valid condition");
    }

}
