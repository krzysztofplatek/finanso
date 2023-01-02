package com.springboot.finanso.service;

import com.springboot.finanso.dao.FinanceRepository;
import com.springboot.finanso.entity.Finance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FinanceServiceImpl implements FinanceService {

    final private FinanceRepository financeRepository;

    @Autowired
    public FinanceServiceImpl(FinanceRepository financeRepository) {
        this.financeRepository = financeRepository;
    }

    @Override
    public List<Finance> findAll() {
        return financeRepository.findAll();

    }

    @Override
    public Finance findById(int id) {
        Optional<Finance> result = financeRepository.findById(id);

        Finance finance;

        if (result.isPresent())
            finance = result.get();
        else
            throw new RuntimeException("Did not found employee id - " + id);

        return finance;
    }

    @Override
    public void save(Finance finance) {
        financeRepository.save(finance);
    }

    @Override
    public void deleteById(int id) {
        financeRepository.deleteById(id);
    }

}
