package com.restaurant.searcher.application.service.dataloader.impl;

import com.restaurant.searcher.application.service.dataloader.CuisineDataLoaderService;
import com.restaurant.searcher.application.service.dataloader.RestaurantDataLoaderService;
import com.restaurant.searcher.application.util.ResourceUtil;
import com.restaurant.searcher.domain.constants.Constants;
import com.restaurant.searcher.domain.exception.internalserver.DataFileErrorException;
import com.restaurant.searcher.domain.model.vo.RestaurantVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
@Slf4j
@AllArgsConstructor
public class RestaurantDataLoaderServiceImpl implements RestaurantDataLoaderService {
    private final CuisineDataLoaderService cuisineDataLoaderService;

    @Cacheable(cacheNames = Constants.CACHE_KEY_RESTAURANTS)
    public List<RestaurantVO> loadData() {

        var cuisines = cuisineDataLoaderService.loadData();
        var restaurantMap = new HashMap<String, RestaurantVO>();
        try {
            var streamRestaurantsCsv = ResourceUtil.loadStreamFromFile(Constants.PATH_DATA_RESTAURANTS);
            streamRestaurantsCsv.forEach(line -> {
                if (!line.startsWith("name")) {
                    var data = line.split(Constants.CSV_SEPARATOR);
                    var name = data[0];
                    restaurantMap.put(name, RestaurantVO
                            .builder()
                            .name(name)
                            .customerRating(Integer.valueOf(data[1]))
                            .distance(Integer.valueOf(data[2]))
                            .price(Integer.valueOf(data[3]))
                            .cuisine(cuisines.get(Integer.valueOf(data[4])))
                            .build()
                    );
                }
            });
        }
        catch (RuntimeException ex) {
            log.error("Error reading data {} ",ex.getMessage());
            throw new DataFileErrorException(ex.getMessage());
        }
        return new ArrayList<>(restaurantMap.values());
    }
}
