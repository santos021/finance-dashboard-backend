package com.fdb.finance_project_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FinancialRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Double amount;

	@Enumerated(EnumType.STRING)
	private Type type;

	private String category;
	private LocalDate date;
	private String description;

	@ManyToOne
	private User createdBy;
}
