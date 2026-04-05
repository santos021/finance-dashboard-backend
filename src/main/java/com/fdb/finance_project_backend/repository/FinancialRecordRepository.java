package com.fdb.finance_project_backend.repository;

import com.fdb.finance_project_backend.entity.FinancialRecord;
import com.fdb.finance_project_backend.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface FinancialRecordRepository extends JpaRepository<FinancialRecord, Long> {
	List<FinancialRecord> findByType(Type type);

	List<FinancialRecord> findByCategory(String category);

	List<FinancialRecord> findByDate(LocalDate date);

	@Query("SELECT SUM(f.amount) FROM FinancialRecord f WHERE f.type= :type")
	Double sumByType(Type type);

	@Query("SELECT f.category, SUM(f.amount) FROM FinancialRecord f GROUP BY f.category")
	List<Object[]> getCategorySummary();

	List<FinancialRecord> findTop5ByOrderByDateDesc();

	@Query("SELECT MONTH(f.date), SUM(f.amount) FROM FinancialRecord f GROUP BY MONTH(f.date)")
	List<Object[]> getMonthlyTrends();

	List<FinancialRecord> findByCategoryContainingIgnoreCase(String category);
}
