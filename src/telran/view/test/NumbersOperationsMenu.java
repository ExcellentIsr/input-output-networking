package telran.view.test;

import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;

public class NumbersOperationsMenu {
	public static Item getNumbersOperationMenu() {
		return new Menu("Numbers Operations", new Item[] { 
				Item.of("Add two numbers", NumbersOperationsMenu::sum),
				Item.of("Subtruct two numbers", NumbersOperationsMenu::subtruct),
				Item.of("Multiply two numbers", NumbersOperationsMenu::multiply),
				Item.of("Divide two numbers", NumbersOperationsMenu::divide), 
				Item.exit(), 
			});
	}
	
	static void sum(InputOutput io) {
		double[] numbers = getTwoNUmbers(io);
		
		io.writeLine(numbers[0] + numbers[1]);
	}
	
	static void subtruct(InputOutput io) {
		double[] numbers = getTwoNUmbers(io);
		
		io.writeLine(numbers[0] - numbers[1]);
	}
	
	static void multiply(InputOutput io) {
		double[] numbers = getTwoNUmbers(io);
		
		io.writeLine(numbers[0] * numbers[1]);
	}
	
	static void divide(InputOutput io) {
		double[] numbers = getTwoNUmbers(io);
		
		io.writeLine(numbers[0] / numbers[1]);
	}

	private static double[] getTwoNUmbers(InputOutput io) {
		double firstNumber = io.readNumber("Enter first number", "Wrong number", -Double.MAX_VALUE, Double.MAX_VALUE);
		double secondNumber = io.readNumber("Enter second number", "Wrong number", -Double.MAX_VALUE, Double.MAX_VALUE);
		
		return new double[] {firstNumber, secondNumber};
	}
}
