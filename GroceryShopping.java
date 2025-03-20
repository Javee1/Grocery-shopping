package com.Java_Programs;

import java.util.Scanner;

public class GroceryShopping {

	public static void main(String[] args) {
		String[] products = { "Apple", "Banana", "Milk", "Eggs", "Rice" };
		double[] prices = { 1.5, 0.5, 2.0, 3.0, 1.2 };
		Scanner scanner = new Scanner(System.in);
		double totalCost = 0.0;
		StringBuilder cart = new StringBuilder();

		while (true) {
			System.out.println("\nAvailable products:");
			for (int i = 0; i < products.length; i++) {
				System.out.println((i + 1) + ". " + products[i] + " - $" + prices[i]);
			}

			System.out.print("\nEnter the product number to add to your cart (1-5) or 0 to exit: ");
			int choice = scanner.nextInt();

			if (choice == 0) {
				break;
			} else if (choice < 1 || choice > 5) {
				System.out.println("Invalid choice. Please choose a number between 1 and 5.");
				continue;
			}

			System.out.print("Enter quantity for " + products[choice - 1] + ": ");
			int quantity = scanner.nextInt();

			double cost = prices[choice - 1] * quantity;
			totalCost += cost;
			
			 cart.append(quantity).append(" x ").append(products[choice - 1])
             .append(" - $").append(cost).append("\n");

			System.out.println("Added " + quantity + " " + products[choice - 1]
					+ "(s) to your cart. Total cost so far: $" + totalCost);
		}
		
		System.out.println("\nYour cart contains:");
        System.out.println(cart);
		System.out.println("\nYour total cost is: $" + totalCost);
		System.out.println("Thank you for shopping with us!");

	}
}
