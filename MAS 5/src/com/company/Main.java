package com.company;

import java.io.*;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        Order order = new Order(1, new Date());
        Item item1 = new Item(1, "Iphon 14 ", 6499.0);
        Item item2 = new Item(2, "Product 2", 20.0);
        Item item3 = new Item(3, "Product 3", 30.0);
        order.add_item(item1);
        order.add_item(item2);
        order.add_item(item3);

        boolean exit = false;
        while (!exit) {
            System.out.println("1. Serialize order");
            System.out.println("2. Deserialize order");
            System.out.println("3. Exit");
            System.out.print("Enter option: ");
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                int option = Integer.parseInt(reader.readLine());
                switch (option) {
                    case 1:
                        serialize(order);
                        break;
                    case 2:
                        deserialize();
                        break;
                    case 3:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid option");
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void serialize(Order order) {
        try {
            FileOutputStream fileOut = new FileOutputStream("Zamowienie");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(order);
            out.close();
            fileOut.close();
            System.out.println("Order serialized to order.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    private static void deserialize() {
        try {
            FileInputStream fileIn = new FileInputStream("order.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Order order = (Order) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Order deserialized from order.ser");
            System.out.println("Order id: " + order.getId());
            System.out.println("Order date: " + order.getDate());
            System.out.println("Order status: " + order.getStatus());
            System.out.println("Order items:");
            for (Item item : order.getItems()) {
                System.out.println(" - " + item.getName() + " (Cena: " + item.getPrice() + ")");
            }
            System.out.println("Calosc zamowienia: " + order.calculate_total());
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Order class not found");
            c.printStackTrace();
        }
    }

}
