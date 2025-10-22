# Laporan Praktikum Minggu 2 
Topik: Class dan Object (Produk Pertanian)

## Identitas
- Nama  : Novi Fitriyani
- NIM   : 240202843
- Kelas : 3IKRA

---

## Tujuan
Mahasiswa mdapat emahami konsep class dan object serta dapat membuat class Produk dengan enkapsulasi, membuat objek, dan menampilkan informasi produk menggunakan Java.

---

## Dasar Teori
- Class adalah blueprint atau cetak biru dari objek yang berisi atribut dan method.
- Oject adalah instansiasi dari class yang dapat menyimpan data dan memanggil method.
- nkapsulasi digunakan untuk menyembunyikan data (atribut private) dan mengatur akses melalui getter dan setter.
- Method statis (static) dapat dipanggil tanpa membuat objek.
- Package digunakan untuk mengelompokkan class sesuai fungsinya.

---

## Langkah Praktikum
1. Membuat folder project: oop-20251-240202843/praktikum/week2-class-object/.
2. Membuat struktur folder src/main/java/com/upb/agripos/model, util, dan agripos.
3. Membuat file Produk.java di package model dengan atribut kode, nama, harga, dan stok, serta getter/setter dan method tambahStok/kurangiStok.
4. Membuat file CreditBy.java di package util untuk menampilkan identitas mahasiswa.
5. Membuat file MainProduk.java di package agripos, instansiasi tiga produk, menampilkan informasi produk, dan memanggil CreditBy.print().
6. Compile dan run program di terminal VS Code.
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
---

## Hasil Eksekusi
<img width="1093" height="1018" alt="image" src="https://github.com/user-attachments/assets/8d455515-fb7f-41dc-ac33-f088d5a40efa" />

---

## Analisis
- Program membuat tiga objek Produk dan menampilkan informasi setiap produk.
- CreditBy.print() digunakan untuk menampilkan identitas mahasiswa di akhir program.
- Perbedaan dengan minggu sebelumnya: menggunakan enkapsulasi dengan getter/setter dan memisahkan fungsi ke dalam package.
- Kendala: awalnya error main method not found, diselesaikan dengan memastikan MainProduk.java memiliki public static void main(String[] args) dan package/folder sesuai.
---

## Kesimpulan
Dengan menggunakan class dan object, program menjadi lebih terstruktur, mudah dikembangkan, dan memisahkan logika kode berdasarkan package.

---

## Quiz
1. Mengapa atribut sebaiknya dideklarasikan sebagai private dalam class?
   **Jawaban:** Karena atribut yang dideklarasikan private melindungi data dari akses langsung luar class (prinsip enkapsulasi). Dengan begitu, data hanya bisa diubah melalui method yang telah disediakan (getter dan setter), sehingga keamanan dan konsistensi data lebih terjaga. 

2. Apa fungsi getter dan setter dalam enkapsulasi? 
   **Jawaban:** Getter digunakan untuk mengambil nilai atribut private, sedangkan setter digunakan untuk mengubah nilai atribut private secara terkendali. Dengan cara ini, akses ke data dapat diatur dan divalidasi agar tidak menyebabkan kesalahan logika atau data yang tidak valid.

3. Bagaimana cara class Produk mendukung pengembangan aplikasi POS yang lebih kompleks?
   **Jawaban:** Class Produk menjadi dasar representasi data barang di aplikasi POS (Point of Sale). Dengan adanya atribut seperti kode, nama, harga, dan stok serta method seperti tambahStok() dan kurangiStok(), class ini bisa dikembangkan untuk fitur-fitur lanjut seperti transaksi penjualan, laporan stok, atau integrasi dengan database tanpa perlu mengubah struktur dasarnya.
