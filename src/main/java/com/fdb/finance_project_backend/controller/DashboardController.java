package com.fdb.finance_project_backend.controller;

import com.fdb.finance_project_backend.dto.CategoryDTO;
import com.fdb.finance_project_backend.dto.DashboardDTO;
import com.fdb.finance_project_backend.dto.MonthlyDTO;
import com.fdb.finance_project_backend.entity.FinancialRecord;
import com.fdb.finance_project_backend.service.FinancialRecordService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/analyst/dashboard")
@RequiredArgsConstructor
@Tag(name = "Dashboard APIs", description = "All dashboard operations")
public class DashboardController {
	private final FinancialRecordService financialRecordService;

	@GetMapping("/summery")
	public DashboardDTO getSummery(){
		return financialRecordService.getDashboardSummery();
	}

	@GetMapping("/category")
	public List<CategoryDTO> getCategorySummary() {
		return financialRecordService.getCategorySummary();
	}

	@GetMapping("/recent")
	public List<FinancialRecord> getRecent() {
		return financialRecordService.getRecentTransactions();
	}

	@GetMapping("/monthly")
	public List<MonthlyDTO> getMonthlyTrends() {
		return financialRecordService.getMonthlyTrends();
	}
}
