package com.fdb.finance_project_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MonthlyDTO {
	private Integer month;
	private Integer total;
}
