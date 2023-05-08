package com.company;

import java.util.ArrayList;
import java.util.List;

public class Status {
    public static final Status CANCELED = new Status("Canceled");
    public static final Status PAID = new Status("Paid");
    public static final Status SHIPPED = new Status("Shipped");
    private static int numStatuses = 0; //Jest to ATRYUT KLASOWY, ponieważ jest deklarowany jako
    // private static int numStatuses = 0; i odwołuje się bezpośrednio do klasy,
    // a nie do instancji obiektu tej klasy.
    private int id;
    private String name;
    private static List<Status> statuses = new ArrayList<Status>();

    //ESTENSJA TRWAŁOŚCI
    //obiekty tej klasy są trwale przechowywane w liście statuses za pomocą metody add(this),
    // wywołanej w konstruktorze. Dzięki temu każdy utworzony obiekt klasy Status zostanie
    // dodany do listy, która może być później wykorzystana w celu odczytu statusów lub
    // przeszukiwania ich po ID lub nazwie za pomocą odpowiednich metod statycznych.

    // konstruktor
    public Status(String name) {
        this.id = ++numStatuses;
        this.name = name;
        statuses.add(this);
    }

    // settery i gettery
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // metoda zwracająca listę wszystkich dostępnych statusów
    public static List<Status> getStatuses() {
        return statuses;
    }

    // metoda zwracająca status po id
    public static Status getStatusById(int id) {
        for (Status status : statuses) {
            if (status.getId() == id) {
                return status;
            }
        }
        return null;
    }

    // metoda zwracająca status po nazwie
    public static Status getStatusByName(String name) {
        for (Status status : statuses) {
            if (status.getName().equals(name)) {
                return status;
            }
        }
        return null;
    }
}