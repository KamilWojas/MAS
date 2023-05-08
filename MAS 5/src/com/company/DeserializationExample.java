package com.company;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DeserializationExample {
    public static void main(String[] args) {
        try {
            // Otwórz strumień wejściowy z pliku
            FileInputStream fileIn = new FileInputStream("order.ser");

            // Tworzenie obiektu ObjectInputStream dla strumienia wejściowego
            ObjectInputStream in = new ObjectInputStream(fileIn);

            // Deserializacja obiektu
            Order order = (Order) in.readObject();

            // Zamykanie strumieni
            in.close();
            fileIn.close();

            // Wyświetlanie informacji o zamówieniu
            System.out.println("Order ID: " + order.getId());
            System.out.println("Order Date: " + order.getDate());
            System.out.println("Order Status: " + order.getStatus());
            System.out.println("Order Items: " + order.getItems());
            System.out.println("Order Total: " + order.calculate_total());

        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Order class not found");
            c.printStackTrace();
        }
    }
}