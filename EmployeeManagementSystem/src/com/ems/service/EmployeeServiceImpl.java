package com.ems.service;


import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import javax.naming.InvalidNameException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ems.dao.EmployeeDao;
import com.ems.dao.EmployeeDaoImpl;
import com.ems.model.Employee;
import com.ems.util.InvalidDobException;
import com.ems.util.InvalidSalaryException;
@Component("service")
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	EmployeeDao dao;
public EmployeeServiceImpl() {
	// TODO Auto-generated constructor stub
	dao=new EmployeeDaoImpl();
}
	@Override
	public boolean validateEmployee(Employee emp) throws Exception {
		// TODO Auto-generated method stub
		String empName=emp.getEmpName();
		LocalDate dob=emp.getDob();
		float salary=emp.getBasicSalary();
		if(empName.length()<4 || empName.length()>15)
			throw new InvalidNameException("Name must be between 4 to 15 characters");
		if(dob.isAfter(LocalDate.of(1995,12,31)) || dob.isBefore(LocalDate.of(1900, 1, 1)))
			throw new InvalidDobException("Please enter dob in range 1-1-1900 to 31-12-1995");
		if(salary>80000 || salary<20000)
			throw new InvalidSalaryException("Salary must be between 20000 to 80000");
		return true;
	}

	@Override
	public String generateId(String empname) {
		// TODO Auto-generated method stub
		String nameChar=empname.substring(0,2).toUpperCase();
		Random rand =new Random();
		int dgt=(int)(rand.nextDouble()*10000);
		return nameChar+dgt;
	}

	@Override
	public boolean save(Employee emp) throws Exception {
		// TODO Auto-generated method stub
		if(validateEmployee(emp)) {
			emp.setEmpId(generateId(emp.getEmpName()));
			return dao.save(emp);
			
		}	
		return false;
	}

	@Override
	public boolean delete(String empId) throws Exception {
		// TODO Auto-generated method stub
		
		return dao.delete(empId);
	}

	@Override
	public boolean update(Employee emp) throws Exception {
		// TODO Auto-generated method stub
		if(validateEmployee(emp))
			return dao.update(emp);
		return false;
	}

	@Override
	public Employee getEmployee(String empId) throws Exception {
		// TODO Auto-generated method stub
		
		return dao.getEmployee(empId);
	}

	@Override
	public List<Employee> getAllEmployees() throws Exception {
		// TODO Auto-generated method stub
		return dao.getAllEmployees();
	}

}
