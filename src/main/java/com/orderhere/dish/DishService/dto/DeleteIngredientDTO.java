package com.orderhere.dish.DishService.dto;

import lombok.Data;

@Data
public class DeleteIngredientDTO {
    private Integer dishId;
    private Integer ingredientId;
}
