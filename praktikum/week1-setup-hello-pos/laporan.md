# Laporan Praktikum Minggu 1 (sesuaikan minggu ke berapa?)
Topik: Pemrogramman dalam 3 Paradigma

## Identitas
- Nama  : Novi Fitriyani
- NIM   : 240202843
- Kelas : 3IKRA

---

## Tujuan
Mahasiswa memahami dan menerapkan konsep pemrograman dalam tiga paradigma:
1. Paradigma Prosedural
2. Paradigma Berorientasi Objek (OOP)
3. Paradigma Fungsional
dengan membuat program sederhana "Hello POS World"
---

## Dasar Teori
1. Paradigma Prosedural: Pemrograman yang berfokus pada prosedur atau fungsi. 
2. Paradigma OOP: Pemrograman berbasis objek yang menggunakan class dan object.  
3. Paradigma Fungsional: Pemrograman yang berfokus pada penggunaan fungsi tanpa efek samping (side effects).

---

## Langkah Praktikum
1. Membuat folder project:
    praktikum/week1-setup-hello-pos/src/main/java/com/upb/agripos/
2. Membuat tiga file java:
    - HelloProcedural.java
    - HelloOOP.java
    - HelloFunctional.java
3. Menjalankan masing masing program dan mengambil screenshot hasil eksekusi
4. Menyimpan screenshot di folder:
    praktikum/week1-setup-hello-pos/screenshots/
5. Membuat file laporan (laporan-week1.md) di folder:
    praktikum/week1-setup-hello-pos/
6. Commit dengan pesan: 
    Menambahkan tugas week 1: Hello POS dalam 3 paradigma

---

## Kode Program
HelloProcedural.java
```java
package com.upb.agripos;

public class HelloProcedural {
    public static void main(String[] args) {
        String nim = "240202843";
        String nama = "Novi Fitriyani";
        String[] produk = {"Beras", "Pupuk", "Benih"};
        int[] harga = {10000, 15000, 12000};
        int total = 0;
        System.out.println("Hello POS World");
        System.out.println("NIM: " + nim + ", Nama: " + nama);
        System.out.println("Daftar Produk:");
        for (int i = 0; i < produk.length; i++) {
            System.out.println("- " + produk[i] + ": " + harga[i]);
            total += harga[i];
        }
        System.out.println("Total harga semua produk: " + total);
    }
}
```

HelloOOP.java
```java
package com.upb.agripos;

class Produk {
    String nama;
    int harga;

    Produk(String nama, int harga) {
        this.nama = nama;
        this.harga = harga;
    }
}

public class HelloOOP {
    public static void main(String[] args) {
        String nim = "240202843";
        String namaMhs = "Novi Fitriyani";
        Produk[] daftar = {
            new Produk("Beras", 10000),
            new Produk("Pupuk", 15000),
            new Produk("Benih", 12000)
        };
        int total = 0;
        System.out.println("Hello POS World");
        System.out.println("NIM: " + nim + ", Nama: " + namaMhs);
        System.out.println("Daftar Produk:");
        for (Produk p : daftar) {
            System.out.println("- " + p.nama + ": " + p.harga);
            total += p.harga;
        }
        System.out.println("Total harga semua produk: " + total);
    }
}
```

HelloFunctional.java
```java
package com.upb.agripos;

import java.util.*;
import java.util.stream.*;

public class HelloFunctional {
    public static void main(String[] args) {
        String nim = "240202843";
        String nama = "Novi Fitriyani";
        List<String> produk = Arrays.asList("Beras", "Pupuk", "Benih");
        List<Integer> harga = Arrays.asList(10000, 15000, 12000);
        System.out.println("Hello POS World");
        System.out.println("NIM: " + nim + ", Nama: " + nama);
        System.out.println("Daftar Produk:");
        IntStream.range(0, produk.size())
            .forEach(i -> System.out.println("- " + produk.get(i) + ": " + harga.get(i)));
        int total = harga.stream().mapToInt(Integer::intValue).sum();
        System.out.println("Total harga semua produk: " + total);
    }
}


---
```

## Hasil Eksekusi
(Sertakan screenshot hasil eksekusi program.  
![Screenshot hasil](screenshots/hasil.png)
)
---

## Analisis
- Ketiga program menghasilkan output yang sama tetapi menggunakan pendekatan berbeda.  
- Paradigma Prosedural lebih sederhan namun minim reusable code.  
- Paradigma OOP lebih modular dan mempermudah pemeliharaan kode.
- Paradigma Fungsional membuat kode lebih ringkas dan memanfaatkan fitur modern Java seperti stream API
- Kendala: Menyesuaikan struktur folder dan package agar program berjalan tanpa error.
- Solusi: Memastikan struktur folder sesuai ketentuan dan menambahkan package declaration.   
---

## Kesimpulan
Penggunaan paradigma yang berbeda memberikan fleksibilitas dalam menulis kode. Pemilihan paradigma harus disesuaikan dengan kebutuhan proyek kompleksitas program.

---

## Quiz
1. Apakah OOP selalu lebih baik dari prosedural?  
   **Jawaban:**  Tidak selalu. OOP lebih baik untuk aplikasi besar dan kompleks karena mendukung modularitas, enkapsulasi, dan reuse kode. Namun untuk program sederhana, prosedural lebih cepat dibuat dan mudah dipahami.

2. Kapan functional programming lebih cocok digunakan dibanding OOP atau prosedural?  
   **Jawaban:**  Functional programming lebih cocok digunakan ketika aplikasi membutuhkan pengolahan data yang besar, paralelisme, atau transformasi data yang kompleks, karena memanfaatkan fungsi murni dan stream processing yang efisien.

3. Bagaimana paradigma (prosedural, OOP, fungsional) memengaruhi maintainability dan scalability aplikasi?  
   **Jawaban:**  
   - Prosedural: Maintainability rendah untuk aplikasi besar, scalability terbatas.
   - OOP: Maintainability tinggi karena struktur modular, scalability baik dengan desain class yang baik.
   - Fungsional: Maintainability tinggi karena kode lebih ringkas dan minim efek samping, scalability sangat baik untuk aplikasi yang memproses data besar.

4. Mengapa OOP lebih cocok untuk mengembangkan aplikasi POS dibanding prosedural?  
   **Jawaban:**  OOP cocok untuk aplikasi POS karena struktur modularnya memungkinkan pengelolaan komponen seperti produk, transaksi, dan pengguna secara terpisah. Ini memudahkan pengembangan, pemeliharaan, dan penambahan fitur baru.

5. Bagaimana paradigma fungsional dapat membantu mengurangi kode berulang (*boilerplate code*)?  
   **Jawaban:**  Paradigma fungsional menggunakan fungsi murni, lambda expression, dan stream API sehingga banyak logika dapat ditulis secara ringkas tanpa harus membuat kode panjang yang berulang, sehingga meminimalkan boilerplate code.
