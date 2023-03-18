package com.criteria.service;

import java.util.List;

import com.criteria.entities.Company;
import com.criteria.operation.CompanySepecification;

public interface CompanyService {
	
	public void addCompanyDetails(Company company);
	public Company getCompanyDetailById(Integer companyId);
	public List<Company> getAllCompanyDetails();
	public Company updateCompanyDetails(Company company,Integer companyId);
	public String deleteCompanyDetailsById(Integer companyId);
	public List<Company> getFilterDetails(CompanySepecification spec);

}
