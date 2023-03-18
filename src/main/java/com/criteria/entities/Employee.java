package com.criteria.entities;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer employeeId ;
	private String employeeName ;
	private String employeeAddres ;
	private Integer employeeSalary ;
	@Temporal(TemporalType.TIMESTAMP)
	private Date joiningDate ;
	private String disignation ;
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER )
	@JoinColumn(name = "fk_companyId")
	@JsonBackReference
    private Company company ;

}
