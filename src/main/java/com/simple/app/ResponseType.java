package com.simple.app;

import com.simple.app.entity.Employee;

public class ResponseType {

	private String response;

	private Employee employee;

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public ResponseType(String response, Employee employee) {
		this.response = response;
		this.employee = employee;
	}

	public ResponseType() {
	}

}
