package com.restaurant.searcher.domain.model.rest.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RestaurantRestResponse {
    private String name;
    private Integer customerRating;
    private BigDecimal distance;
    private BigDecimal price;
    private String cuisineName;
}
