# Laporan Praktikum Minggu 4
Topik: Polymorphism (Info Produk)

## Identitas
- Nama  : Novi Fitriyani
- NIM   : 240202843
- Kelas : 3IKRA

---

## Tujuan
- Mahasiswa mampu **menjelaskan konsep polymorphism** dalam OOP.  
- Mahasiswa mampu **membedakan method overloading dan overriding**.  
- Mahasiswa mampu **mengimplementasikan polymorphism (overriding, overloading, dynamic binding)** dalam program.  
- Mahasiswa mampu **menganalisis contoh kasus polymorphism** pada sistem nyata (Agri-POS).  
---

## Dasar Teori
Polymorphism berarti “banyak bentuk” dan memungkinkan objek yang berbeda merespons panggilan method yang sama dengan cara yang berbeda.  
1. **Overloading** → mendefinisikan method dengan nama sama tetapi parameter berbeda.  
2. **Overriding** → subclass mengganti implementasi method dari superclass.  
3. **Dynamic Binding** → pemanggilan method ditentukan saat runtime, bukan compile time.  

Dalam konteks Agri-POS, misalnya:  
- Method `getInfo()` pada `Produk` dioverride oleh `Benih`, `Pupuk`, `AlatPertanian` untuk menampilkan detail spesifik.  
- Method `tambahStok()` bisa dibuat overload dengan parameter berbeda (int, double).  

---

## Langkah Praktikum
1. **Overloading**  
   - Tambahkan method `tambahStok(int jumlah)` dan `tambahStok(double jumlah)` pada class `Produk`.  

2. **Overriding**  
   - Tambahkan method `getInfo()` pada superclass `Produk`.  
   - Override method `getInfo()` pada subclass `Benih`, `Pupuk`, dan `AlatPertanian`.  

3. **Dynamic Binding**  
   - Buat array `Produk[] daftarProduk` yang berisi objek `Benih`, `Pupuk`, dan `AlatPertanian`.  
   - Loop array tersebut dan panggil `getInfo()`. Perhatikan bagaimana Java memanggil method sesuai jenis objek aktual.  

4. **Main Class**  
   - Buat `MainPolymorphism.java` untuk mendemonstrasikan overloading, overriding, dan dynamic binding.  

5. **CreditBy**  
   - Tetap panggil `CreditBy.print("<NIM>", "<Nama>")`.  

6. **Commit dan Push**  
   - Commit dengan pesan: `week4-polymorphism`.  

---

## Kode Program

**Produk.java**
```java
package main.java.com.upb.agripos.model;

public class Produk {
    private String kode;
    private String nama;
    private double harga;
    private int stok;

    public Produk(String kode, String nama, double harga, int stok) {
        this.kode = kode;
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
    }

    public double getHarga() {
        return harga;
    }

    public double getStok() {
        return stok;
    }


    public void tambahStok(int jumlah) {
        this.stok += jumlah;
    }

    public void tambahStok(double jumlah) {
        this.stok += (int) jumlah;
    }

    public String getInfo() {
        return "Produk: " + nama + " (Kode: " + kode + "), Harga: Rp" + harga + ", Stok: " + stok;
    }
}
```
**Benih.java**
```java
package main.java.com.upb.agripos.model;

public class Benih extends Produk {
    private String varietas;

    public Benih(String kode, String nama, double harga, int stok, String varietas) {
        super(kode, nama, harga, stok);
        this.varietas = varietas;
    }

    @Override
    public String getInfo() {
        return "Benih: " + super.getInfo() + ", Varietas: " + varietas;
    }
}
```

**Pupuk.java**
```java
package main.java.com.upb.agripos.model;

public class Pupuk extends Produk {
    private String jenis;

    public Pupuk(String kode, String nama, double harga, int stok, String jenis) {
        super(kode, nama, harga, stok);
        this.jenis = jenis;
    }

    @Override
    public String getInfo() {
        return "Pupuk: " + jenis + " - " + super.getInfo();
    }
}
```

**AlatPertanian.java**
```java
package main.java.com.upb.agripos.model;

public class AlatPertanian extends Produk {
    private String material;

    public AlatPertanian(String kode, String nama, double harga, int stok, String material) {
        super(kode, nama, harga, stok);
        this.material = material;
    }
    
    @Override
    public String getInfo() {
        return "Alat Pertanian (" + material + "): " + super.getInfo();
    }
}
```

