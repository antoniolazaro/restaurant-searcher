package com.restaurant.searcher.application.service.dataloader;

import com.restaurant.searcher.domain.model.vo.RestaurantVO;

import java.util.List;

public interface RestaurantDataLoaderService {

    List<RestaurantVO> loadData();
}
