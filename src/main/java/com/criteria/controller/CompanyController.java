package com.criteria.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.criteria.entities.Company;
import com.criteria.operation.CompanySepecification;
import com.criteria.operation.SearchingCriteria;
import com.criteria.operation.SearchingOperation;
import com.criteria.service.CompanyService;

@RestController
@RequestMapping("/company")
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	@PostMapping("/add")
	public ResponseEntity<?> addCompanyDetail(@RequestBody Company company) {
		this.companyService.addCompanyDetails(company);
		return ResponseEntity.status(HttpStatus.CREATED).body("Company details add successfully.!!!");
	}

	@GetMapping("/get/{companyId}")
	public ResponseEntity<?> getCompanyDetailsById(@PathVariable Integer companyId) {
		Company companyDetailById = this.companyService.getCompanyDetailById(companyId);
		return ResponseEntity.ok(companyDetailById);
	}

	@GetMapping("/getAll")
	public ResponseEntity<?> getAllCompanyDetails() {
		List<Company> allCompanyDetails = this.companyService.getAllCompanyDetails();
		return ResponseEntity.ok(allCompanyDetails);
	}

	@PutMapping("/update/{companyId}")
	public ResponseEntity<?> updateCompanyDetail(@RequestBody Company company, @PathVariable Integer companyId) {
		Company updatedCompanyDetails = this.companyService.updateCompanyDetails(company, companyId);
		return ResponseEntity.ok(updatedCompanyDetails);
	}

	@DeleteMapping("/delete/{companyId}")
	public ResponseEntity<?> deleteCompantDetail(@PathVariable Integer companyId) {
		String companyDetails = this.companyService.deleteCompanyDetailsById(companyId);
		return ResponseEntity.ok(companyDetails);
	}

	@GetMapping("/search")
	public ResponseEntity<?> search(@RequestParam(name = "chairmanName", required = false) String chairmanName,
			@RequestParam(name = "stateName", required = false) String stateName,
			@RequestParam(name = "districtName", required = false) String districtName,
			@RequestParam(name = "typeOfCompany", required = false) String typeOfCompany,
			@RequestParam(name = "companyStatus", required = false) String[] companyStatus) {
		CompanySepecification spec = new CompanySepecification();

		if (stateName != null) {
			spec.add(new SearchingCriteria("stateName", stateName, SearchingOperation.EQUAL, null));
		}
		if (districtName != null) {
			spec.add(new SearchingCriteria("stateName", districtName, SearchingOperation.EQUAL, null));
		}
		if (typeOfCompany != null) {
			spec.add(new SearchingCriteria("typeOfCompany", "Manegment", SearchingOperation.NOT_EQUAL, null));
		}
		if (companyStatus != null && companyStatus.length > 0) {
			spec.add(new SearchingCriteria("companyStatus", null, SearchingOperation.EQUAL,
					Arrays.asList(companyStatus)));
		}
		if (chairmanName != null) {
			spec.add(new SearchingCriteria("chairmanName", chairmanName, SearchingOperation.EQUAL, null));
		}

		List<Company> searchingCompanyDetails = companyService.getFilterDetails(spec);

		return ResponseEntity.ok(searchingCompanyDetails);

	}

}
