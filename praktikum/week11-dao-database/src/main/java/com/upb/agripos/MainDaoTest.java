package main.java.com.upb.agripos;

import java.sql.Connection;
import java.sql.DriverManager;

import com.upb.agripos.Product;

import main.java.com.upb.agripos.dao.ProductDAO;
import main.java.com.upb.agripos.dao.ProductDAOImpl;

public class MainDaoTest {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, I am Novi Fitriyani-240202843 (Week11)");

        // Koneksi ke database
        Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://[::1]:5432/agripos",
                "postgres",
                "postgres");

        ProductDAO dao = new ProductDAOImpl(conn);

        // Insert produk baru (sertakan stock)
        Product newProduct = new Product("P01", "Pupuk Organik", 25000, 10); // 10 = stock
        dao.insert(newProduct);

        // Update produk (jangan lupa sertakan stock juga)
        Product updatedProduct = new Product("P01", "Pupuk Organik Premium", 30000, 15);
        dao.update(updatedProduct);

        // Cari produk
        Product p = dao.findByCode("P01");
        if (p != null) {
            System.out.println("Nama Produk: " + p.getName());
            System.out.println("Harga: " + p.getPrice());
            System.out.println("Stock: " + p.getStock());
        } else {
            System.out.println("Product dengan code P01 tidak ditemukan!");
        }

        // READ ALL
        System.out.println("Daftar Produk:");
        for (Product prod : dao.findAll()) {
            System.out.println(
                    prod.getCode() + " - " + prod.getName() + " - " + prod.getPrice() + " - " + prod.getStock());
        }

        // Hapus produk
        dao.delete("P01");

        System.out.println("\nDaftar Produk Setelah Delete:");
        for (Product prod : dao.findAll()) {
            System.out.println(
                    prod.getCode() + " - " + prod.getName() + " - " + prod.getPrice() + " - " + prod.getStock());
        }

        // Tutup koneksi
        conn.close();
    }
}
