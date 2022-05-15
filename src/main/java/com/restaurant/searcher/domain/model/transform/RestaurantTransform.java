package com.restaurant.searcher.domain.model.transform;

import com.restaurant.searcher.domain.model.rest.response.RestaurantRestResponse;
import com.restaurant.searcher.domain.model.vo.RestaurantVO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(builder = @Builder(disableBuilder = true), nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface RestaurantTransform {

    @Mapping(source = "cuisine.name", target = "cuisineName")
    RestaurantRestResponse toRestaurantRestResponse(RestaurantVO restaurantVO);
    List<RestaurantRestResponse> toListRestaurantRestResponse(List<RestaurantVO> listRestaurant);
}
