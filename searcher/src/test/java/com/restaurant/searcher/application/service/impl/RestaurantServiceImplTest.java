package com.restaurant.searcher.application.service.impl;

import com.restaurant.searcher.application.service.dataloader.RestaurantDataLoaderService;
import com.restaurant.searcher.application.service.restaurant.RestaurantSearchValidatorService;
import com.restaurant.searcher.application.service.restaurant.impl.RestaurantServiceImpl;
import com.restaurant.searcher.domain.exceptions.badrequest.InvalidTextException;
import com.restaurant.searcher.domain.exceptions.badrequest.OutsideIntervalException;
import com.restaurant.searcher.domain.exceptions.badrequest.RequiredParameterException;
import com.restaurant.searcher.domain.exceptions.internalserver.DataFileErrorException;
import com.restaurant.searcher.domain.model.vo.RestaurantSearchVO;
import com.restaurant.searcher.domain.model.vo.RestaurantVO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RestaurantServiceImplTest {

    @Mock
    private RestaurantDataLoaderService restaurantDataLoaderService;
    @Mock
    private RestaurantSearchValidatorService restaurantSearchValidatorService;

    @InjectMocks
    private RestaurantServiceImpl restaurantService;

    @Test
    void testValidationFailedWithRequiredParameterException() {

        var restaurantSearch = RestaurantSearchVO.builder().build();
        doThrow(RequiredParameterException.class).when(restaurantSearchValidatorService).validateRestaurantSearch(eq(restaurantSearch));

        Assertions.assertThrows(RequiredParameterException.class,
                () -> restaurantService.search(restaurantSearch),
                "Is valid");
    }

    @Test
    void testValidationFailedWithInvalidCustomerRatingException() {

        var restaurantSearch = RestaurantSearchVO.builder().build();
        doThrow(OutsideIntervalException.class).when(restaurantSearchValidatorService).validateRestaurantSearch(eq(restaurantSearch));

        Assertions.assertThrows(OutsideIntervalException.class,
                () -> restaurantService.search(restaurantSearch),
                "Is valid");
    }

    @Test
    void testValidationFailedWithPositiveNumberException() {

        var restaurantSearch = RestaurantSearchVO.builder().build();
        doThrow(OutsideIntervalException.class).when(restaurantSearchValidatorService).validateRestaurantSearch(eq(restaurantSearch));

        Assertions.assertThrows(OutsideIntervalException.class,
                () -> restaurantService.search(restaurantSearch),
                "Is valid");
    }

    @Test
    void testValidationFailedWithInvalidTextException() {

        var restaurantSearch = RestaurantSearchVO.builder().build();
        doThrow(InvalidTextException.class).when(restaurantSearchValidatorService).validateRestaurantSearch(eq(restaurantSearch));

        Assertions.assertThrows(InvalidTextException.class,
                () -> restaurantService.search(restaurantSearch),
                "Is valid");
    }

    @Test
    void testFailedLoadingData() {

        var restaurantSearch = RestaurantSearchVO.builder().build();
        doNothing().when(restaurantSearchValidatorService).validateRestaurantSearch(eq(restaurantSearch));
        when(restaurantDataLoaderService.loadData()).thenThrow(DataFileErrorException.class);

        Assertions.assertThrows(DataFileErrorException.class,
                () -> restaurantService.search(restaurantSearch),
                "Is valid");
    }

    @Test
    void testSearch() {

        var restaurantSearch = RestaurantSearchVO
                .builder()
                .restaurantName("123")
                .build();
        doNothing().when(restaurantSearchValidatorService).validateRestaurantSearch(eq(restaurantSearch));
        var firstItem = RestaurantVO.builder().name("123456").distance(1).customerRating(5).price(1).build();
        var list = List.of(
                firstItem,
                RestaurantVO.builder().name("Restaurant 123").distance(2).customerRating(2).price(10).build(),
                RestaurantVO.builder().name("123 Restaurant").distance(3).customerRating(3).price(15).build(),
                RestaurantVO.builder().name("Restaurant 123 Restaurant").distance(3).customerRating(3).price(1).build(),
                RestaurantVO.builder().name("12").distance(4).customerRating(4).price(20).build(),
                RestaurantVO.builder().name("1234").distance(5).customerRating(4).price(3).build(),
                RestaurantVO.builder().name("12345").distance(5).customerRating(4).price(3).build(),
                RestaurantVO.builder().name("134").distance(6).customerRating(5).price(5).build());
        when(restaurantDataLoaderService.loadData()).thenReturn(list);

        var dataFiltered = restaurantService.search(restaurantSearch);
        Assertions.assertNotNull(dataFiltered, "not null");
        Assertions.assertEquals(5, dataFiltered.size(), "valid condition");
        Assertions.assertEquals(firstItem, dataFiltered.get(0), "valid condition");
    }
}
