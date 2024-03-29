package com.zkdlu;

import com.zkdlu.domain.product.Product;
import com.zkdlu.domain.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class KakaoPayApplication implements ApplicationRunner {
    public static void main(String[] args) {
        SpringApplication.run(KakaoPayApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    ProductRepository productRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        for (int i = 0; i < 100; i++) {
            productRepository.save(Product.builder()
                    .name("옷" + i)
                    .image("https://placeimg.com/100/100/any")
                    .price(1000 + i * 1000)
                    .stock(i)
                    .build());
            productRepository.save(Product.builder()
                    .name("신발" + i)
                    .price(1000 + i * 1000)
                    .stock(i)
                    .image("https://placeimg.com/100/100/any")
                    .build());
            productRepository.save(Product.builder()
                    .name("장난감" + i)
                    .image("https://placeimg.com/100/100/any")
                    .price(1000 + i * 1000)
                    .stock(i)
                    .build());

        }
    }
}
