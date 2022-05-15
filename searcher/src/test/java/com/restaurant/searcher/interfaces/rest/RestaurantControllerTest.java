package com.restaurant.searcher.interfaces.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurant.searcher.application.service.restaurant.RestaurantService;
import com.restaurant.searcher.domain.exception.badrequest.RequiredParameterException;
import com.restaurant.searcher.domain.model.rest.response.RestaurantRestResponse;
import com.restaurant.searcher.domain.model.transform.RestaurantTransform;
import com.restaurant.searcher.domain.model.vo.RestaurantSearchVO;
import com.restaurant.searcher.domain.model.vo.RestaurantVO;
import com.restaurant.searcher.interfaces.rest.exception.RestaurantSearcherExceptionHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RestaurantController.class)
@AutoConfigureMockMvc(addFilters = false)
@Execution(ExecutionMode.SAME_THREAD)
public class RestaurantControllerTest {

    private static final String URI = "/restaurant";

    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private RestaurantService restaurantService;
    @MockBean
    private RestaurantTransform restaurantTransform;
    private MockMvc mockMvc;

    @BeforeEach
    void beforeEach(){
        mockMvc = MockMvcBuilders
                .standaloneSetup(new RestaurantController(restaurantService, restaurantTransform))
                .setControllerAdvice(new RestaurantSearcherExceptionHandler()).build();
    }

    @Test
    void testRestauranteControllerThrowSomeBadRequestException() throws Exception{
        var restaurantName = "abc";
        when(restaurantService.search(eq(RestaurantSearchVO.builder().restaurantName(restaurantName).build())))
                .thenThrow(RequiredParameterException.class);

        mockMvc.perform(MockMvcRequestBuilders
                .get(URI)
                .param("restaurantName", restaurantName)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }

    @Test
    void testRestauranteControllerThrowSomeInternalServerErrorException() throws Exception{
        var restaurantName = "abc";
        when(restaurantService.search(eq(RestaurantSearchVO.builder().restaurantName(restaurantName).build())))
                .thenThrow(new RuntimeException("Error"));

        mockMvc.perform(MockMvcRequestBuilders
                .get(URI)
                .param("restaurantName", restaurantName)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isInternalServerError());
    }

    @Test
    void testRestauranteControllerSuccessful() throws Exception{
        var restaurantName = "123";

        var list = List.of(
                RestaurantVO.builder().name("Restaurant 123").distance(2).customerRating(2).price(10).build(),
                RestaurantVO.builder().name("123456").distance(1).customerRating(5).price(1).build(),
                RestaurantVO.builder().name("123 Restaurant").distance(3).customerRating(3).price(15).build(),
                RestaurantVO.builder().name("Restaurant 123 Restaurant").distance(3).customerRating(3).price(1).build(),
                RestaurantVO.builder().name("12345").distance(5).customerRating(4).price(3).build());

        when(restaurantService.search(eq(RestaurantSearchVO.builder().restaurantName(restaurantName).build())))
                .thenReturn(list);

        var listTransformed = List.of(
                RestaurantRestResponse.builder().name("Restaurant 123").distance(2).customerRating(2).price(10).build(),
                RestaurantRestResponse.builder().name("123456").distance(1).customerRating(5).price(1).build(),
                RestaurantRestResponse.builder().name("123 Restaurant").distance(3).customerRating(3).price(15).build(),
                RestaurantRestResponse.builder().name("Restaurant 123 Restaurant").distance(3).customerRating(3).price(1).build(),
                RestaurantRestResponse.builder().name("12345").distance(5).customerRating(4).price(3).build());

        when(restaurantTransform.toListRestaurantRestResponse(eq(list))).thenReturn(listTransformed);

        var responseAsString = mockMvc.perform(MockMvcRequestBuilders
                .get(URI)
                .param("restaurantName", restaurantName)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        var data = objectMapper.readValue(responseAsString,
                new TypeReference<List<RestaurantRestResponse>>() {});
        Assertions.assertNotNull(data, "not null");
        Assertions.assertEquals(5, data.size(), "valid condition");
        Assertions.assertEquals(listTransformed.get(0).getName(), data.get(0).getName(), "valid condition");
        Assertions.assertEquals(listTransformed.get(1).getName(), data.get(1).getName(), "valid condition");
        Assertions.assertEquals(listTransformed.get(2).getName(), data.get(2).getName(), "valid condition");
        Assertions.assertEquals(listTransformed.get(3).getName(), data.get(3).getName(), "valid condition");
        Assertions.assertEquals(listTransformed.get(4).getName(), data.get(4).getName(), "valid condition");
    }

    @Configuration
    @Import({RestaurantController.class, RestaurantSearcherExceptionHandler.class})
    public static class SpringTestConfig{

    }
}
