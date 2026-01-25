# Laporan Praktikum Minggu 11
Topik: Data Access Object (DAO) dan CRUD Database dengan JDBC

## Identitas
- Nama  : Novi Fitriyani
- NIM   : 240202843
- Kelas : 3IKRA

---

## Tujuan
Mahasiswa mampu:
1. Menjelaskan konsep Data Access Object (DAO) dalam pengembangan aplikasi OOP.
2. Menghubungkan aplikasi Java dengan basis data menggunakan JDBC.
3. Mengimplementasikan operasi CRUD (Create, Read, Update, Delete) secara lengkap.
4. Mengintegrasikan DAO dengan class aplikasi OOP sesuai prinsip desain yang baik.


---

## Dasar Teori
1. DAO adalah pola desain untuk memisahkan logika akses data dari logika bisnis aplikasi.  
2. JDBC digunakan untuk koneksi antara Java dan database relasional seperti PostgreSQL.  
3. CRUD terdiri dari Create (Insert), Read (Select), Update, dan Delete data.  
4. PreparedStatement digunakan untuk mencegah SQL Injection dan memudahkan parameterisasi query.  
5. Integrasi DAO menjaga kode tetap modular dan mudah dipelihara.

---

## Langkah Praktikum
1. Buat database PostgreSQL agripos dan tabel product
2. Buat class model Product.java dengan atribut code, name, price, stock.
3. Buat interface DAO ProductDAO.java dengan metode insert, findByCode, findAll, update, delete.
4. Implementasikan ProductDAOImpl.java menggunakan JDBC dan PreparedStatement.
5. Buat MainDaoTest.java untuk menguji operasi CRUD:
- Insert produk baru
- Update produk
- Read produk dengan findByCode dan findAll
- Delete produk

---

## Kode Program
**Product.java**
```java
package main.java.com.upb.agripos.model;

public class Product {
    private String code;
    private String name;
    private double price;
    private int stock;

    public Product(String code, String name, double price, int stock) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
```

**ProductDAO.java**
```java
package main.java.com.upb.agripos.dao;

import java.util.List;

import com.upb.agripos.Product;

public interface ProductDAO {
    void insert(Product product) throws Exception;

    Product findByCode(String code) throws Exception;

    List<Product> findAll() throws Exception;

    void update(Product product) throws Exception;

    void delete(String code) throws Exception;

}
```

**ProductDAOImpl.java**
```java
package main.java.com.upb.agripos.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.upb.agripos.Product;

public class ProductDAOImpl implements ProductDAO {

    private final Connection connection;

    public ProductDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Product p) throws Exception {
        String sql = "INSERT INTO product(code, name, price, stock) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, p.getCode());
            ps.setString(2, p.getName());
            ps.setDouble(3, p.getPrice());
            ps.setInt(4, p.getStock());
            ps.executeUpdate();
        }
    }

    @Override

    public Product findByCode(String code) throws Exception {
        String sql = "SELECT * FROM product WHERE code = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, code);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Product(
                            rs.getString("code"),
                            rs.getString("name"),
                            rs.getDouble("price"),
                            rs.getInt("stock"));
                }
            }
        }
        return null;
    }

    @Override
    public List<Product> findAll() throws Exception {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM product";
        try (PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Product(
                        rs.getString("code"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("stock")));
            }
        }
        return list;
    }

    @Override
    public void update(Product p) throws Exception {
        String sql = "UPDATE product SET name=?, price=?, stock=? WHERE code=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, p.getName());
            ps.setDouble(2, p.getPrice());
            ps.setInt(3, p.getStock());
            ps.setString(4, p.getCode());
            ps.executeUpdate();
        }
    }

    @Override
    public void delete(String code) throws Exception {
        String sql = "DELETE FROM product WHERE code=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, code);
            ps.executeUpdate();
        }
    }

}
```

**MainDaoTest.java**
```java
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

```

---

## Hasil Eksekusi
**Screenshot**  

<img width="833" height="216" alt="image" src="https://github.com/user-attachments/assets/7c9e2e36-0fa9-41b3-8275-b691d4dbfe5c" />

---

## Analisis
- Program berhasil mengimplementasikan DAO dengan JDBC untuk operasi CRUD.
- Pendekatan DAO membuat logika akses data terpisah dari logika aplikasi.
- Kendala awal: nama package dan import perlu sesuai struktur folder agar javac tidak error.
- Solusi: menyusun folder sesuai package com.upb.agripos dan compile dengan jalur yang benar.
---

## Kesimpulan
- DAO mempermudah pengelolaan data dengan OOP.
- CRUD berjalan lengkap dan output sesuai yang diharapkan.
- Program modular dan siap dikembangkan lebih lanjut.

---
