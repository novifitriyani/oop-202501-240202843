# Laporan Praktikum Minggu 9
Topik: Exception Handling, Custom Exception, dan Penerapan Design Pattern

## Identitas
- Nama  : Novi Fitriyani
- NIM   : 240202843
- Kelas : 3IKRA

---

## Tujuan
Mahasiswa mampu:  
1. Menjelaskan perbedaan antara error dan exception.
2. Mengimplementasikan try–catch–finally dengan tepat.
3. Membuat custom exception sesuai kebutuhan program.
4. Mengintegrasikan exception handling ke dalam aplikasi sederhana (kasus keranjang belanja).
5. (Opsional) Menerapkan design pattern sederhana (Singleton/MVC) dan unit testing dasar.

---

## Dasar Teori
1. Error merupakan kesalahan fatal yang biasanya tidak dapat ditangani oleh program, seperti OutOfMemoryError.
2. Exception adalah kondisi kesalahan yang masih dapat ditangani sehingga program tidak langsung berhenti.
3. Exception handling di Java menggunakan struktur try–catch–finally.
4. Custom exception dibuat untuk menangani kesalahan yang spesifik sesuai kebutuhan bisnis aplikasi.
---

## Langkah Praktikum
1. Menyiapkan struktur direktori dan package com.upb.agripos.
2. Membuat minimal dua custom exception, yaitu InvalidQuantityException, ProductNotFoundException, dan InsufficientStockException.
3.Membuat class Product sebagai model produk yang memiliki atribut stok.
4. Mengimplementasikan class ShoppingCart dengan validasi dan exception handling pada metode tambah produk, hapus produk, dan checkout.
5. Membuat class MainExceptionDemo sebagai controller untuk menguji seluruh exception.
6. Menjalankan program dan melakukan screenshot hasil eksekusi.
7. Melakukan commit dan push ke repository GitHub.

---

## Kode Program
**Product.java**
```java
package com.upb.agripos;

public class Product {
    private final String code;
    private final String name;
    private final double price;
    private int stock;

    public Product(String code, String name, double price, int stock) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getCode() { return code; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }
    public void reduceStock(int qty) { this.stock -= qty; }
}
```

**InvalidQuantityException.java**
```java
package com.upb.agripos;

public class InvalidQuantityException extends Exception {
    public InvalidQuantityException(String msg) { super(msg); }
}
```

**ProductNotFoundException.java**
```java
package com.upb.agripos;

public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(String msg) { super(msg); }
}
```

**InsufficientStockException.java**
```java
package com.upb.agripos;

public class InsufficientStockException extends Exception {
    public InsufficientStockException(String msg) { super(msg); }
}
```

**ShoppingCart.java**
```java
package com.upb.agripos;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private final Map<Product, Integer> items = new HashMap<>();

    public void addProduct(Product p, int qty) throws InvalidQuantityException {
        if (qty <= 0) {
            throw new InvalidQuantityException("Quantity harus lebih dari 0.");
        }
        items.put(p, items.getOrDefault(p, 0) + qty);
    }

    public void removeProduct(Product p) throws ProductNotFoundException {
        if (!items.containsKey(p)) {
            throw new ProductNotFoundException("Produk tidak ada dalam keranjang.");
        }
        items.remove(p);
    }

    public void checkout() throws InsufficientStockException {
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int qty = entry.getValue();
            if (product.getStock() < qty) {
                throw new InsufficientStockException(
                    "Stok tidak cukup untuk: " + product.getName()
                );
            }
        }
        // contoh pengurangan stok bila semua cukup
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            entry.getKey().reduceStock(entry.getValue());
        }
    }
}
```

**MainExceptionDemo.java**
```java
package com.upb.agripos;

public class MainExceptionDemo {
    public static void main(String[] args) {
        System.out.println("Hello, I am Novi Fitriyani-240202843 (Week9)");

        ShoppingCart cart = new ShoppingCart();
        Product p1 = new Product("P01", "Pupuk Organik", 25000, 3);

        try {
            cart.addProduct(p1, -1);
        } catch (InvalidQuantityException e) {
            System.out.println("Kesalahan: " + e.getMessage());
        }

        try {
            cart.removeProduct(p1);
        } catch (ProductNotFoundException e) {
            System.out.println("Kesalahan: " + e.getMessage());
        }

        try {
            cart.addProduct(p1, 5);
            cart.checkout();
        } catch (Exception e) {
            System.out.println("Kesalahan: " + e.getMessage());
        }
    }
}
```

---

## Hasil Eksekusi
**Screenshot**
![alt text](image.png)
---

## Analisis
- Program berjalan dengan memanfaatkan blok try–catch untuk menangani setiap kesalahan yang terjadi.
- Dibandingkan praktikum minggu sebelumnya, praktikum ini lebih menekankan pada penanganan kesalahan agar program tetap berjalan dengan aman.
- Kendala yang ditemui adalah munculnya pesan warning dari JDK, namun tidak mempengaruhi hasil eksekusi program.
---

## Kesimpulan
Penerapan exception handling dan custom exception membuat program lebih robust dan mampu menangani kesalahan secara terstruktur. Dengan adanya mekanisme ini, aplikasi tidak langsung berhenti saat terjadi kesalahan dan dapat memberikan pesan yang informatif kepada pengguna.

---

## Quiz
1. Jelaskan perbedaan error dan exception.  
   **Jawaban:**  Error adalah kesalahan fatal yang tidak dapat ditangani oleh program, sedangkan exception adalah kesalahan yang masih dapat ditangani.

2. Apa fungsi finally dalam blok try–catch–finally?  
   **Jawaban:**  Finally digunakan untuk mengeksekusi kode yang harus selalu dijalankan baik terjadi exception maupun tidak.

3. Mengapa custom exception diperlukan?  
   **Jawaban:**  Custom exception diperlukan agar penanganan kesalahan lebih spesifik dan sesuai dengan kebutuhan aplikasi.

4. Berikan contoh kasus bisnis dalam POS yang membutuhkan custom exception.  
   **Jawaban:**  Contohnya adalah ketika jumlah pembelian melebihi stok yang tersedia sehingga diperlukan exception stok tidak mencukupi.
