package com.upb.agripos;

public class MainCart {
    public static void main(String[] args) {
        System.out.println("Hello, I am Novi Fitriyani-240202843 (Week7)");

        Product p1 = new Product("P01", "Beras", 50000, 0);
        Product p2 = new Product("P02", "Pupuk", 30000, 0);

        ShoppingCart cart = new ShoppingCart();
        cart.addProduct(p1, 0);
        cart.addProduct(p2, 0);
        cart.printCart();

        cart.removeProduct(p1);
        cart.printCart();
    }
}