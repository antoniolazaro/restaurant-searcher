package com.restaurant.searcher.application.service.dataloader.impl;

import com.restaurant.searcher.application.service.dataloader.CuisineDataLoaderService;
import com.restaurant.searcher.application.util.ResourceUtil;
import com.restaurant.searcher.domain.constants.Constants;
import com.restaurant.searcher.domain.exceptions.internalserver.DataFileErrorException;
import com.restaurant.searcher.domain.model.vo.CuisineVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
@AllArgsConstructor
public class CuisineDataLoaderServiceImpl implements CuisineDataLoaderService {

    @Cacheable(cacheNames = Constants.CACHE_KEY_CUISINES)
    public Map<Integer, CuisineVO> loadData() {
        var cuisinesMap = new HashMap<Integer, CuisineVO>();
        try {
            var streamCuisinesCsv = ResourceUtil.loadStreamFromFile(Constants.PATH_DATA_CUISINES);

            streamCuisinesCsv.forEach(line -> {
                if (!line.startsWith("id")) {
                    var data = line.split(Constants.CSV_SEPARATOR);
                    var id = Integer.valueOf(data[0]);
                    cuisinesMap.put(id, CuisineVO
                            .builder()
                            .id(id)
                            .name(data[1])
                            .build());
                }
            });
        } catch (RuntimeException ex) {
            log.error("Error reading data {} ",ex.getMessage());
            throw new DataFileErrorException(ex.getMessage());
        }
        return cuisinesMap;
    }
}
