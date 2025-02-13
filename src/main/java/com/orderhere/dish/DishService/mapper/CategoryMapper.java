package com.orderhere.dish.DishService.mapper;

import com.orderhere.dish.DishService.dto.CategoryGetDto;
import com.orderhere.dish.DishService.dto.CategoryPostDto;
import com.orderhere.dish.DishService.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {
  CategoryGetDto CategoryToCategoryGetDto(Category category);

  Category CategoryPostDtoToCategory(CategoryPostDto categoryPostDto);
}
