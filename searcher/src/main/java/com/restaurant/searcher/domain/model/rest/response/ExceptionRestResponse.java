package com.restaurant.searcher.domain.model.rest.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExceptionRestResponse {

    @Builder.Default
    private final LocalDateTime time = LocalDateTime.now();
    private String description;
    private Integer httpStatusCode;
    private String httpStatusDescription;

}
