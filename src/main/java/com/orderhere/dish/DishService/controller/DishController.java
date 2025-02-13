package com.orderhere.dish.DishService.controller;

import com.orderhere.dish.DishService.dto.DishCreateDto;
import com.orderhere.dish.DishService.dto.DishGetDto;
import com.orderhere.dish.DishService.dto.DishUpdateDTO;
import com.orderhere.dish.DishService.dto.PagingDto;
import com.orderhere.dish.DishService.enums.DishSort;
import com.orderhere.dish.DishService.service.DishService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.orderhere.dish.DishService.util.SortHelper.getSortOrder;

@RestController
@RequestMapping("/v1/public/dish")
@RequiredArgsConstructor
@Validated
public class DishController {
    private final DishService dishService;

    // still in use, will deprecate this when solving gql upload issue
    @PreAuthorize("hasRole('sys_admin')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void createDish(@Valid @ModelAttribute DishCreateDto dishCreateDto) {
        dishService.createDish(dishCreateDto);
    }

    // still in use, will deprecate this when solving gql upload issue
    @PreAuthorize("hasRole('sys_admin')")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public DishGetDto updateDish(
            @Valid @ModelAttribute DishUpdateDTO dishUpdateDto) {
        return dishService.updateDish(dishUpdateDto);
    }
}
