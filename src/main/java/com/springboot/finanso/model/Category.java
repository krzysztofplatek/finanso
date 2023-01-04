package com.springboot.finanso.model;

public class Category {

    public enum Categories {
        health("Health"),
        household_expenses("Household expenses"),
        education("Education"),
        transport("Transport"),
        family("Family"),
        holidays("Holidays"),
        sport("Sport"),
        work("Work"),
        others("Others");

        private final String toString;

        Categories(String toString) {
            this.toString = toString;
        }

        public String toString() {
            return toString;
        }
    }

}
