package com.fdb.finance_project_backend.service;

import com.fdb.finance_project_backend.dto.CategoryDTO;
import com.fdb.finance_project_backend.dto.DashboardDTO;
import com.fdb.finance_project_backend.dto.MonthlyDTO;
import com.fdb.finance_project_backend.dto.ValidationDTO;
import com.fdb.finance_project_backend.entity.FinancialRecord;
import com.fdb.finance_project_backend.entity.Type;
import com.fdb.finance_project_backend.entity.User;
import com.fdb.finance_project_backend.repository.FinancialRecordRepository;
import com.fdb.finance_project_backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class FinancialRecordService {
	private final FinancialRecordRepository financialRecordRepository;

	private final UserRepository userRepository;

	public FinancialRecord createRecord(FinancialRecord record){
		return financialRecordRepository.save(record);
	}

	public List<FinancialRecord> getAllRecords(){
		return financialRecordRepository.findAll();
	}

	public Double getTotalIncome(){
		return financialRecordRepository.sumByType(Type.INCOME);
	}

	public Double getTotalExpense(){
		return financialRecordRepository.sumByType(Type.EXPENSE);
	}

	public DashboardDTO getDashboardSummery(){
		Double income = financialRecordRepository.sumByType(Type.INCOME);
		Double expense = financialRecordRepository.sumByType(Type.EXPENSE);

		if (income == null) income = 0.0;
		if (expense == null) expense = 0.0;

		return new DashboardDTO(
				income.intValue(),
				expense.intValue(),
				(int) (income - expense)
		);
	}

	public List<CategoryDTO> getCategorySummary() {

		List<Object[]> data = financialRecordRepository.getCategorySummary();

		return data.stream()
				.map(obj -> new CategoryDTO(
						(String) obj[0],
						((Double) obj[1]).intValue()
				)).toList();
	}

	public List<FinancialRecord> getRecentTransactions() {
		return financialRecordRepository.findTop5ByOrderByDateDesc();
	}

	public List<MonthlyDTO> getMonthlyTrends() {
		List<Object[]> data = financialRecordRepository.getMonthlyTrends();

		return data.stream()
				.map(obj -> new MonthlyDTO(
						(Integer) obj[0],
						((Double) obj[1]).intValue()
				)).toList();
	}

	private String getCurrentUserEmail() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth.getName(); // email
	}

	public FinancialRecord createFromDTO(ValidationDTO dto) {

		String email = getCurrentUserEmail();

		User user = userRepository.findByEmail(email)
				.orElseThrow(()-> new RuntimeException("User not found"));

		FinancialRecord record = new FinancialRecord();

		record.setAmount(dto.getAmount());
		record.setCategory(dto.getCategory());
		record.setDescription(dto.getDescription());

		// Convert String → Enum
		record.setType(Type.valueOf(dto.getType()));

		// Convert String → LocalDate
		record.setDate(LocalDate.parse(dto.getDate()));

		record.setCreatedBy(user);

		return financialRecordRepository.save(record);
	}

	public FinancialRecord updateRecord(Long id, ValidationDTO dto) {

		FinancialRecord record = financialRecordRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Record not found"));

		record.setAmount(dto.getAmount());
		record.setCategory(dto.getCategory());
		record.setDescription(dto.getDescription());
		record.setType(Type.valueOf(dto.getType()));
		record.setDate(LocalDate.parse(dto.getDate()));
		record.setCreatedBy(record.getCreatedBy());

		return financialRecordRepository.save(record);
	}

	public void deleteRecord(Long id) {

		FinancialRecord record = financialRecordRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Record not found"));

		financialRecordRepository.delete(record);
	}

	public List<FinancialRecord> filterRecords(String type,
	                                           String category,
	                                           String date) {

		if (type != null) {
			return financialRecordRepository.findByType(Type.valueOf(type));
		}

		if (category != null) {
			return financialRecordRepository.findByCategory(category);
		}

		if (date != null) {
			return financialRecordRepository.findByDate(LocalDate.parse(date));
		}

		return financialRecordRepository.findAll();
	}

	public List<FinancialRecord> searchByCategory(String keyword) {
		return financialRecordRepository.findByCategoryContainingIgnoreCase(keyword);
	}

}
