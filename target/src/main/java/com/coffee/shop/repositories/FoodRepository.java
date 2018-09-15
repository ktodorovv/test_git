package com.coffee.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coffee.shop.entities.menuitems.Food;
import com.coffee.shop.models.view.menuitem.MenuItemSingleView;

@Repository
public interface FoodRepository extends JpaRepository<Food, String>{

	Food findOneById(String id);
}
