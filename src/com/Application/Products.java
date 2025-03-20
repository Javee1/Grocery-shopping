package com.Application;
import java.util.Scanner;

public class Products {

        public static void main(String[] args) {
            // Defining products in different categories
            String[] clothes = { "T-shirt", "Jeans", "Jacket" };
            double[] clothesPrices = { 20.0, 40.0, 50.0 };
            double clothesGST = 0.12;  // 12% GST on clothes

            String[] electronics = { "Laptop", "Smartphone", "Headphones" };
            double[] electronicsPrices = { 500.0, 300.0, 50.0 };
            double electronicsGST = 0.18;  // 18% GST on electronics

            String[] plasticItems = { "Water Bottle", "Plastic Chair", "Tupperware" };
            double[] plasticPrices = { 5.0, 10.0, 3.0 };
            double plasticGST = 0.05;  // 5% GST on plastic items

            String[] steelItems = { "Steel Plate", "Cutlery Set", "Water Tank" };
            double[] steelPrices = { 30.0, 25.0, 100.0 };
            double steelGST = 0.10;  // 10% GST on steel items (updated)

            Scanner scanner = new Scanner(System.in);
            double totalCost = 0.0; // Total cost after GST
            StringBuilder cart = new StringBuilder(); // To store cart items

            while (true) {
                // Display categories to choose from
                System.out.println("\nSelect a category to view products:");
                System.out.println("1. Clothes");
                System.out.println("2. Electronics");
                System.out.println("3. Plastic Items");
                System.out.println("4. Steel Items");
                System.out.println("0. Exit");

                System.out.print("\nEnter your choice (0-4): ");
                int categoryChoice = scanner.nextInt();

                if (categoryChoice == 0) {
                    break;  // Exit the loop
                }

                String[] selectedCategory = null;
                double[] selectedPrices = null;
                double selectedGST = 0.0;
                String categoryName = "";

                // Determine the selected category
                switch (categoryChoice) {
                    case 1:
                        selectedCategory = clothes;
                        selectedPrices = clothesPrices;
                        selectedGST = clothesGST;
                        categoryName = "Clothes";
                        break;
                    case 2:
                        selectedCategory = electronics;
                        selectedPrices = electronicsPrices;
                        selectedGST = electronicsGST;
                        categoryName = "Electronics";
                        break;
                    case 3:
                        selectedCategory = plasticItems;
                        selectedPrices = plasticPrices;
                        selectedGST = plasticGST;
                        categoryName = "Plastic Items";
                        break;
                    case 4:
                        selectedCategory = steelItems;
                        selectedPrices = steelPrices;
                        selectedGST = steelGST;
                        categoryName = "Steel Items";
                        break;
                    default:
                        System.out.println("Invalid choice! Please try again.");
                        continue;
                }

                // Display the products in the selected category
                System.out.println("\nAvailable " + categoryName + ":");
                for (int i = 0; i < selectedCategory.length; i++) {
                    System.out.println((i + 1) + ". " + selectedCategory[i] + " - $" + selectedPrices[i]);
                }

                // Select a product
                System.out.print("\nEnter the product number to add to your cart (1-" + selectedCategory.length + "): ");
                int productChoice = scanner.nextInt();

                if (productChoice < 1 || productChoice > selectedCategory.length) {
                    System.out.println("Invalid product choice. Please select a valid product.");
                    continue;
                }

                // Get the quantity of the selected product
                System.out.print("Enter quantity for " + selectedCategory[productChoice - 1] + ": ");
                int quantity = scanner.nextInt();

                // Calculate the cost with GST
                double productCost = selectedPrices[productChoice - 1] * quantity;
                double gstAmount = productCost * selectedGST;
                double totalProductCost = productCost + gstAmount;
                totalCost += totalProductCost;

                cart.append(quantity).append(" x ").append(selectedCategory[productChoice - 1])
                        .append(" - $").append(productCost).append(" (GST: $").append(gstAmount)
                        .append(") Total: $").append(totalProductCost).append("\n");

                System.out.println("Added " + quantity + " " + selectedCategory[productChoice - 1] + "(s) to your cart.");
                System.out.println("Product cost: $" + productCost + " + GST: $" + gstAmount + " = Total: $" + totalProductCost);
            }

            // When exiting the loop, show the cart and the final total cost
            System.out.println("\nYour cart contains:");
            System.out.println(cart);
            System.out.println("Your total cost after GST is: $" + totalCost);
            System.out.println("Thank you for shopping with us!");

            scanner.close();
        }
    }



