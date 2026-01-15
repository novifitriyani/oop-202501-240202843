# Laporan Praktikum Minggu 7
Topik: Collections dan Implementasi Keranjang Belanja

## Identitas
- Nama  : Novi fitriyani
- NIM   : 240202843
- Kelas : 3IKRA

---

## Tujuan
Mahasiswa mampu:  
1. Menjelaskan konsep collection dalam Java (List, Map, Set).
2. Menggunakan ArrayList untuk menyimpan dan mengelola objek.
3. Mengimplementasikan Map atau Set sesuai kebutuhan pengelolaan data.
4. Melakukan operasi dasar pada collection: tambah, hapus, dan hitung total.
5. Menganalisis efisiensi penggunaan collection dalam konteks sistem Agri-POS.

---

## Dasar Teori
1. Collections Framework
Java Collections Framework menyediakan struktur data untuk mengelola objek secara dinamis dan efisien.  
Struktur utama:  
- List (implementasi: ArrayList) — Terurut, dapat menyimpan elemen duplikat.
- Map (implementasi: HashMap) — Menyimpan pasangan key–value, akses cepat berdasarkan key.
- Set (implementasi: HashSet) — Tidak menerima duplikat dan tidak mempertahankan urutan.  
2. Studi Kasus: Keranjang Belanja Agri-POS  
- Keranjang belanja harus dapat:  
- Menambahkan produk
- Menghapus produk
- Menampilkan isi keranjang
- Menghitung total nilai transaksi
- Menangani jumlah (quantity) menggunakan Map  
Kasus ini mencerminkan penggunaan struktur data dalam aplikasi nyata seperti POS.
---

## Langkah Praktikum
1. Membuat class Product dengan atribut code, name, dan price.
2. Membuat class ShoppingCart menggunakan ArrayList<Product> untuk menambahkan, menghapus, dan menampilkan produk.
3. Membuat MainCart.java untuk menguji program.
4. Menambahkan beberapa produk (Beras dan Pupuk) ke keranjang.
5. Menjalankan metode addProduct(), removeProduct(), printCart(), dan getTotal().
6. Melakukan screenshot hasil eksekusi program (screenshots/hasil.png).
7. Commit & push
---

## Kode Program
**Product.java**
```java
package main.java.com.upb.agripos;

public class Product {
    private final String code;
    private final String name;
    private final double price;

    public Product(String code, String name, double price) {
        this.code = code;
        this.name = name;
        this.price = price;
    }

    public String getCode() { return code; }
    public String getName() { return name; }
    public double getPrice() { return price; }
}
```

**ShoppingCart.java**
```java
package main.java.com.upb.agripos;

import java.util.ArrayList;

public class ShoppingCart {
    private final ArrayList<Product> items = new ArrayList<>();

    public void addProduct(Product p) { items.add(p); }
    public void removeProduct(Product p) { items.remove(p); }

    public double getTotal() {
        double sum = 0;
        for (Product p : items) {
            sum += p.getPrice();
        }
        return sum;
    }

    public void printCart() {
        System.out.println("Isi Keranjang:");
        for (Product p : items) {
            System.out.println("- " + p.getCode() + " " + p.getName() + " = " + p.getPrice());
        }
        System.out.println("Total: " + getTotal());
    }
}
```

**ShoppingCartMap.java**
```java
package main.java.com.upb.agripos;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCartMap {
    private final Map<Product, Integer> items = new HashMap<>();

    public void addProduct(Product p) { items.put(p, items.getOrDefault(p, 0) + 1); }

    public void removeProduct(Product p) {
        if (!items.containsKey(p)) return;
        int qty = items.get(p);
        if (qty > 1) items.put(p, qty - 1);
        else items.remove(p);
    }

    public double getTotal() {
        double total = 0;
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }

    public void printCart() {
        System.out.println("Isi Keranjang (Map):");
        for (Map.Entry<Product, Integer> e : items.entrySet()) {
            System.out.println("- " + e.getKey().getCode() + " " + e.getKey().getName() + " x" + e.getValue());
        }
        System.out.println("Total: " + getTotal());
    }
}
```

**MainCart.java**
```java
package main.java.com.upb.agripos;

public class MainCart {
    public static void main(String[] args) {
        System.out.println("Hello, I am Novi Fitriyani-240202843 (Week7)");

        Product p1 = new Product("P01", "Beras", 50000);
        Product p2 = new Product("P02", "Pupuk", 30000);

        ShoppingCart cart = new ShoppingCart();
        cart.addProduct(p1);
        cart.addProduct(p2);
        cart.printCart();

        cart.removeProduct(p1);
        cart.printCart();
    }
}
```

---

## Hasil Eksekusi
**Screenshot**
![alt text](image.png)
---

## Analisis
- Kode berjalan dengan baik, produk berhasil ditambahkan, dihapus, dan total transaksi dihitung benar.
- Perbedaan pendekatan minggu ini dibanding minggu sebelumnya: penggunaan Java Collections (ArrayList) untuk manajemen objek, bukan array statis.
- Kendala: memastikan item dihapus dengan benar dari ArrayList, berhasil diatasi dengan items.remove(p).
---

## Kesimpulan
- ArrayList efektif digunakan untuk implementasi keranjang belanja sederhana.
- Map dapat digunakan sebagai alternatif jika ingin menyimpan quantity tiap produk.
- Menggunakan Collections membuat kode lebih fleksibel, dinamis, dan mudah dikembangkan.an mudah dikembangkan.*)

---

## Quiz
(1. Jelaskan perbedaan mendasar antara List, Map, dan Set.  
   **Jawaban:**  
   - List menyimpan elemen secara terurut dan bisa memiliki duplikasi. Cocok untuk menyimpan data berurutan seperti daftar belanja.
   - Set menyimpan elemen unik tanpa duplikasi dan tidak menjamin urutan. Digunakan ketika duplikasi harus dihindari, misalnya daftar kode produk.- - Map menyimpan data dalam pasangan key–value. Key harus unik, sedangkan value bisa sama. Berguna untuk menyimpan data yang bisa dicari cepat berdasarkan key, misalnya produk dan jumlahnya di keranjang.

2. Mengapa ArrayList cocok digunakan untuk keranjang belanja sederhana?
   **Jawaban:**  
   - ArrayList mudah digunakan untuk menambahkan dan menghapus produk secara dinamis.
   - Mendukung iterasi (looping) untuk menampilkan semua item dengan cepat.
   - Struktur ini fleksibel, sehingga jumlah item bisa bertambah atau berkurang tanpa perlu menentukan ukuran awal seperti array biasa.

3. Bagaimana struktur Set mencegah duplikasi data?
   **Jawaban:**  
   - Set menggunakan mekanisme hashing dan metode equals() untuk mengecek apakah elemen sudah ada.
   - Jika elemen sudah ada, Set tidak menambahnya lagi. Dengan demikian, tidak ada elemen duplikat dalam Set.

4. Kapan sebaiknya menggunakan Map dibandingkan List? Jelaskan dengan contoh.
   **Jawaban:**  
   - Map digunakan ketika data memiliki pasangan unik key–value dan kita ingin akses cepat berdasarkan key.
   - Contoh: Keranjang belanja yang menyimpan jumlah tiap produk.  
   ```java
   Map<Product, Integer> cart = new HashMap<>();
   cart.put(beras, 2);  // key = produk, value = jumlah
   cart.put(pupuk, 1);
   ```  
   Dengan Map, kita bisa langsung menambah, mengurangi, dan menghitung total tiap produk tanpa harus menelusuri seluruh list.

   )
