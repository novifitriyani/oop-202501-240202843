# Laporan Praktikum Minggu 2
Topik: Class dan Object (Produk Pertanian)

## Identitas
- Nama  : Novi Fitriyani
- NIM   : 240202843
- Kelas : 3IKRA

---

## Tujuan
Mahasiswa memahami konsep class dan object serta dapat membuat class Produk dengan enkapsulasi, membuat objek, dan menampilkan informasi produk menggunakan java.

---

## Dasar Teori 
1. Class adalah blueprint atau cetak biru dari objek yang berisi atribut dan method.   
2. Object adalah instansiasi dari class yang dapat menyimpan data dan memanggil method.  
3. Enkapsulasi digunakan untuk menyembunyikan data (atribut private) dan mengatur akses melalui getter dan setter
4. Method statis (static) dapat dipanggil tanpa membuat objek.
5. Package digunakkan untuk mengelompokkan class sesuai fungsinya.

---

## Langkah Praktikum
1. Membuat folder project: oop-202501-240202843/praktikum/week2-class-object/.
2. Membuat struktur folder src/main/java/com/upb/agripos, model, util, dan agripos.
3. Membuat file Produk.java di package model dengan atribut kode, nama, harga, dan stok, serta getter/setter dan method tambahStok/kurangiStok.
4. Membuat file CreditBy.java di package util untuk menampilkan identitas mahasiswa.
5. Membuat file MainProduk.java di package agripos, instansi tiga produk, menampilkan informasi produk, dan memanggil CreditBy.print().
6. Compile dan run program di terminal VS code.
7. Commit dengan pesan: week2-class-object.

---

## Kode Program
Produk.java
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

public String getKode() {return kode; }
public void setKode (String kode) {this.kode = kode;}

public String getNama() {return nama; }
public void setNama (String nama) {this.nama = nama;}

public double getHarga() {return harga; }
public void setHarga (double harga) {this.harga = harga;}

public int getStok() {return stok; }
public void setStok(int stok) {this.stok = stok;}

public void tambahStok(int jumlah) {
    this.stok += jumlah;
}

public void kurangiStok(int jumlah) {
    if (this.stok >= jumlah) {
        this.stok -= jumlah;
    } else {
        System.out.println("Stok tidak mencukupi!");
    }
}
}
```

CreditBy.java
```java
package main.java.com.upb.agripos.util;

public class CreditBy {
    public static void print(String nim, String nama) {
        System.out.println("\ncredit by: " + nim + " - " + nama);
    }
}
```
---

MainProduk.java
```java
package main.java.com.upb.agripos;

import main.java.com.upb.agripos.model.Produk;
import main.java.com.upb.agripos.util.CreditBy;

public class MainProduk {
    public static void main(String[] args) {
        main.java.com.upb.agripos.model.Produk p1 = new Produk("BNH-001", "Benih Padi IR64", 25000, 100);
        Produk p2 = new Produk("PPK-101", "Pupuk Urea 50kg", 350000, 40);
        Produk p3 = new Produk("ALT-501", "Cangkul Baja", 90000, 15);

        System.out.println("Kode: " + p1.getKode() + ", Nama: " + p1.getNama() + ", Harga: " + p1.getHarga() + ", Stok: " + p1.getStok());
        System.out.println("Kode: " + p2.getKode() + ", Nama: " + p2.getNama() + ", Harga: " + p2.getHarga() + ", Stok: " + p2.getStok());
        System.out.println("Kode: " + p3.getKode() + ", Nama: " + p3.getNama() + ", Harga: " + p3.getHarga() + ", Stok: " + p3.getStok());

        // Tampilkan identitas mahasiswa
        CreditBy.print("240202843", "Novi Fitriyani");
}

}
```


## Hasil Eksekusi
(Sertakan screenshot hasil eksekusi program.  
![Screenshot hasil](screenshots/hasil.png)
)
---

## Analisis
(
- Jelaskan bagaimana kode berjalan.  
- Apa perbedaan pendekatan minggu ini dibanding minggu sebelumnya.  
- Kendala yang dihadapi dan cara mengatasinya.  
)
---

## Kesimpulan
(Tuliskan kesimpulan dari praktikum minggu ini.  
Contoh: *Dengan menggunakan class dan object, program menjadi lebih terstruktur dan mudah dikembangkan.*)

---

## Quiz
(1. [Tuliskan kembali pertanyaan 1 dari panduan]  
   **Jawaban:** …  

2. [Tuliskan kembali pertanyaan 2 dari panduan]  
   **Jawaban:** …  

3. [Tuliskan kembali pertanyaan 3 dari panduan]  
   **Jawaban:** …  )
