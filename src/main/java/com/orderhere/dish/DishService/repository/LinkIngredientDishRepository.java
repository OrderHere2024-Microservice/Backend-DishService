package com.orderhere.dish.DishService.repository;

import com.amazonaws.xray.spring.aop.XRayEnabled;
import com.orderhere.dish.DishService.model.Dish;
import com.orderhere.dish.DishService.model.Ingredient;
import com.orderhere.dish.DishService.model.LinkIngredientDish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@XRayEnabled
public interface LinkIngredientDishRepository extends JpaRepository<LinkIngredientDish, Integer> {
    List<LinkIngredientDish> findByDish(Dish dish);

    Optional<Object> findByDishAndIngredient(Dish dish, Ingredient ingredient);
}
