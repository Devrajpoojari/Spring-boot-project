package com.simple.app.controller;

import java.lang.module.ResolutionException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.simple.app.ResponseType;
import com.simple.app.entity.Employee;
import com.simple.app.exceptions.ResourceNotFoundException;
import com.simple.app.repo.EmpoyeeRepository;

//@Controller
@RestController
@RequestMapping("/api/employee")
public class WebAppController {

//	List<Employee> empList = new ArrayList<Employee>(); // database

	@Autowired
	private EmpoyeeRepository empoyeeRepository;

	final static Logger logger = LoggerFactory.getLogger(WebAppController.class);

	@GetMapping({ "/welcome" })
	public String welcome() { // api
		return "demo";
	}

//	// @PostMapping("/add")
//	@RequestMapping(value = "/add", method = RequestMethod.POST)
//	public Employee addEmployee(@RequestBody Employee employee) {
//		System.out.println(employee);
//		empList.add(employee);
//		System.out.println("Employee Added successfully");
//		return employee;
//	}

	// @PostMapping("/add")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<ResponseType> addEmployee(@RequestBody @Valid Employee employee) throws Exception {
		System.out.println(employee);
//		empList.add(employee);
		Employee emp = null;
		if (employee.getEmployeeSalary() >= 1000) {
			emp = empoyeeRepository.save(employee);
		} else {
			throw new Exception("Employee is not eligible to do work");
		}

		logger.info("Employee Added successfully");

		return new ResponseEntity<ResponseType>(new ResponseType("Employee added successfully", emp),
				HttpStatus.CREATED);
	}

//	@GetMapping("/get-all-employees")
	@RequestMapping(value = "/get-all-employees", method = RequestMethod.GET)
	public List<Employee> getAll() {
		logger.info("Getting all the employees from the database");
		return empoyeeRepository.findAll();
	}

	@PutMapping("/update/{id}")
//	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@RequestBody Employee employee, @PathVariable int id) throws Exception {
		Employee e = null;
//		for (int i = 0; i < empList.size(); i++) {
//			if (empList.get(i).getEid() == id) {
//				empList.remove(i);
//				empList.add(employee);
//				e = empList.get(i);
//				logger.info("Updating the employee details");
//				return ResponseEntity.ok(e);
//			}
//
//		}
//		logger.error("Update is not possible for this employee");
//		throw new Exception("Employee not exists with id : " + id);
		Optional<Employee> emp = empoyeeRepository.findById(id);
		if (!emp.isEmpty()) {
			empoyeeRepository.delete(emp.get());
			e = empoyeeRepository.save(employee);
		} else {
			throw new Exception("Employee not exists with id : " + id);
		}
		return ResponseEntity.ok(e);

	}

	// delete employee object
//	@DeleteMapping("/delete/{id}")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable int id) throws Exception {

//		for (int i = 0; i < empList.size(); i++) {
//			if (empList.get(i).getEid() == id) {
//				empList.remove(i);
//				return "Employee object deleted";
//			}
//		}

		Optional<Employee> emp = empoyeeRepository.findById(id);
		if (!emp.isEmpty()) {
			empoyeeRepository.delete(emp.get());
			return "Employee deleted successfully with id :" + id;
		}

		throw new Exception("Employee not exists with id : " + id);

	}

	@GetMapping("/get-list")
	public List<?> getAllEmp() {

		return empoyeeRepository.findAll().stream()
				.filter(emp -> emp.getEmployeeName().startsWith("R") || emp.getEmployeeName().startsWith("r")).sorted()
				.collect(Collectors.toList());
	}

	@PostMapping("/add-list")
	public List<?> addList(@RequestBody List<Employee> list) {
//		empList.addAll(list);

		return empoyeeRepository.saveAll(list);
	}

	@GetMapping("/get-emp-by-name/{name}")
	public ResponseEntity<?> getEmployeeByName(@PathVariable String name) throws ResourceNotFoundException {
		Employee emp = empoyeeRepository.getEmpoyeeByName(name);
		if (emp == null) {
			throw new ResourceNotFoundException("Employee Not exists by : " + name);
		} else {
			return new ResponseEntity<>(emp, HttpStatus.OK);
		}

	}

}
