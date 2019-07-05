import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Lab9 {

	public static void main(String[] args) {

		Map<String, Double> items = new HashMap<>();
		ArrayList<String> itemsordered = new ArrayList<>();
		ArrayList<Double> prices = new ArrayList<>();
		ArrayList<Integer> quantity = new ArrayList<>();

		Scanner scnr = new Scanner(System.in);
		char userChoice = 'Y';

		items.put("apple", 0.99);
		items.put("banana", 0.59);
		items.put("cauliflower", 1.59);
		items.put("dragonfruit", 2.19);
		items.put("Elderberry", 1.79);
		items.put("figs", 2.09);
		items.put("grapefruit", 1.99);
		items.put("honeydew", 3.49);

		System.out.println("Welcome to Guenther's Market");
		System.out.println();

		do {
			System.out.printf("%-15s %s\n", "Item", "Price");
			System.out.printf("%-10s\n", "==========================");

			for (Map.Entry<String, Double> me : items.entrySet()) {

				System.out.printf("%-15s %s\n", me.getKey(), "$" + me.getValue());
			}

			System.out.println("What item would you like to order?");
			String userOrder = scnr.next();
			if (!items.containsKey(userOrder)) {
				System.out.println("Sorry we don't have those.  Please try again.");
				scnr.nextLine();
				continue;
			} else {
				System.out.println("How many do you want? ");
				int count = scnr.nextInt();
				if (!itemsordered.contains(userOrder)) {
					itemsordered.add(userOrder);
					prices.add(items.get(userOrder));
					quantity.add(count);
				} else {
					int position = itemsordered.indexOf(userOrder);
					int value = quantity.get(position);
					value += count;
					quantity.add(position, value);
				}

				System.out.println("Adding " + count + " " + userOrder + "(s) to cart at $" + items.get(userOrder));
			}
			System.out.println("Would you like to order anything else (y/n)?");
			userChoice = scnr.next().charAt(0);
		} while ((userChoice == 'Y') || (userChoice == 'y'));

		System.out.println("Thanks for your order!");
		System.out.println("Here's what you got: ");
		for (

				int i = 0; i < itemsordered.size(); i++) {
			System.out.printf("%-15s %s\n", itemsordered.get(i), prices.get(i) * quantity.get(i));
		}
		System.out.println("Average price per item in order was " + calculateAvgCost(prices));
		System.out.println("Index of the highest cost item " + calculateIndexMax(prices));
		System.out.println("Index of the lowest cost item " + calculateIndexMin(prices));
		System.out.println("The most expensive item ordered is " + calculateMaxExpensive(prices, itemsordered));
		System.out.println("The least expensive item ordered is " + calculateLeastExpensive(prices, itemsordered));

		scnr.close();
	}

	public static double calculateAvgCost(ArrayList<Double> prices) {

		double sum = 0;
		for (int i = 0; i < prices.size(); i++) {
			sum += prices.get(i);
		}
		return sum / prices.size();
	}

	public static int calculateIndexMax(ArrayList<Double> prices) {

		double max = Collections.max(prices);
		return prices.indexOf(max);
	}

	public static int calculateIndexMin(ArrayList<Double> prices) {

		double min = Collections.min(prices);
		return prices.indexOf(min);
	}

	public static String calculateMaxExpensive(ArrayList<Double> prices, ArrayList<String> itemsordered) {

		double max = Collections.max(prices);
		int pos = prices.indexOf(max);
		return itemsordered.get(pos);
	}

	public static String calculateLeastExpensive(ArrayList<Double> prices, ArrayList<String> itemsordered) {

		double min = Collections.min(prices);
		int pos = prices.indexOf(min);
		return itemsordered.get(pos);
	}
}
