package com.restaurant.searcher.application.service.impl;

import com.restaurant.searcher.application.service.restaurant.impl.RestaurantSearchValidatorServiceImpl;
import com.restaurant.searcher.domain.exceptions.badrequest.InvalidTextException;
import com.restaurant.searcher.domain.exceptions.badrequest.OutsideIntervalException;
import com.restaurant.searcher.domain.exceptions.badrequest.RequiredParameterException;
import com.restaurant.searcher.domain.model.vo.RestaurantSearchVO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RestaurantSearchValidatorServiceImplTest  {

    @InjectMocks
    private RestaurantSearchValidatorServiceImpl restaurantSearchValidatorService;

    @Test
    void testRestaurantSearchNull() {

        Assertions.assertThrows(RequiredParameterException.class,
                () -> restaurantSearchValidatorService.validateRestaurantSearch(null),
                "Is valid");
    }

    @Test
    void testRestaurantSearchEmpty() {

        Assertions.assertThrows(RequiredParameterException.class,
                () -> restaurantSearchValidatorService.validateRestaurantSearch(RestaurantSearchVO.builder().build()),
                "Is valid");
    }

    @Test
    void testRestaurantSearchInvalidName() {

        Assertions.assertThrows(InvalidTextException.class,
                () -> restaurantSearchValidatorService.validateRestaurantSearch(RestaurantSearchVO
                        .builder()
                                .restaurantName("A")
                        .build()),
                "Is valid");
    }

    @Test
    void testRestaurantSearchValidName() {

        restaurantSearchValidatorService.validateRestaurantSearch(RestaurantSearchVO
                .builder()
                .restaurantName("AB")
                .build());

        restaurantSearchValidatorService.validateRestaurantSearch(RestaurantSearchVO
                .builder()
                .restaurantName("ABC")
                .build());
    }

    @Test
    void testRestaurantSearchInvalidCustomerRating() {

        Assertions.assertThrows(OutsideIntervalException.class,
                () -> restaurantSearchValidatorService.validateRestaurantSearch(RestaurantSearchVO
                        .builder()
                        .customerRating(-1)
                        .build()),
                "Is valid");

        Assertions.assertThrows(OutsideIntervalException.class,
                () -> restaurantSearchValidatorService.validateRestaurantSearch(RestaurantSearchVO
                        .builder()
                        .customerRating(0)
                        .build()),
                "Is valid");

        Assertions.assertThrows(OutsideIntervalException.class,
                () -> restaurantSearchValidatorService.validateRestaurantSearch(RestaurantSearchVO
                        .builder()
                        .customerRating(6)
                        .build()),
                "Is valid");
    }

    @Test
    void testRestaurantSearchValidCustomerRating() {

        restaurantSearchValidatorService.validateRestaurantSearch(RestaurantSearchVO
                .builder()
                .customerRating(1)
                .build());

        restaurantSearchValidatorService.validateRestaurantSearch(RestaurantSearchVO
                .builder()
                .customerRating(2)
                .build());

        restaurantSearchValidatorService.validateRestaurantSearch(RestaurantSearchVO
                .builder()
                .customerRating(3)
                .build());

        restaurantSearchValidatorService.validateRestaurantSearch(RestaurantSearchVO
                .builder()
                .customerRating(4)
                .build());

        restaurantSearchValidatorService.validateRestaurantSearch(RestaurantSearchVO
                .builder()
                .customerRating(5)
                .build());

    }

    @Test
    void testRestaurantSearchInvalidDistance() {

        Assertions.assertThrows(OutsideIntervalException.class,
                () -> restaurantSearchValidatorService.validateRestaurantSearch(RestaurantSearchVO
                        .builder()
                        .distance(-1)
                        .build()),
                "Is valid");

        Assertions.assertThrows(OutsideIntervalException.class,
                () -> restaurantSearchValidatorService.validateRestaurantSearch(RestaurantSearchVO
                        .builder()
                        .distance(0)
                        .build()),
                "Is valid");

        Assertions.assertThrows(OutsideIntervalException.class,
                () -> restaurantSearchValidatorService.validateRestaurantSearch(RestaurantSearchVO
                        .builder()
                        .distance(11)
                        .build()),
                "Is valid");

    }

    @Test
    void testRestaurantSearchValidDistance() {

        restaurantSearchValidatorService.validateRestaurantSearch(RestaurantSearchVO
                .builder()
                .distance(1)
                .build());

        restaurantSearchValidatorService.validateRestaurantSearch(RestaurantSearchVO
                .builder()
                .distance(5)
                .build());

        restaurantSearchValidatorService.validateRestaurantSearch(RestaurantSearchVO
                .builder()
                .distance(10)
                .build());

    }

    @Test
    void testRestaurantSearchInvalidPrice() {

        Assertions.assertThrows(OutsideIntervalException.class,
                () -> restaurantSearchValidatorService.validateRestaurantSearch(RestaurantSearchVO
                        .builder()
                        .price(-1)
                        .build()),
                "Is valid");

        Assertions.assertThrows(OutsideIntervalException.class,
                () -> restaurantSearchValidatorService.validateRestaurantSearch(RestaurantSearchVO
                        .builder()
                        .price(0)
                        .build()),
                "Is valid");

        Assertions.assertThrows(OutsideIntervalException.class,
                () -> restaurantSearchValidatorService.validateRestaurantSearch(RestaurantSearchVO
                        .builder()
                        .price(9)
                        .build()),
                "Is valid");

        Assertions.assertThrows(OutsideIntervalException.class,
                () -> restaurantSearchValidatorService.validateRestaurantSearch(RestaurantSearchVO
                        .builder()
                        .price(51)
                        .build()),
                "Is valid");

    }

    @Test
    void testRestaurantSearchValidPrice() {

        restaurantSearchValidatorService.validateRestaurantSearch(RestaurantSearchVO
                .builder()
                .price(10)
                .build());

        restaurantSearchValidatorService.validateRestaurantSearch(RestaurantSearchVO
                .builder()
                .price(25)
                .build());

        restaurantSearchValidatorService.validateRestaurantSearch(RestaurantSearchVO
                .builder()
                .price(50)
                .build());

    }

    @Test
    void testRestaurantSearchInvalidCuisineName() {

        Assertions.assertThrows(InvalidTextException.class,
                () -> restaurantSearchValidatorService.validateRestaurantSearch(RestaurantSearchVO
                        .builder()
                        .cuisineName("A")
                        .build()),
                "Is valid");
    }

    @Test
    void testRestaurantSearchValidCuisineName() {

        restaurantSearchValidatorService.validateRestaurantSearch(RestaurantSearchVO
                .builder()
                .cuisineName("AB")
                .build());

        restaurantSearchValidatorService.validateRestaurantSearch(RestaurantSearchVO
                .builder()
                .cuisineName("ABC")
                .build());
    }

}
