package com.restaurant.searcher.application.util;

import com.restaurant.searcher.domain.exceptions.internalserver.GenericServerErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Stream;

@Slf4j
public final class ResourceUtil {

    private ResourceUtil(){
    }

    public static Stream<String> loadStreamFromFile(String path) {
        try{
            var csvData = ResourceUtils.getFile(path);
            return Files.lines(csvData.toPath());
        }catch(IOException ex){
            log.error("Error ao processar arquivo {}",ex.getMessage());
            throw new GenericServerErrorException(ex.getMessage());
        }
    }
}
