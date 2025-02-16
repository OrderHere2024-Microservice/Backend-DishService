package com.orderhere.dish.DishService.controller;

import com.orderhere.dish.DishService.dto.DishCreateDto;
import com.orderhere.dish.DishService.dto.DishGetDto;
import com.orderhere.dish.DishService.dto.DishUpdateDTO;
import com.orderhere.dish.DishService.dto.PagingDto;
import com.orderhere.dish.DishService.enums.DishSort;
import com.orderhere.dish.DishService.service.DishService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

import static com.orderhere.dish.DishService.util.SortHelper.getSortOrder;

@Controller
@RequiredArgsConstructor
public class DishGraphQLController {

    private final DishService dishService;

    @QueryMapping
    public PagingDto<List<DishGetDto>> getDishes(@Argument Integer restaurantId,
                                                 @Argument int page,
                                                 @Argument int size,
                                                 @Argument String sort,
                                                 @Argument String order) {
        return dishService.getDishPageByRestaurantId(
                restaurantId,
                page,
                size,
                DishSort.getEnumByString(sort),
                getSortOrder(order)
        );
    }

    @PreAuthorize("hasRole('sys_admin')")
    @MutationMapping
    public Boolean deleteDish(@Argument Integer dishId) throws Exception {
        dishService.deleteDish(dishId);
        return true;
    }

}
