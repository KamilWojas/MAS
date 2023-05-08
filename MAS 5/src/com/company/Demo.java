package com.company;

import java.util.Date;

public class Demo {
    public static void main(String[] args) {
        // tworzenie obiektów klasy Item
        Item item1 = new Item(1, "Kawa", 10.50);
        Item item2 = new Item(2, "Herbata", 5.25);
        Item item3 = new Item(3, "Ciastka", 7.99);

        // tworzenie obiektów klasy Order
        Order order1 = new Order(1, new Date());
        order1.add_item(item1);
        order1.add_item(item2);
        order1.add_item(item3);

        Order order2 = new Order(2, new Date());
        order2.add_item(item2);
        order2.add_item(item3);

        // wyświetlanie informacji o zamówieniach
        System.out.println("Zamówienie 1:");
        System.out.println("ID: " + order1.getId());
        System.out.println("Data: " + order1.getDate());
        System.out.println("Status: " + order1.getStatus());
        System.out.println("Produkty: ");
        for (Item item : order1.getItems()) {
            System.out.println(item.getName() + " - " + item.getPrice());
        }
        System.out.println("Wartość zamówienia: " + order1.calculate_total());

        System.out.println("Zamówienie 2:");
        System.out.println("ID: " + order2.getId());
        System.out.println("Data: " + order2.getDate());
        System.out.println("Status: " + order2.getStatus());
        System.out.println("Produkty: ");
        for (Item item : order2.getItems()) {
            System.out.println(item.getName() + " - " + item.getPrice());
        }
        System.out.println("Wartość zamówienia: " + order2.calculate_total());

        // zmiana statusu zamówienia
        System.out.println("Zmiana statusu zamówienia 1 na opłacone i wysłane.");
        order1.pay();
        order1.ship();
        System.out.println("Nowy status zamówienia 1: " + order1.getStatus());

        // anulowanie zamówienia
        System.out.println("Anulowanie zamówienia 2.");
        order2.cancel();
        System.out.println("Nowy status zamówienia 2: " + order2.getStatus());
    }
}

