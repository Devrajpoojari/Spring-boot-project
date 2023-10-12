package com.simple.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.simple.app.entity.Employee;

@Repository
public interface EmpoyeeRepository extends JpaRepository<Employee, Integer> {
	
	@Query(value = "select  e from Employee e where e.employeeName=?1")
	public Employee getEmpoyeeByName(String employeeName);

}
