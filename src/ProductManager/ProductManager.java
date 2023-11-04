/*
ProductManager.java handle all the method needed to manage the product

Methods will be file orientated, every method will do read write operation to store data on a file (.txt)
 */

package ProductManager;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class ProductManager {
    public void createProduct() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Product ID: ");
        String productID = sc.next();
        System.out.println("Enter Product Name: ");
        sc.nextLine(); // Consume the newline character left in the buffer
        String name = sc.nextLine(); // Read the entire line, including spaces

        // Check if name is longer than 5 characters
        while (name.length() < 5) {
            System.out.println("Product Name must be longer than 5 characters");
            System.out.println("Enter Product Name: ");
            name = sc.nextLine();
        }

        System.out.println("Enter Product Price: ");
        double price = sc.nextDouble();
        while (price < 0 || price > 10000) {
            System.out.println("Product Price must be greater than 0");
            System.out.println("Enter Product Price: ");
            price = sc.nextDouble();
        }

        System.out.println("Enter Product Quantity: ");
        int quantity = sc.nextInt();
        while (quantity < 0 || quantity > 10000) {
            System.out.println("Product Quantity must be greater than 0 and smaller than 10000");
            System.out.println("Enter Product Quantity: ");
            quantity = sc.nextInt();
        }

        System.out.println("Enter Product Category: ");
        String category = sc.next();

        Product product = new Product(productID, name, price, quantity, category);

        FileWriter fw = new FileWriter("src/ProductManager/Product.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(product.toString());
        bw.newLine();
        bw.close();
        fw.close();
    }

    // check existing product using name or productid
    public boolean checkProduct() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Product ID or part of Product Name: ");
        String input = sc.nextLine();
        ArrayList<Product> products = readProduct();
        for (Product product : products) {
            if (product.getName().contains(input) || Objects.equals(product.getProductID(), input)) {
                return true;
            }
        }
        return false;
    }
    // search product's information by name or id
    public void searchProduct() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Product ID or part of Product Name: ");
        String input = sc.nextLine();
        ArrayList<Product> products = readProduct();
        for (Product product : products) {
            if (product.getName().contains(input) || Objects.equals(product.getProductID(), input)) {
                System.out.println(product.toString());
            }
        }
    }
    // update product using id or part of name of that product
    public void updateProduct() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Product ID or part of Product Name: ");
        String input = sc.nextLine();
        ArrayList<Product> products = readProduct();
        for (Product product : products) {
            if (product.getName().contains(input) || Objects.equals(product.getProductID(), input)) {
                System.out.println("Enter Product ID: ");
                String ProductID = sc.nextLine();
                System.out.println("Enter Product Name: ");
                String name = sc.nextLine();
                System.out.println("Enter Product Price: ");
                double price = sc.nextDouble();
                System.out.println("Enter Product Quantity: ");
                int quantity = sc.nextInt();
                sc.nextLine(); // catch next line character
                System.out.println("Enter Product Category: ");
                String category = sc.nextLine();
                product.setProductID(ProductID);
                product.setName(name);
                product.setPrice(price);
                product.setQuantity(quantity);
                product.setCategory(category);
                FileWriter fw = new FileWriter("src/ProductManager/Product.txt");
                BufferedWriter bw = new BufferedWriter(fw);
                for (Product product1 : products) {
                    bw.write(product1.toString());
                    bw.newLine();
                }
                bw.close();
                fw.close();
            }
        }
    }
    // readProduct method will read all the product from Product.txt and store it in an ArrayList
    public ArrayList<Product> readProduct() throws IOException {
        ArrayList<Product> products = new ArrayList<>();
        FileReader fr = new FileReader("src/ProductManager/Product.txt");
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null) {
            line = line.trim(); // Remove leading and trailing whitespace
            if (!line.isEmpty()) {
                String[] arr2 = line.split(", ");
                if (arr2.length == 5) {
                    String productID = arr2[0];
                    String name = arr2[1];
                    double price = Double.parseDouble(arr2[2].replaceAll("[^0-9.]", ""));
                    int quantity = Integer.parseInt(arr2[3].replaceAll("[^0-9]", ""));
                    String category = arr2[4];
                    Product product = new Product(productID, name, price, quantity, category);
                    products.add(product);
                } else {
                    // Handle invalid input or report an error
                    System.err.println("Invalid data format in the line: " + line);
                }
            }
        }
        return products;
    }


    // delete product using id or part of name of that product
    public void deleteProduct() throws IOException {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter Product ID or part of Product Name: ");
            String input = sc.nextLine();
            ArrayList<Product> products = readProduct();
            for (Product product : products) {
                if (product.getName().contains(input) || Objects.equals(product.getProductID(), input)) {
                    products.remove(product);
                    FileWriter fw = new FileWriter("src/ProductManager/Product.txt");
                    BufferedWriter bw = new BufferedWriter(fw);
                    for (Product product1 : products) {
                        bw.write(product1.toString());
                        bw.newLine();
                    }
                    bw.close();
                    fw.close();
                }
            }
        }
    // print all list from file, sort by quantity descending, price descending
    public void printProduct() throws IOException {
        ArrayList<Product> products = readProduct();
        for (Product product : products) {
            System.out.println(product.toString());
        }
    }

    public void menu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Create Product");
        System.out.println("2. Check Product");
        System.out.println("3. Search Product");
        System.out.println("4. Update Product");
        System.out.println("5. Delete Product");
        System.out.println("6. Print Product");
        System.out.println("7. Exit");
        System.out.println("Enter your choice: ");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                try {
                    createProduct();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                try {
                    if (checkProduct()) {
                        System.out.println("Product is existing");
                    } else {
                        System.out.println("Product is not existing");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                try {
                    searchProduct();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 4:
                try {
                    updateProduct();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 5:
                try {
                    deleteProduct();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 6:
                try {
                    printProduct();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 7:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice");
        }
    }
}