**ObatHama.java**
```java
package main.java.com.upb.agripos.model;

public class ObatHama extends Produk {
    private String kandungan;

    public ObatHama(String kode, String nama, double harga, int stok, String kandungan) {
        super(kode, nama, harga, stok);
        this.kandungan = kandungan;
    }

    @Override
    public String getInfo() {
        return "Obat Hama (Kandungan: " + kandungan + ") - " + super.getInfo();
    }
}

```

**CreditBy.java**
```java
package main.java.com.upb.agripos.util;

public class CreditBy {
    public static void print(String nim, String nama) {
        System.out.println("\ncredit by: " + nim + " - " + nama);
    }
}
```
**MainPolymorphism.java**
```java
package main.java.com.upb.agripos;

import main.java.com.upb.agripos.model.*;
import main.java.com.upb.agripos.util.CreditBy;

public class MainPolymorphism {
    public static void main(String[] args) {
        Produk[] daftarProduk = {
            new Benih("BNH-001", "Benih Padi IR64", 25000, 100, "IR64"),
            new Pupuk("PPK-101", "Pupuk Urea", 350000, 40, "Urea"),
            new AlatPertanian("ALT-501", "Cangkul Baja", 90000, 15, "Baja")
        };

        for (Produk p : daftarProduk) {
            System.out.println(p.getInfo()); // Dynamic Binding
        }

        CreditBy.print("240202843", "Novi Fitriyani");
    }
}
```
---

## Hasil Eksekusi
(Sertakan screenshot hasil eksekusi program.  
![Screenshot hasil](screenshots/hasil.png)
)
---

## Analisis
1. **Overloading** diterapkan pada method `tambahStok()` di class `Produk`, di mana terdapat dua versi method dengan parameter berbeda (`int` dan `double`). Hal ini memungkinkan metode yang sama digunakan untuk tipe data berbeda tanpa konflik.
2. **Overriding** diterapkan pada method `getInfo()` di masing-masing subclass (`Benih`, `Pupuk`, `AlatPertanian`, dan `ObatHama`) untuk menampilkan informasi produk yang lebih spesifik. Dengan ini, setiap subclass memiliki cara sendiri dalam menampilkan detail produknya.
3. **Dynamic Binding** terjadi ketika program menjalankan perintah `p.getInfo()` di dalam loop array `Produk[] daftarProduk`. Walaupun variabelnya bertipe `Produk`, Java secara otomatis memanggil method `getInfo()` milik subclass aktual (misalnya `Benih` atau `Pupuk`).
4. Tidak terdapat kendala besar selama implementasi. Tantangan kecil muncul saat memahami perbedaan antara overloading dan overriding, terutama dalam penentuan parameter dan anotasi `@Override`. Setelah membaca ulang materi dan melakukan uji coba kecil, program dapat berjalan normal dan menampilkan hasil sesuai konsep *dynamic binding*.
---

## Kesimpulan
1. **Overloading** memungkinkan satu method memiliki beberapa versi dengan parameter berbeda.
2. **Overriding** memungkinkan subclass menyesuaikan perilaku method dari superclass.
3. **Dynamic binding** membuat program lebih fleksibel dan mudah dikembangkan.
4. Konsep polymorphism terbukti dapat diterapkan untuk sistem penjualan (Agri-POS) agar lebih modular dan mudah diperluas.
---

## Quiz
1. Apa perbedaan overloading dan overriding?  
   **Jawaban:** Overloading memiliki nama method yang sama tetapi parameter berbeda. Overriding memiliki nama dan parameter sama, namun implementasinya berbeda di subclass.

2. Bagaimana Java menentukan method mana yang dipanggil dalam dynamic binding?  
   **Jawaban:** Java menentukan method yang dipanggil berdasarkan tipe objek aktual saat runtime, bukan tipe referensinya.

3. Berikan contoh kasus polymorphism dalam sistem POS selain produk pertanian.  
   **Jawaban:** Contoh pada sistem POS toko elektronik, class `Produk` bisa memiliki subclass `Laptop`, `Smartphone`, dan `Aksesoris`, masing-masing dengan implementasi `getInfo()` berbeda sesuai spesifikasinya. 
