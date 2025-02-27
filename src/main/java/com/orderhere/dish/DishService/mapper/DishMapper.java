package com.orderhere.dish.DishService.mapper;

import com.orderhere.dish.DishService.dto.DishCreateDto;
import com.orderhere.dish.DishService.dto.DishGetDto;
import com.orderhere.dish.DishService.dto.DishUpdateDTO;
import com.orderhere.dish.DishService.model.Dish;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DishMapper {
  @Mapping(source = "category.categoryId", target = "categoryId")
  @Mapping(source = "category.categoryName", target = "categoryName")
  DishGetDto dishToDishGetDto(Dish dish);

  @Mapping(source = "categoryId", target = "category.categoryId")
  Dish dishCreateDtoToDish(DishCreateDto dishCreateDto);
  void updateDishFromDishUpdateDTO(DishUpdateDTO dishUpdateDTO, @MappingTarget Dish dish);
}
