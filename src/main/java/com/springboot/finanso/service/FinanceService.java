package com.springboot.finanso.service;

import com.springboot.finanso.entity.Finance;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FinanceService {

    List<Finance> findAll();

    Finance findById(int id);

    void save(Finance finance);

    void deleteById(int id);

    Page<Finance> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);


}
