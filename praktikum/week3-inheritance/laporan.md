# Laporan Praktikum Minggu 3
Topik: Inheritance (Kategori Produk)

## Identitas
- Nama  : Novi Fitriyani
- NIM   : 240202843
- Kelas : 3IKRA

---

## Tujuan
- Mahasiswa mampu menjelaskan konsep inheritance (pewarisan class) dalam OOP.
- Mahasiswa mampu membuat superclass dan subclass untuk produk pertanian.
- Mahasiswa mampu mendemonstrasikan hierarki class melalui contoh kode.
- Mahasiswa mampu menggunakan `super` untuk memanggil konstruktor dan method parent class.
- Mahasiswa mampu membuat laporan praktikum yang menjelaskan perbedaan penggunaan inheritance dibanding class tunggal.
---

## Dasar Teori
Inheritance adalah mekanisme dalam OOP yang memungkinkan suatu class mewarisi atribut dan method dari class lain.
- **Superclass**: class induk yang mendefinisikan atribut umum.
- **Subclass**: class turunan yang mewarisi atribut/method superclass, dan dapat menambahkan atribut/method baru.
- `super` digunakan untuk memanggil konstruktor atau method superclass.
Dalam konteks Agri-POS, kita dapat membuat class `Produk` sebagai superclass, kemudian `Benih`, `Pupuk`, dan `AlatPertanian` sebagai subclass. Hal ini membuat kode lebih reusable dan terstruktur.

---

## Langkah Praktikum
1. **Membuat Superclass Produk**  
Membuat class Produk yang berisi atribut umum:
`kode`, `nama`, `harga`, dan `stok`.
3. **Membuat Subclass**  
   - `Benih.java` → atribut tambahan: varietas.  
   - `Pupuk.java` → atribut tambahan: jenis.  
   - `AlatPertanian.java` → atribut tambahan: material. 
4. **Membuat Main Class**  
Membuat class `MainInheritance` untuk menginstansiasi dan menampilkan data dari setiap subclass.
5. **Menambahkan CreditBy**  
Class `CreditBy` digunakan untuk mencetak identitas mahasiswa pada akhir program.
6. **Commit dan Push ke GitHub**
---

## Kode Program
**Benih.java**
```java
package com.upb.agripos.model;

public class Benih extends Produk {
    private String varietas;

    public Benih(String kode, String nama, double harga, int stok, String varietas) {
        super(kode, nama, harga, stok);
        this.varietas = varietas;
    }

    public String getVarietas() { return varietas; }
    public void setVarietas(String varietas) { this.varietas = varietas; }
}
```
**Pupuk.java**
```java
package com.upb.agripos.model;

public class Pupuk extends Produk {
    private String jenis;

    public Pupuk(String kode, String nama, double harga, int stok, String jenis) {
        super(kode, nama, harga, stok);
        this.jenis = jenis;
    }

    public String getJenis() { return jenis; }
    public void setJenis(String jenis) { this.jenis = jenis; }
}
```
**AlatPertanian.java**
```java
package com.upb.agripos.model;

public class AlatPertanian extends Produk {
    private String material;

    public AlatPertanian(String kode, String nama, double harga, int stok, String material) {
        super(kode, nama, harga, stok);
        this.material = material;
    }

    public String getMaterial() { return material; }
    public void setMaterial(String material) { this.material = material; }
}
```
**MainInheritance.java**
```java 
package com.upb.agripos;

import com.upb.agripos.model.*;
import com.upb.agripos.util.CreditBy;

public class MainInheritance {
    public static void main(String[] args) {
        Benih b = new Benih("BNH-001", "Benih Padi IR64", 25000, 100, "IR64");
        Pupuk p = new Pupuk("PPK-101", "Pupuk Urea", 350000, 40, "Urea");
        AlatPertanian a = new AlatPertanian("ALT-501", "Cangkul Baja", 90000, 15, "Baja");

        System.out.println("Benih: " + b.getNama() + " Varietas: " + b.getVarietas());
        System.out.println("Pupuk: " + p.getNama() + " Jenis: " + p.getJenis());
        System.out.println("Alat Pertanian: " + a.getNama() + " Material: " + a.getMaterial());

        CreditBy.print("<NIM>", "<Nama Mahasiswa>");
    }
}

---
```
## Hasil Eksekusi
Screenshot
---

## Analisis
- Program menggunakan konsep inheritance di mana `Benih`, `Pupuk`, dan `AlatPertanian` mewarisi atribut dari `Produk`.
- Keyword `super` digunakan untuk mengakses konstruktor dari superclass agar atribut dasar dapat diinisialisasi dengan benar.
- Subclass menambahkan atribut baru yang spesifik sesuai jenis produk.
- Pendekatan ini lebih efisien dibanding membuat tiga class terpisah tanpa hubungan, karena atribut umum (`kode`, `nama`, `harga`, `stok`) tidak perlu ditulis ulang.
- Kendala: pada awalnya terjadi error karena struktur paket belum sesuai, diperbaiki dengan menyesuaikan nama folder dengan `package`.

## Kesimpulan
Dengan menerapkan inheritance, program menjadi lebih terstruktur, efisien, dan mudah dikembangkan. Superclass `Produk` dapat digunakan kembali oleh subclass lainnya, sementara subclass tetap dapat menambahkan atribut atau perilaku khusus sesuai kebutuhannya.

---

## Quiz
1. Apa keuntungan menggunakan inheritance dibanding membuat class terpisah tanpa hubungan?  
   **Jawaban:** Inheritance memungkinkan penggunaan ulang kode (reusability) dan mengurangi duplikasi atribut/metode yang sama di beberapa class. 

2. Bagaimana cara subclass memanggil konstruktor superclass?  
   **Jawaban:** Dengan menggunakan keyword `super()` di dalam konstruktor subclass.

3. Berikan contoh kasus di POS pertanian selain Benih, Pupuk, dan Alat Pertanian yang bisa dijadikan subclass.  
   **Jawaban:** Contohnya `ObatTanaman` dengan atribut tambahan seperti `kandungan` dan `dosis`.
