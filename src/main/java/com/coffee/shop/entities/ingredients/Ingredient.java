package com.coffee.shop.entities.ingredients;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.coffee.shop.entities.enums.IngredientType;
import com.coffee.shop.entities.menuitems.HotDrink;

@Entity
@Table(name = "ingredients")
public class Ingredient {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
	private String id;
	
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
	private IngredientType ingredientType;
	
    @Column(nullable = false)
	private String name;
	
    @Column(nullable = false)
	private double calories;
	
    @Column(nullable = false)
	private double price;
	
    @Column(nullable = false)
	private double quantity;
    
	@ManyToMany
	@JoinTable(name = "additional_ingredients_hot_drinks",
	joinColumns = @JoinColumn(name = "additional_ingredient_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "hot_drink_id", referencedColumnName = "id"))
    private Set<HotDrink> hotDrinks;

	// TODO: need to separate the two ingredient types - BaseIngredient and AdditionalIngredient; The enum solution is worse
	@OneToMany(mappedBy = "baseIngredient", targetEntity = HotDrink.class, cascade = CascadeType.ALL)
	private Set<HotDrink> hotDrinksBaseIngredientFor;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public IngredientType getIngredientType() {
		return ingredientType;
	}

	public void setIngredientType(IngredientType ingredientType) {
		this.ingredientType = ingredientType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getCalories() {
		return calories;
	}

	public void setCalories(double calories) {
		this.calories = calories;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public Set<HotDrink> getHotDrinks() {
		return hotDrinks;
	}

	public void setHotDrinks(Set<HotDrink> hotDrinks) {
		this.hotDrinks = hotDrinks;
	}
}