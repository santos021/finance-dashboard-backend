package com.fdb.finance_project_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ValidationDTO {
	@NotNull(message = "Amount is required")
	private Double amount;

	@NotBlank(message = "Category is required")
	private String category;

	@NotBlank(message = "Type is required")
	private String type;

	@NotBlank(message = "Date is required")
	private String date;

	private String description;
}
