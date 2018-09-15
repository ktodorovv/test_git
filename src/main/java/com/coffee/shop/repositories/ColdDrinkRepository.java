package com.coffee.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coffee.shop.entities.menuitems.ColdDrink;

@Repository
public interface ColdDrinkRepository extends JpaRepository<ColdDrink, String>{

	ColdDrink findOneById(String id);
}
