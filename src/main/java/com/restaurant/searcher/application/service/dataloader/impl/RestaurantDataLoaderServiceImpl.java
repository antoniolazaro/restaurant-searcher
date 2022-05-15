package com.restaurant.searcher.application.service.dataloader.impl;

import com.restaurant.searcher.application.service.dataloader.CuisineDataLoaderService;
import com.restaurant.searcher.application.service.dataloader.RestaurantDataLoaderService;
import com.restaurant.searcher.application.util.ResourceUtil;
import com.restaurant.searcher.domain.constants.Constants;
import com.restaurant.searcher.domain.exceptions.internalserver.DataFileErrorException;
import com.restaurant.searcher.domain.model.vo.RestaurantVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
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

        log.debug("Loading cuisines data");
        var cuisines = cuisineDataLoaderService.loadData();
        log.debug("cuisines data loaded");
        var restaurantMap = new HashMap<String, RestaurantVO>();
        try {
            log.debug("Loading restaurants data");
            var streamRestaurantsCsv = ResourceUtil.loadStreamFromFile(Constants.PATH_DATA_RESTAURANTS);
            log.debug("restaurants data loaded");
            streamRestaurantsCsv.forEach(line -> {
                if (!line.startsWith("name")) {
                    log.debug("processing line {} ", line);
                    var data = line.split(Constants.CSV_SEPARATOR);
                    log.debug("processing data {} ", data);
                    var name = data[0];
                    var restaurant = RestaurantVO
                            .builder()
                            .name(name)
                            .customerRating(Integer.valueOf(data[1]))
                            .distance(new BigDecimal(data[2]))
                            .price(new BigDecimal(data[3]))
                            .cuisine(cuisines.get(Integer.valueOf(data[4])))
                            .build();
                    log.debug("saving restaurant {} ", restaurant);
                    restaurantMap.put(name, restaurant);
                    log.debug("saved restaurant {} ", restaurant);
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
