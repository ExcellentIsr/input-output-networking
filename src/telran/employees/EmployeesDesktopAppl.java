package telran.employees;

import java.util.ArrayList;
import java.util.Arrays;

import telran.employees.controller.CompanyControllerItems;
import telran.view.*;

public class EmployeesDesktopAppl {

	private static final String FILE_PATH = "employees.data";

	public static void main(String[] args) {
		InputOutput io = new StandartInputOutput();
		Company company = new CompanyImpl();
		
		company.restore(FILE_PATH);
		Item[] companyItems = CompanyControllerItems.getCompanyItems(company, new String[] {"QA", "Development", "Audit", "Managment", "Accounting"});
		ArrayList<Item> items = new ArrayList<>(Arrays.asList(companyItems));
		items.add(Item.of("Exit & Save", io1 -> company.save(FILE_PATH), true));
		Menu menu = new Menu("Company Application",  items);
		menu.perform(io);
	}
}
