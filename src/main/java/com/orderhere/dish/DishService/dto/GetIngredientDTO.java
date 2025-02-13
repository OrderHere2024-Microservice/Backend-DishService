package com.orderhere.dish.DishService.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GetIngredientDTO {
    private Integer linkIngredientDishId;
    private Integer dishId;
    private Integer ingredientId;
    private BigDecimal quantityValue;
    private String quantityUnit;
}
