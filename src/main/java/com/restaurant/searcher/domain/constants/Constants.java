package com.restaurant.searcher.domain.constants;

public interface Constants {
    String CSV_SEPARATOR = ",";
    String CACHE_KEY_CUISINES = "KEY_CUISINES";
    String CACHE_KEY_RESTAURANTS = "KEY_RESTAURANTS";
    String PATH_DATA_CUISINES = "classpath:data/cuisines.csv";
    String PATH_DATA_RESTAURANTS = "classpath:data/restaurants.csv";
    Integer MAX_SEARCH_RESULT = 5;

    Integer MIN_CUSTOMER_RATING = 1;
    Integer MAX_CUSTOMER_RATING = 5;

    Integer MIN_DISTANCE = 1;
    Integer MAX_DISTANCE = 10;

    Integer MIN_PRICE = 10;
    Integer MAX_PRICE = 50;
}
