package com.Ecommer.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.cdi.JpaRepositoryExtension;
import org.springframework.stereotype.Repository;

import com.Ecommer.model.Category;

@Repository
public interface categoriesrepos extends JpaRepository<Category, Integer> {
}



