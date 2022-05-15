package com.restaurant.searcher.application.service.restaurant.util;

import com.restaurant.searcher.domain.exceptions.badrequest.OutsideIntervalException;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public final class IntervalValidator {

    private IntervalValidator(){
    }

    public static boolean validate(Integer val, Integer min, Integer max){
        if (Objects.nonNull(val)) {
            if (val < min || val > max) {
                log.error("Customer rating should be between {} and {}. Value informed: {} ", min, max, val);
                throw new OutsideIntervalException(min, max, val);
            }
            return true;
        }
        return false;
    }
}
