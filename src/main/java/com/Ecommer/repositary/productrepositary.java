package com.Ecommer.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Ecommer.model.product;

@Repository
public interface productrepositary extends JpaRepository<product, Integer>{

}
