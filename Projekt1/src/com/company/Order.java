package com.company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order implements Serializable {
    private int id;
    private Date date;
    private EStatus status;
    private List<Item> items;

    // konstruktor
    public Order(int id, Date date) {
        this.id = id;
        this.date = date;
        this.status = EStatus.PLACED;
        this.items = new ArrayList<Item>();
    }

    // dodawanie i usuwanie przedmiotów
    public void add_item(Item item) {
        items.add(item);
    }

    public void remove_item(Item item) {
        items.remove(item);
    }

    // obliczanie całkowitej wartości zamówienia
    public double calculate_total() {
        double total = 0.0;
        for (Item item : items) {
            total += item.getPrice();
        }
        return total;
    }

    // anulowanie zamówienia
    public void cancel() {
        status = EStatus.CANCELLED;
    }

    // oznaczanie zamówienia jako opłacone i wysłane
    public void pay() {
        status = EStatus.PAID;
    }

    public void ship() {
        status = EStatus.SHIPPED;
    }

    // settery i gettery
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public EStatus getStatus() {
        return status;
    }

    public void setStatus(EStatus status) {
        this.status = status;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
