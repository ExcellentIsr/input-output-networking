package telran.view.test;
import java.time.LocalDate;

import telran.view.*;
public class DatesOperationsMenu {
	public static Item getDatesOperationsMenu() {
		return new Menu ("Dates Operations", new Item[] {
				Item.of("Plus days", DatesOperationsMenu::plusDays),
				Item.of("Minus days", DatesOperationsMenu::minusDays),
				Item.exit(),
		});
	}
	
	static void daysOperation(InputOutput io, boolean flag) {
		LocalDate date = io.readDate("Enter date", "Wrong date");
		int days = io.readInt("Enter amount of days", "Wrong date", 1, Integer.MAX_VALUE);
		
		if(!flag) {
			days = -days;
		}
		
		io.writeLine(date.plusDays(days));
	}
	
	static void plusDays(InputOutput io) {
		daysOperation(io, true);
	}
	
	static void minusDays(InputOutput io) {
		daysOperation(io, false);
	}
}
