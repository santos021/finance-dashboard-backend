package com.fdb.finance_project_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryDTO {
	private String category;
	private Integer total;
}