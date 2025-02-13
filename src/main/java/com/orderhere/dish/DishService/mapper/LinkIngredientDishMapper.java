package com.orderhere.dish.DishService.mapper;

import com.orderhere.dish.DishService.dto.GetIngredientDTO;
import com.orderhere.dish.DishService.model.LinkIngredientDish;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LinkIngredientDishMapper {
    @Mapping(target = "dishId", source = "dish.dishId")
    @Mapping(target = "ingredientId", source = "ingredient.ingredientId")
    GetIngredientDTO toDto(LinkIngredientDish linkIngredientDish);

}
