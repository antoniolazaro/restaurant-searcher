package com.restaurant.searcher.application.service.restaurant.util;

import com.restaurant.searcher.domain.exceptions.badrequest.InvalidTextException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public final class TextValidator {

    private TextValidator(){
    }

    public static boolean validate(String text){
        if (StringUtils.isNotBlank(text)) {
            if(text.length() < 2) {
                log.error("Restaurant name should has at least 2 character");
                throw new InvalidTextException(text);
            }
            return true;
        }
        return false;
    }
}
