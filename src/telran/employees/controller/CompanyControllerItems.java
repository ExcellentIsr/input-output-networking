package telran.employees.controller;

import java.time.LocalDate;
import java.util.*;

import telran.employees.*;
import telran.view.*;

public class CompanyControllerItems {
	private static final int MIN_SALARY = 5_000;
	private static final int MAX_SALARY = 50_000;
	static private Company company;
	static private HashSet<String> departments;
	
	private CompanyControllerItems() {
		
	}
	static public Item[] getCompanyItems(Company company, String[] departments) {
		CompanyControllerItems.company = company;
		CompanyControllerItems.departments = new HashSet<>(Arrays.asList(departments));
		return getItems();
	}
	private static Item[] getItems() {
		return new Item[] {
			getAdminMenu(), getUserMenu() 	
		};
	}
	
	private static Item getUserMenu() {
		return new Menu("User action", 
				Item.of("Get employee", CompanyControllerItems::getEmployee),
				Item.of("Get all employee", CompanyControllerItems::getAllEmployees), 
				Item.of("Get employees by salary", CompanyControllerItems::getEmployeesBySalary),
				Item.of("Get employees by month", CompanyControllerItems::getEmployeesByMonth), 
				Item.of("Get employees by department", CompanyControllerItems::getEmployeesByDepartment),
				Item.exit());
	}
	private static Item getAdminMenu() {
		return new Menu("Admin actiom", 
				Item.of("Add employee", CompanyControllerItems::addEmployee),
				Item.of("Remove employee", CompanyControllerItems::removeEmployee),
				Item.exit());
	}
	
	private static Long getId(InputOutput io, boolean exist) {
		long id = io.readLong("Enter employee ID", "Wrong ID", 1, Long.MAX_VALUE);
		Employee empl = company.getEmployee(id);
		
		return (empl == null && !exist) || (empl != null && exist) ? id : null;
	}
	
	private static String getDepartment(InputOutput io) {
		String department = io.readStringOptions("Enter department " + departments, "Wrong department", departments);
		
		return department;
	}
	

	private static int getSalary(InputOutput io) {
		return io.readInt("Enter salary", "Wrong salary", MIN_SALARY, MAX_SALARY);
	}
	
	private static void addEmployee(InputOutput io) {
		Long id = getId(io, false);
		
		if(id == null) {
			io.writeLine(String.format("Employee with id %s already exists", id));
		} else {
			String name = io.readString("Enter employee's name");
			LocalDate birthDate = io.readDate("Enter birth date of emploee's", "Wrong date");
			String department = getDepartment(io);
			int salary = getSalary(io);
			Employee empl = new Employee(id, name , birthDate , department, salary );
			io.writeLine(company.addEmployee(empl) ? "Employee added" : "Employee already exists");
		}
	}
	
	private static void removeEmployee(InputOutput io) {
		Long id = getId(io, true);
		
		if(id == null) {
			io.writeLine(String.format("Employee with id %s already not exist", id));
		} else {
			Employee empl = company.removeEmployee(id);
			io.writeLine(empl != null ? "Removed" : "Employee is not found");
		}
	}
	
	private static void getAllEmployees(InputOutput io) {
		displayResult(company.getAllEmployees(), io);
	}
	
	private static void getEmployee(InputOutput io) {
		Long id = getId(io, true);
		io.writeLine(id != null ? company.getEmployee(id) : "Employee is not found");
	}
	
	private static void getEmployeesBySalary(InputOutput io) {
		int salaryMin = io.readInt("Enter salary from", "Wrong salary", MIN_SALARY, MAX_SALARY);
		int salaryMax = io.readInt("Enter salary to", "Wrong salary", salaryMin, MAX_SALARY);
		
		displayResult(company.getEmployeesBySalary(salaryMin, salaryMax), io);
	}
	
	private static void getEmployeesByMonth(InputOutput io) {
		int month = io.readInt("Enter month number", "Wrong month", 1, 12);
		displayResult(company.getEmployeesByMonthBirth(month), io);
	}
	
	private static void getEmployeesByDepartment(InputOutput io) {
		String department = getDepartment(io);
		displayResult(company.getEmployeesByDepartment(department), io);
	}
	
	private static void displayResult(List<Employee> employees, InputOutput io) {
		employees.forEach(io::writeLine);
	}
}
