package com.restaurant.searcher.domain.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RestaurantSearchVO {
    private String restaurantName;
    private Integer customerRating;
    private Integer distance;
    private Integer price;
    private String cuisineName;
}
