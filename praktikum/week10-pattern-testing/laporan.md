# Laporan Praktikum Minggu 10
Topik: Design Pattern (Singleton, MVC) dan Unit Testing menggunakan JUnit

## Identitas
- Nama  : Novi Fitriyani
- NIM   : 240202843
- Kelas : 3IKRA

---

## Tujuan
Mahasiswa mampu:  
1. Menjelaskan konsep dasar design pattern dalam rekayasa perangkat lunak.
2. Mengimplementasikan Singleton Pattern dengan benar.
3. Menjelaskan dan menerapkan Model–View–Controller (MVC) pada aplikasi sederhana.
4. Membuat dan menjalankan unit test menggunakan JUnit.
5. Menganalisis manfaat penerapan design pattern dan unit testing terhadap kualitas perangkat lunak.

---

## Dasar Teori
1. Design pattern adalah solusi umum yang telah teruji untuk permasalahan berulang dalam pengembangan perangkat lunak.
2. Singleton Pattern memastikan sebuah class hanya memiliki satu instance selama aplikasi berjalan.
3. MVC (Model–View–Controller) memisahkan logika aplikasi, tampilan, dan pengendali agar kode lebih terstruktur.
4. Unit testing digunakan untuk menguji fungsi-fungsi kecil dalam program secara terpisah.

---

## Langkah Praktikum
1. Menyiapkan project Java dan struktur folder praktikum Week 10.
2. Membuat class DatabaseConnection dengan menerapkan Singleton Pattern.
3. Mengimplementasikan struktur MVC yang terdiri dari Model (Product), View (ConsoleView), dan Controller (ProductController).
4. Menjalankan program utama AppMVC untuk memastikan integrasi MVC berjalan dengan baik.
5. Membuat unit test menggunakan JUnit pada class Product.
6. Menjalankan unit test dan mendokumentasikan hasil pengujian.
7. Melakukan commit
---

## Kode Program
**DataaseConnection.java**
```java
package com.upb.agripos.config;

public class DatabaseConnection {

    private static DatabaseConnection instance;

    // Constructor private → tidak bisa new dari luar
    private DatabaseConnection() {
        System.out.println("Database connected");
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
}

```

**Product.java**
```java
package com.upb.agripos.model;

public class Product {
    private final String code;
    private final String name;

    public Product(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
```

**ConsolView.java**
```java
package com.upb.agripos.view;

public class ConsoleView {
    public void showMessage(String message) {
        System.out.println(message);
    }
}
```

**ProductController.java**
```java
package com.upb.agripos.controller;

import com.upb.agripos.model.Product;
import com.upb.agripos.view.ConsoleView;

public class ProductController {
    private final Product model;
    private final ConsoleView view;

    public ProductController(Product model, ConsoleView view) {
        this.model = model;
        this.view = view;
    }

    public void showProduct() {
        view.showMessage("Produk: " + model.getCode() + " - " + model.getName());
    }
}
```

**AppMVC.java**
```java
package com.upb.agripos;

import com.upb.agripos.controller.ProductController;
import com.upb.agripos.model.Product;
import com.upb.agripos.view.ConsoleView;

public class AppMVC {
    public static void main(String[] args) {
        System.out.println("Hello, I am Novi Fitriyani-240202843 (Week10)");
        Product product = new Product("P01", "Pupuk Organik");
        ConsoleView view = new ConsoleView();
        ProductController controller = new ProductController(product, view);
        controller.showProduct();
    }
}
```

**ProductTest.java**
```java
package com.upb.agripos;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import com.upb.agripos.model.Product;

public class ProductTest {

    @Test
    public void testProductName() {
        Product p = new Product("P01", "Benih Jagung");
        Assertions.assertEquals("Benih Jagung", p.getName());
    }
}

```

---

## Hasil Eksekusi
**Screenshot**
<img width="854" height="865" alt="image" src="https://github.com/user-attachments/assets/e017afc2-2b41-45e3-ac86-a370396ab62d" />
<img width="997" height="762" alt="image" src="https://github.com/user-attachments/assets/cf96dbe7-e367-479e-af93-58a54f426339" />

---

## Analisis
- Kode berjalan dengan memisahkan tanggung jawab antara Model, View, dan Controller sehingga alur program lebih jelas.
- Dibandingkan minggu sebelumnya, praktikum ini menekankan pada struktur desain dan pengujian kode.
- Kendala yang dihadapi adalah konfigurasi JUnit, namun dapat diatasi dengan memastikan dependensi JUnit sudah sesuai.
---

## Kesimpulan
Dengan menerapkan Singleton Pattern, MVC, dan unit testing menggunakan JUnit, aplikasi menjadi lebih terstruktur, mudah dipelihara, dan memiliki kualitas kode yang lebih baik.
---

## Quiz
1. Mengapa constructor pada Singleton harus bersifat private?  
   **Jawaban:**  Constructor pada Singleton harus bersifat private agar object tidak dapat dibuat langsung dari luar class menggunakan new. Dengan demikian, pembuatan object hanya bisa dilakukan melalui method getInstance() sehingga memastikan hanya ada satu instance selama aplikasi berjalan.

2. Jelaskan manfaat pemisahan Model, View, dan Controller.  
   **Jawaban:**  Pemisahan Model, View, dan Controller membuat aplikasi menjadi lebih terstruktur, mudah dipelihara, dan mudah dikembangkan. Selain itu, perubahan pada tampilan tidak memengaruhi logika bisnis, dan sebaliknya, sehingga meningkatkan fleksibilitas serta kemudahan pengujian.

3. Apa peran unit testing dalam menjaga kualitas perangkat lunak?  
   **Jawaban:**  Unit testing berperan untuk memastikan setiap fungsi atau method berjalan sesuai dengan yang diharapkan, membantu mendeteksi kesalahan sejak dini, serta meningkatkan keandalan dan kualitas perangkat lunak sebelum digunakan lebih lanjut.

4. Apa risiko jika Singleton tidak diimplementasikan dengan benar?  
   **Jawaban:**  Jika Singleton tidak diimplementasikan dengan benar, dapat terjadi lebih dari satu instance dari class tersebut. Hal ini dapat menyebabkan inkonsistensi data, pemborosan sumber daya, dan potensi kesalahan logika dalam aplikasi.
