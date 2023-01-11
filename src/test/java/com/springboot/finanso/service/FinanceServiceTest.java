package com.springboot.finanso.service;

import com.springboot.finanso.dao.FinanceRepository;
import com.springboot.finanso.entity.Finance;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class FinanceServiceTest {

    @Mock
    private FinanceRepository financeRepository;

    @InjectMocks
    FinanceServiceImpl financeService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldSaveFinance() {
        Finance finance = new Finance(1, "title1", 11.98, "category1", "comment1", Date.valueOf("2019-12-12"));
        when(financeRepository.save(any(Finance.class))).thenReturn(finance);
        Finance savedFinance = financeRepository.save(finance);
        assertThat(savedFinance.getTitle()).isNotNull();
    }

    @Test
    public void shouldReturnAllFinances() {
        Finance finance1 = new Finance(1, "title1", 11.98, "category1", "comment1", Date.valueOf("2019-12-12"));
        Finance finance2 = new Finance(2, "title2", -41.98, "category2", "comment2", Date.valueOf("2022-12-12"));
        List<Finance> financesList = new ArrayList<>();
        financesList.add(finance1);
        financesList.add(finance2);

        when(financeRepository.findAll()).thenReturn(financesList);
        List<Finance> returnedFinances = financeService.findAll();
        assertThat(returnedFinances.size()).isGreaterThan(1);
    }

    @Test
    public void shouldReturnFinanceById() {
        when(financeRepository.findById(1)).thenReturn(Optional.of(new Finance(1, "title1", 11.98, "category1", "comment1", Date.valueOf("2019-12-12"))));
        Finance finance = financeService.findById(1);
        assertEquals("title1", finance.getTitle());
        assertEquals("category1", finance.getCategory());
    }

}
