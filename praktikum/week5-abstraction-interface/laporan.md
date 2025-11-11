# Laporan Praktikum Minggu 5
Topik: Abstraction (Abstract Class & Interface)

## Identitas
- Nama  : Novi Fitriyani
- NIM   : 240202843
- Kelas : 3IKRA

---

## Tujuan
- Mahasiswa mampu **menjelaskan perbedaan abstract class dan interface**.
- Mahasiswa mampu **mendesain abstract class dengan method abstrak** sesuai kebutuhan kasus.
- Mahasiswa mampu **membuat interface dan mengimplementasikannya pada class**.
- Mahasiswa mampu **menerapkan multiple inheritance melalui interface** pada rancangan kelas.
- Mahasiswa mampu **mendokumentasikan kode** (komentar kelas/method, README singkat pada folder minggu).

---

## Dasar Teori
**Abstraksi** adalah proses menyederhanakan kompleksitas dengan menampilkan elemen penting dan menyembunyikan detail implementasi.
- **Abstract class**: tidak dapat diinstansiasi, dapat memiliki method abstrak (tanpa badan) dan non-abstrak. Dapat menyimpan state (field).
- **Interface**: kumpulan kontrak (method tanpa implementasi konkret). Sejak Java 8 mendukung default method. Mendukung **multiple inheritance** (class dapat mengimplementasikan banyak interface).
- Gunakan **abstract class** bila ada _shared state_ dan perilaku dasar; gunakan **interface** untuk mendefinisikan kemampuan/kontrak lintas hierarki.

Dalam konteks Agri-POS, **Pembayaran** dapat dimodelkan sebagai abstract class dengan method abstrak `prosesPembayaran()` dan `biaya()`. Implementasi konkritnya: `Cash` dan `EWallet`. Kemudian, interface seperti `Validatable` (mis. verifikasi OTP) dan `Receiptable` (mencetak bukti) dapat diimplementasikan oleh jenis pembayaran yang relevan.


---

## Langkah Praktikum
1. **Abstract Class – Pembayaran**
   - Buat `Pembayaran` (abstract) dengan field `invoiceNo`, `total` dan method:
     - `double biaya()` (abstrak) → biaya tambahan (fee).
     - `boolean prosesPembayaran()` (abstrak) → mengembalikan status berhasil/gagal.
     - `double totalBayar()` (konkrit) → `return total + biaya();`.

2. **Subclass Konkret**
   - `Cash` → biaya = 0, proses = selalu berhasil jika `tunai >= totalBayar()`.
   - `EWallet` → biaya = 1.5% dari `total`; proses = membutuhkan validasi.

3. **Interface**
   - `Validatable` → `boolean validasi();` (contoh: OTP).
   - `Receiptable` → `String cetakStruk();`

4. **Multiple Inheritance via Interface**
   - `EWallet` mengimplementasikan **dua interface**: `Validatable`, `Receiptable`.
   - `Cash` setidaknya mengimplementasikan `Receiptable`.

5. **Main Class**
    - Buat `MainAbstraction.java` untuk mendemonstrasikan pemakaian `Pembayaran` (polimorfik).
    - Tampilkan hasil proses dan struk. Di akhir, panggil `CreditBy.print("[NIM]", "[Nama]")`.

6. **Commit dan Push**
   - Commit dengan pesan: `week5-abstraction-interface`. 

## Kode Program
**Pembayaran.java**
```java
package com.upb.agripos.model.pembayaran;

public abstract class Pembayaran {
    protected String invoiceNo;
    protected double total;

    public Pembayaran(String invoiceNo, double total) {
        this.invoiceNo = invoiceNo;
        this.total = total;
    }

    public abstract double biaya();               // fee/biaya tambahan
    public abstract boolean prosesPembayaran();   // proses spesifik tiap metode

    public double totalBayar() {
        return total + biaya();
    }

    public String getInvoiceNo() { return invoiceNo; }
    public double getTotal() { return total; }
}
```

**Cash.java**
```java
package com.upb.agripos.model.pembayaran;


import com.upb.agripos.model.kontrak.Receiptable;

public class Cash extends Pembayaran implements Receiptable {
    private double tunai;

    public Cash(String invoiceNo, double total, double tunai) {
        super(invoiceNo, total);
        this.tunai = tunai;
    }

    @Override
    public double biaya() {
        return 0.0;
    }

    @Override
    public boolean prosesPembayaran() {
        return tunai >= totalBayar(); // sederhana: cukup uang tunai
    }

    @Override
    public String cetakStruk() {
        return String.format("INVOICE %s | TOTAL: %.2f | BAYAR CASH: %.2f | KEMBALI: %.2f",
                invoiceNo, totalBayar(), tunai, Math.max(0, tunai - totalBayar()));
    }
}
```

**EWallet.java**
```java
package com.upb.agripos.model.pembayaran;

import com.upb.agripos.model.kontrak.Validatable;
import com.upb.agripos.model.kontrak.Receiptable;

public class EWallet extends Pembayaran implements Validatable, Receiptable {
    private String akun;
    private String otp; // sederhana untuk simulasi

    public EWallet(String invoiceNo, double total, String akun, String otp) {
        super(invoiceNo, total);
        this.akun = akun;
        this.otp = otp;
    }

    @Override
    public double biaya() {
        return total * 0.015; // 1.5% fee
    }

    @Override
    public boolean validasi() {
        return otp != null && otp.length() == 6; // contoh validasi sederhana
    }

    @Override
    public boolean prosesPembayaran() {
        return validasi(); // jika validasi lolos, anggap berhasil
    }

    @Override
    public String cetakStruk() {
        return String.format("INVOICE %s | TOTAL+FEE: %.2f | E-WALLET: %s | STATUS: %s",
                invoiceNo, totalBayar(), akun, prosesPembayaran() ? "BERHASIL" : "GAGAL");
    }
}
```

