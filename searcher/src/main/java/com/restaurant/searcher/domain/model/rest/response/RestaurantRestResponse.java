package com.restaurant.searcher.domain.model.rest.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RestaurantRestResponse {
    private String name;
    private Integer customerRating;
    private Integer distance;
    private Integer price;
    private String cuisineName;
}
