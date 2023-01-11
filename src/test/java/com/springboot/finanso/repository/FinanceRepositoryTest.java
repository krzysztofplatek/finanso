package com.springboot.finanso.repository;

import com.springboot.finanso.dao.FinanceRepository;
import com.springboot.finanso.entity.Finance;

import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class FinanceRepositoryTest {

    @Autowired
    FinanceRepository financeRepository;

    @Test
    public void testFindByTitle() {
        List<Finance> expectedList = entities()
                .map(financeRepository::save)
                .filter(finance -> finance.getTitle()
                        .equals("title2"))
                .toList();

        List<Finance> actualList = financeRepository.findByTitle("title2");

        assertEquals(expectedList, actualList);
    }

    @Test
    public void shouldReturnListOfAllFinances() {
        entities().forEach(financeRepository::save);
        List<Finance> financesList = financeRepository.findAll();
        assertThat(financesList).isNotNull();
        assertThat(financesList.size()).isEqualTo(4);
    }

    @Test
    public void shouldDeleteFinance() {
        entities().forEach(financeRepository::save);

        financeRepository.findById(102)
                .ifPresent(finance -> financeRepository.deleteById(finance.getId()));

        Optional<Finance> financeDeleted = financeRepository.findById(102);
        assertThat(financeDeleted).isEmpty();
    }

    private Stream<Finance> entities() {
        return Stream.of(
                new Finance(1, "title1", 11.98, "category1", "comment1", Date.valueOf("2019-12-12")),
                new Finance(2, "title2", -12.55, "category2", "comment111", Date.valueOf("2020-04-11")),
                new Finance(3, "title4", 911.12, "category3", "comment3", Date.valueOf("2021-11-25")),
                new Finance(4, "title2", 61.15, "category4", "comment4", Date.valueOf("2023-04-23"))
        );
    }

}
