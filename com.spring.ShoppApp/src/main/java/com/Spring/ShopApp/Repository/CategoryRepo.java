package com.Spring.ShopApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Spring.ShopApp.model.Category;

public interface CategoryRepo  extends JpaRepository<Category, Long> {

}
