package com.restaurant.searcher.domain.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RestaurantVO {
    private String name;
    private Integer customerRating;
    private BigDecimal distance;
    private BigDecimal price;
    private CuisineVO cuisine;
}
