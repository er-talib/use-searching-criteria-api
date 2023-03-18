package com.criteria.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.criteria.entities.Company;
import com.criteria.entities.Employee;
import com.criteria.operation.CompanySepecification;
import com.criteria.repositories.CompanyRepository;
import com.criteria.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository companyRepository;

	@Override
	public void addCompanyDetails(Company company) {
		company.setEstablishedDate(new Date());
		List<Employee> employee = company.getEmployee().stream().map(e -> {
			e.setJoiningDate(new Date());
			e.setCompany(company);
			return e;
		}).collect(Collectors.toList());
		company.setEmployee(employee);
		this.companyRepository.save(company);
	}

	@Override
	public Company getCompanyDetailById(Integer companyId) {
		return companyRepository.findById(companyId).get();
	}

	@Override
	public List<Company> getAllCompanyDetails() {
		return this.companyRepository.findAll();
	}

	@Override
	public Company updateCompanyDetails(Company company, Integer companyId) {
		company.setCompanyId(companyId);
		List<Employee> employee = company.getEmployee();
		for (Employee emp : employee) {
			Integer employeeId = emp.getEmployeeId();
			emp.setEmployeeId(employeeId);
		}
		company.setEmployee(employee);
		return this.companyRepository.save(company);
	}

	@Override
	public String deleteCompanyDetailsById(Integer companyId) {
		this.companyRepository.deleteById(companyId);
		return "Deleted sussessfully...!!!";
	}

	public List<Company> getFilterDetails(CompanySepecification spec) {
		List<Company> searchCompanyDetails = this.companyRepository.findAll(spec);
        return searchCompanyDetails;
	}

}
