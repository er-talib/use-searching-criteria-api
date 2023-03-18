package com.criteria.entities;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "company_details")
public class Company {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer companyId ;
	private String companyName ;
	private String companyAddress ;
	private String chairmanName ;
	private String typeOfCompany ;
	private String stateName ;
	private String districtName ;
	private String companyCode ;
	@Temporal(TemporalType.TIMESTAMP)
	private Date establishedDate ;
	private String companyStatus ;
	@OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY , mappedBy = "company")
	@JsonManagedReference
	private List<Employee> employee ;

}
