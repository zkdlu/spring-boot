package com.zkdlu.querydsl.domain;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.zkdlu.querydsl.domain.QPerson.person;

@RequiredArgsConstructor
@Repository
public class PersonQueryRepository {
    private final JPAQueryFactory queryFactory;

    public List<Person> findByName(String name) {
        return queryFactory.selectFrom(person)
                .where(person.name.eq(name))
                .fetch();
    }
}
