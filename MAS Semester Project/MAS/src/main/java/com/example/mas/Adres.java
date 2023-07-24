package com.example.mas;


import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

public class Adres implements Serializable {
        private String ulica;
        private String numerDomu;
        private String numerMieszkania;
        private String kodPocztowy;
        private String miasto;
        private String kraj;
        private Adres adres; // Nowy atrybut złożony reprezentujący adres nadrzędny
        private static final Collection<Adres> ALL_ADDRESSES = new ArrayList<>();

        public Adres(String ulica, String numerDomu, String numerMieszkania, String kodPocztowy, String miasto, String kraj) {
                this.ulica = ulica;
                this.numerDomu = numerDomu;
                this.numerMieszkania = numerMieszkania;
                this.kodPocztowy = kodPocztowy;
                this.miasto = miasto;
                this.kraj = kraj;
                this.adres = null;
                ALL_ADDRESSES.add(this);
        }

        public Adres(String ulica, String miasto, String kodPocztowy) {
                this.ulica = ulica;
                this.miasto = miasto;
                this.kodPocztowy = kodPocztowy;
                this.adres = null;
                ALL_ADDRESSES.add(this);
        }

        public String getUlica() {
                return ulica;
        }

        public void setUlica(String ulica) {
                this.ulica = ulica;
        }

        public String getNumerDomu() {
                return numerDomu;
        }

        public void setNumerDomu(String numerDomu) {
                this.numerDomu = numerDomu;
        }

        public String getNumerMieszkania() {
                return numerMieszkania;
        }

        public void setNumerMieszkania(String numerMieszkania) {
                this.numerMieszkania = numerMieszkania;
        }

        public String getKodPocztowy() {
                return kodPocztowy;
        }

        public void setKodPocztowy(String kodPocztowy) {
                this.kodPocztowy = kodPocztowy;
        }

        public String getMiasto() {
                return miasto;
        }

        public void setMiasto(String miasto) {
                this.miasto = miasto;
        }

        public String getKraj() {
                return kraj;
        }

        public void setKraj(String kraj) {
                this.kraj = kraj;
        }

        public Adres getAdres() {
                return adres;
        }

        public void setAdres(Adres adres) {
                this.adres = adres;
        }

        public static void writeExtent(final ObjectOutputStream oos) throws Exception {
                oos.writeObject(ALL_ADDRESSES);
        }

        public static Collection<Adres> readExtent(final ObjectInputStream ois) throws Exception {
                return (Collection<Adres>) ois.readObject();
        }


        public static class Main {
                public static void main(String[] args) throws Exception {
                        final Adres adres1 = new Adres("Aleja 1", "Warszawa", "00-001");
                        final Adres adres2 = new Adres("Ulica 2", "Kraków", "12-345");

                        try (final ObjectOutputStream oos = new ObjectOutputStream(
                                new FileOutputStream("adresy.ser"))) {
                                writeExtent(oos);
                        }

                        try (final ObjectInputStream ois = new ObjectInputStream(
                                new FileInputStream("adresy.ser"))) {
                                final Collection<Adres> allAddresses = readExtent(ois);

                                System.out.println(allAddresses.size());
                                for (Adres adres : allAddresses) {
                                        System.out.println(adres.getUlica() + ", " + adres.getMiasto() + ", " + adres.getKodPocztowy());
                                }
                        }
                }
        }
}