**TransferBank.java**
```java
package com.upb.agripos.model.pembayaran;

import com.upb.agripos.model.kontrak.Receiptable;
import com.upb.agripos.model.kontrak.Validatable;

public class TransferBank extends Pembayaran implements Receiptable, Validatable {
    private String akunBank;
    private boolean valid; // simulasi validasi
    private static final double BIAYA_TRANSFER = 3500;

    public TransferBank(String invoiceNo, double total, String akunBank, boolean valid) {
        super(invoiceNo, total);
        this.akunBank = akunBank;
        this.valid = valid;
    }

    @Override
    public double biaya() { return BIAYA_TRANSFER; }

    @Override
    public boolean validasi() { return valid; }

    @Override
    public boolean prosesPembayaran() { return validasi(); }

    @Override
    public String cetakStruk() {
        return String.format(
            "INVOICE %s | TOTAL+BIAYA: %.2f | TRANSFER BANK: %s | STATUS: %s",
            invoiceNo, totalBayar(), akunBank, prosesPembayaran() ? "BERHASIL" : "GAGAL"
        );
    }
}
```

**Validatable.java**
```java
package com.upb.agripos.model.kontrak;

public interface Validatable {
    boolean validasi(); // misal validasi OTP/ PIN
}
```

**Receiptable.java**
```java
package com.upb.agripos.model.kontrak;

public interface Receiptable {
    String cetakStruk();
}
```

**CreditBy.java**
```java
package com.upb.agripos.util;

public class CreditBy {
    public static void print(String nim, String nama) {
        System.out.println("\ncredit by: " + nim + " - " + nama);
    }
}
```

**MainAbstraction.java**
```java
package com.upb.agripos;

import com.upb.agripos.model.pembayaran.*;
import com.upb.agripos.model.kontrak.*;
import com.upb.agripos.util.CreditBy;

public class MainAbstraction {
    public static void main(String[] args) {
        Pembayaran cash = new Cash("INV-001", 100000, 120000);
        Pembayaran ew = new EWallet("INV-002", 150000, "user@ewallet", "123456");
        Pembayaran bank = new TransferBank("INV-003", 200000, "BankABC-123", true);


        System.out.println(((Receiptable) cash).cetakStruk());
        System.out.println(((Receiptable) ew).cetakStruk());
        System.out.println(((Receiptable) bank).cetakStruk());

    CreditBy.print("240202843", "Novi Fitriyani");
    }
}
```

---

## Hasil Eksekusi
screenshots/Screenshot 2025-11-11 215637.png screenshots/Screenshot 2025-11-11 215656.png screenshots/Screenshot 2025-11-11 215751.png screenshots/Screenshot 2025-11-11 220145.png screenshots/Screenshot 2025-11-11 220204.png screenshots/Screenshot 2025-11-11 220226.png screenshots/Screenshot 2025-11-11 220244.png screenshots/Screenshot 2025-11-11 220338.png
---

## Analisis
- Kelas `pembayaran` berfungsi sebagai abstraksi umum dari berbagai jenis metode pembayaran.
- interface `Validatable` dan `Receiptable` menunjukkan bahwa kelas bisa memiliki lebih dari satu perilaku tanpa pewarisan berganda dari kelas.
- `Cash` tidak perlu validasi, sedangkan `EWallet` dan `TransferBank` wajib melakukan validasi akun dan nomor rekening.
- Implementasi `cetakStruk()` pada setiap kelas menampilkan hasil transaksi sesuai jenis pembayaran.
- Kendala awal yang muncul adalah kesalahan struktur paket 
(package mismatch) dan duplikasi deklarasi package, diselesaikan dengan menata ulang direktori dan memperbaiki nama `package` di setiap file.

## Kesimpulan
Dengan menerapkan abstraction dan interface, kode menjadi:
- Lebih terstuktur (setiap metode pembayaran memiliki perilaku spesifik).
- Lebih fleksibel untuk dikembangkan (mudah menambah metode pembayaran baru)
- Menunjukan praktik multiple inheritance yang aman menggunakan interface
---

## Quiz
1. Jelaskan perbedaan konsep dan penggunaan **abstract class** dan **interface**.  
   **Jawaban:** Abstract class digunakan untuk mendefinisikan dasar perilaku yang dapat diwariskan dengan sebagian implementasi, sedangkan interface hanya mendefinisikan kontrak perilaku tanpa implementasi.
2. Mengapa **multiple inheritance** lebih aman dilakukan dengan interface pada Java?  
   **Jawaban:** Karena interface tidak membawa state (atribut) sehingga tidak menimbulkan konflik pewarisan seperti pada multiple class inheritance
3. Pada contoh Agri-POS, bagian mana yang **paling tepat** menjadi abstract class dan mana yang menjadi interface? Jelaskan alasannya.  
   **Jawaban:**
   - `Pembayaran` → abstract class, karena menjadi dasar umum semua pembayaran dan memiliki implementasi sebagai(`totalBayar()`)
   - `Validatable` & `Receitable` → interface, karena hanya mendefinisikan perilaku tambahan tanpa implementasi bawaan.