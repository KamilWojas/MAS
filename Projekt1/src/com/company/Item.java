package com.company;

import java.io.Serializable;

public class Item implements Serializable {
        private String name;
        private double price;

        public Item(int i, String name, double price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }


    }



