package com.orderhere.dish.DishService.eventDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishDeletedEvent {
    private Integer dishId;
    private String dishName;
}
