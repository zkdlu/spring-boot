package com.zkdlu.querydsl.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PersonQueryRepositoryTest {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PersonQueryRepository personQueryRepository;

    @AfterEach
    void tearDown() {
        personRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("QueryDSL 테스트")
    public void queryDsl_Test() {
        //given
        String name = "geon";
        personRepository.save(new Person(name));

        //when
        List<Person> result = personQueryRepository.findByName(name);

        //then
        assertThat(result.size()).isEqualTo(1);
    }
}