package com.fdb.finance_project_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DashboardDTO {
	private Integer totalIncome;
	private Integer totalExpense;
	private Integer netBalance;
}
