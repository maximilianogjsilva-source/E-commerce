package com.max.carpincho.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.max.carpincho.CarpinchoApplication;
import com.max.carpincho.DataProvider;
import com.max.carpincho.controller.dto.ProductDTO;
import com.max.carpincho.security.config.SecurityConfig;
import com.max.carpincho.security.controller.dto.AuthResponse;
import com.max.carpincho.security.controller.dto.AuthUserRequest;
import com.max.carpincho.security.persistence.entity.PermissionEntity;
import com.max.carpincho.security.persistence.entity.RoleEntity;
import com.max.carpincho.security.persistence.entity.RoleEnum;
import com.max.carpincho.security.persistence.entity.UserEntity;
import com.max.carpincho.security.service.UserEntityServiceImpl;
import com.max.carpincho.service.interfaces.IProductResponseService;
import com.max.carpincho.service.util.ProductMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.test.context.support.WithSecurityContext;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.*;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.RETURNS_MOCKS;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static com.max.carpincho.service.util.ProductMapper.toProductDTO;

@WebMvcTest(ProductController.class)
@ActiveProfiles("test")
@WithUserDetails
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private IProductResponseService responseService;

    @Autowired
    private ObjectMapper objectMapper;



    @Test
    void When_Call_Products_Status_200() throws Exception {
        //Given
        List<ProductDTO> productList = DataProvider.listAll().stream().map(ProductMapper::toProductDTO).toList();
        given(responseService.listAll()).willReturn(productList);

        //WHen
        ResultActions response = mockMvc.perform(get("/demo/list-items")
                .contentType(MediaType.APPLICATION_JSON));

        //Then
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()", is(productList.size())));

    }

    @Test
    void When_Add_Product_Status_201() throws Exception {
        //Given
        ProductDTO product1 = toProductDTO(DataProvider.getNewProductId());
        given(responseService.saveProduct( any(ProductDTO.class) )).willReturn(product1);
                //.willAnswer((invocation -> invocation.getArgument(0)));

        //When
        ResultActions response = mockMvc.perform(post("/demo/add-item")
                        .content(objectMapper.writeValueAsString(product1))
                        .contentType(MediaType.APPLICATION_JSON));

        //Then
        response.andExpect(status().isCreated())
                .andDo(print())
                .andExpect(jsonPath("$.title", is(product1.title())))
                .andExpect(jsonPath("$.srcImg", is(product1.srcImg())))
                .andExpect(jsonPath("$.description", is(product1.description())))
                .andExpect(jsonPath("$.price", is(product1.price())));
    }

    @Test
    void WHen_Call_ProductById_Status_200() throws Exception {
        //Given
        ProductDTO product1 = toProductDTO(DataProvider.getProductId());
        given(responseService.getById( anyInt() )).willReturn(product1);

        //When
        ResultActions response = mockMvc.perform(
                get("/demo/get-id/{id}", product1.id())
                        .contentType(MediaType.APPLICATION_JSON)
        );

        //Then
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.title", is(product1.title())))
                .andExpect(jsonPath("$.srcImg", is(product1.srcImg())))
                .andExpect(jsonPath("$.description", is(product1.description())))
                .andExpect(jsonPath("$.price", is(product1.price())));
    }

    @Test
    void When_Edit_Product_Status_200() throws Exception {
        //Given
        ProductDTO productEdited = toProductDTO(DataProvider.getProductEditedId());
        given(responseService.editProduct(anyInt(), any(ProductDTO.class)))
                .willAnswer((invocation -> invocation.getArgument(1)));

        //When
        ResultActions response = mockMvc.perform(
                put("/demo/update/{id}", productEdited.id())
                        .content(objectMapper.writeValueAsString(productEdited))
                        .contentType(MediaType.APPLICATION_JSON));

        //Then
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.title", is(productEdited.title())))
                .andExpect(jsonPath("$.srcImg", is(productEdited.srcImg())))
                .andExpect(jsonPath("$.description", is(productEdited.description())))
                .andExpect(jsonPath("$.price", is(productEdited.price())));

    }

    @Test
    void When_Delete_Product_Status_NoContent() throws Exception {
        //Given
        Integer id = 1;
        given(responseService.deleteProductById( anyInt() )).willReturn(ResponseEntity.noContent().build());

        //When
        ResultActions response = mockMvc.perform(delete("/demo/delete/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON));

        //Then
        response.andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    void When_Delete_Product_Status_NoFound() throws Exception {
        //Given
        Integer id = 1;
        given(responseService.deleteProductById( anyInt() )).willReturn(ResponseEntity.notFound().build());

        //When
        ResultActions response = mockMvc.perform(delete("/demo/delete/{id}", id)
                .contentType(MediaType.APPLICATION_JSON));

        //Then
        response.andExpect(status().isNotFound())
                .andDo(print());
    }


}
