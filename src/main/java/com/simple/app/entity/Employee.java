package com.simple.app.entity;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Component;

@Entity
public class Employee  implements Comparable<Employee>{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int eid;

	@Column(name = "employee_name",unique = true,length = 10)
	@Pattern(regexp = "^[a-zA-Z\\\\s]*$")
	private String employeeName;

	private float employeeSalary;

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public float getEmployeeSalary() {
		return employeeSalary;
	}

	public void setEmployeeSalary(float employeeSalary) {
		this.employeeSalary = employeeSalary;
	}

	public Employee() {
	}

	@Override
	public String toString() {
		return "Employee [eid=" + eid + ", employeeName=" + employeeName + ", employeeSalary=" + employeeSalary + "]";
	}

	@Override
	public int compareTo(Employee o) {
		
		return this.eid>o.getEid()?1:-1;
	}
}
