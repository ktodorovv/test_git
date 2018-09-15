package com.coffee.shop.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.coffee.shop.entities.enums.HotDrinkType;
import com.coffee.shop.entities.menuitems.HotDrink;

@Repository
@Transactional
public interface HotDrinkRepository extends JpaRepository<HotDrink, String>{
	
	List<HotDrink> findAllByType(HotDrinkType type);
	
	HotDrink findOneById(String id);
}
