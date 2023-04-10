package telran.employees;

import java.io.Serializable;
import java.util.List;

public interface Company extends Iterable<Employee>, Serializable {
	boolean addEmployee(Employee empl);// return true if added (No two employees with the same ID)

	Employee removeEmployee(long id); // return reference to removed Employee or null

	List<Employee> getAllEmployees();

	List<Employee> getEmployeesByMonthBirth(int month); // employees born at a given month

	List<Employee> getEmployeesBySalary(int salaryFrom, int salaryTo); // employees with salary in a given range

	List<Employee> getEmployeesByDepartment(String department);

	Employee getEmployee(long id);

	void save(String pathName); // save all employee objects

	void restore(String pathName); // restore all employee objects
}