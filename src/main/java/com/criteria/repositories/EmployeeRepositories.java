package com.criteria.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.criteria.entities.Employee;
@Repository
public interface EmployeeRepositories extends JpaRepository<Employee, Integer> {

}
