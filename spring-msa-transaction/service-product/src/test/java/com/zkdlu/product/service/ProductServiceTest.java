package com.zkdlu.product.service;

import com.zkdlu.product.domain.Product;
import com.zkdlu.product.domain.ProductRepository;
import com.zkdlu.product.domain.ProductRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    ProductRepository productRepository;
    @InjectMocks
    ProductService productService;

    List<Product> productList;

    @BeforeEach
    void setUp() {
        productList = Arrays.asList(
                Product.builder().id(UUID.fromString("edfe8ef2-68ab-4c8b-afaf-286e6bfbdabc"))
                        .name("1")
                        .image("https://placeimg.com/100/100/any")
                        .stock(1)
                        .build()
        );
    }

    @Test
    @DisplayName("전체 제품 목록 조회")
    void getProducts() {
        //given
        given(productRepository.findAll()).willReturn(productList);

        //when
        var result = productService.getProducts();

        //then
        assertThat(result).isEqualTo(productList);
    }

    @Test
    @DisplayName("페이지별 제품 목록 조회")
    void getProductsByPage() {
        //given
        given(productRepository.findAll(0)).willReturn(productList);

        //when
        var result = productService.getProductsByPage(0);

        //then
        assertThat(result).isEqualTo(productList);
    }

    @Test
    @DisplayName("제품 상세 조회")
    void getProductDetail() {
        //given
        UUID uuid = UUID.fromString("edfe8ef2-68ab-4c8b-afaf-286e6bfbdabc");
        given(productRepository.findById(uuid)).willReturn(Optional.of(Product.builder()
                .id(uuid)
                .image("image")
                .name("name")
                .stock(0)
                .build()));

        //when
        var result = productService.getProductDetail(uuid);

        //then
        assertThat(result.getId()).isEqualTo(uuid);
    }
}