package com.criteria.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.criteria.entities.Company;
@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> , JpaSpecificationExecutor<Company>{

	
}
