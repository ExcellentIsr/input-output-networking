package telran.view.test;
import telran.view.*;

public class CalculatorApplication {
	public static void main(String[] args) {
		StandartInputOutput io = new StandartInputOutput();
		Menu menu = new Menu("Calculator", NumbersOperationsMenu.getNumbersOperationMenu(), DatesOperationsMenu.getDatesOperationsMenu(), Item.exit());
		menu.perform(io);
	}
}
