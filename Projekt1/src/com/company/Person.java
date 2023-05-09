package com.company;

import java.time.LocalDate;
import java.time.Period;

public class Person {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Address address; //W klasie Person mamy atrybut opcjonalny address,
    // który jest obiektem klasy Address. Domyślnie, gdy tworzymy obiekt klasy Person bez podania adresu,
    // wartość tego atrybutu jest ustawiana na null. Złożone atrybuty, takie jak Address,
    // mogą mieć przypisaną wartość null, podobnie jak proste typy, na przykład Integer czy Double,
    // które są klasami opakowującymi dla typów prymitywnych int i double.

    // konstruktor
    public Person(String firstName, String lastName, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    // getter i setter dla atrybutu opcjonalnego
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    // metoda klasowa zwracająca wiek osoby
    public int getAge() {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    // atrybut pochodny - metoda zwracająca pełne imię i nazwisko
    public String getFullName() {
        return firstName + " " + lastName;
    }

    // atrybut złożony - klasa Address - atrybut złożony Address,
    // który jest definiowany jako klasa wewnętrzna (inner class) Address w klasie Person.
    //atrybut złożony jest opisywany za pomocą nowej klasy.
    //W powyższym przykładzie klasa Address jest użyta do opisania atrybutu złożonego address
    // w klasie Person. Zauważ, że obiekt klasy Address tworzony jest w konstruktorze klasy Person.
    public static class Address {
        private String street;
        private String city;
        private String zipCode;

        public Address(String street, String city, String zipCode) {
            this.street = street;
            this.city = city;
            this.zipCode = zipCode;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getZipCode() {
            return zipCode;
        }

        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }
    }
}
