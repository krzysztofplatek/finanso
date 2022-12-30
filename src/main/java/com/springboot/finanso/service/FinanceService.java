package com.springboot.finanso.service;

import com.springboot.finanso.entity.Finance;

import java.util.List;

public interface FinanceService {

    List<Finance> findAll();

    Finance findById(int id);

    void save(Finance finance);

    void deleteById(int id);

}
