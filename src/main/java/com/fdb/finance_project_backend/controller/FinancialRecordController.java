package com.fdb.finance_project_backend.controller;

import com.fdb.finance_project_backend.dto.ValidationDTO;
import com.fdb.finance_project_backend.entity.FinancialRecord;
import com.fdb.finance_project_backend.service.FinancialRecordService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/records")
@AllArgsConstructor
@Tag(name = "Financial Record Management APIs", description = "All CRUD and Filtering Operations")
public class FinancialRecordController {
	private final FinancialRecordService financialRecordService;

	@PostMapping
	public FinancialRecord createForm(@Valid @RequestBody ValidationDTO dto) {
		return financialRecordService.createFromDTO(dto);
	}

	@GetMapping
	public List<FinancialRecord> getAll(){
		return financialRecordService.getAllRecords();
	}

	@GetMapping("/income")
	public Double getIncome(){
		return financialRecordService.getTotalIncome();
	}

	@GetMapping("/expense")
	public Double getExpense(){
		return financialRecordService.getTotalExpense();
	}

	@PutMapping("/{id}")
	public FinancialRecord updateRecord(@PathVariable Long id, @RequestBody ValidationDTO dto){
		return financialRecordService.updateRecord(id, dto);
	}

	@DeleteMapping("/{id}")
	public String deleteRecord(@PathVariable Long id) {
		financialRecordService.deleteRecord(id);
		return "Record deleted successfully";
	}

	@GetMapping("/filter")
	public List<FinancialRecord> filterRecords(
			@RequestParam(required = false) String type,
			@RequestParam(required = false) String category,
			@RequestParam(required = false) String date) {

		return financialRecordService.filterRecords(type, category, date);
	}

	@GetMapping("/search")
	public List<FinancialRecord> search(@RequestParam String keyword) {
		return financialRecordService.searchByCategory(keyword);
	}

}
