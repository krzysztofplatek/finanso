package com.springboot.finanso.dao;

import com.springboot.finanso.entity.Finance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FinanceRepository extends JpaRepository<Finance, Integer> {

    Page<Finance> findAll(Pageable pageable);

    List<Finance> findAll(Sort sort);

    @Query(value = "select * from Finance f where f.title like %:keyword%", nativeQuery = true)
    List<Finance> findByKeyword(@Param("keyword") String keyword);

}
