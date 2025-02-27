package com.orderhere.dish.DishService.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PagingDto<E> {
  private E data;
  private long totalItems;
  private int totalPages;
  private int currentPage;
}
