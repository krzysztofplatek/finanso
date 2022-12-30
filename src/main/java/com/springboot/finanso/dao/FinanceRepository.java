package com.springboot.finanso.dao;

import com.springboot.finanso.entity.Finance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinanceRepository extends JpaRepository<Finance, Integer> {
}
