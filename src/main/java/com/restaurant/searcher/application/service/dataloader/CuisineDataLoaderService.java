package com.restaurant.searcher.application.service.dataloader;

import com.restaurant.searcher.domain.model.vo.CuisineVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public interface CuisineDataLoaderService{

    Map<Integer, CuisineVO> loadData();
}
