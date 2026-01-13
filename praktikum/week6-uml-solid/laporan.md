# Laporan Praktikum Minggu 6
Topik: Desain Arsitektur Sistem dengan UML dan prinsip Solid

## Identitas
- Nama  : Novi Fitriyani
- NIM   : 240202843
- Kelas : 3IKRA
 
---

## Tujuan
1. Mahasiswa mampu mengidentifikasi kebutuhan sistem ke dalam diagram UML.
2. Mahasiswa mampu menggambar UML Class Diagram dengan relasi antar class yang tepat.
3. Mahasiswa mampu menjelaskan prinsip desain OOP (SOLID).
4. Mahasiswa mampu menerapkan minimal dua prinsip SOLID dalam kode program.

---

## Dasar Teori
1. Object-Oriented Programming (OOP) adalah paradigma pemrograman yang berfokus pada objek dan class.
2. UML (Unified Modeling Language) digunakan untuk memvisualisasikan, merancang, dan mendokumentasikan sistem perangkat lunak.
3. Use Case Diagram digunakan untuk menggambarkan interaksi antara aktor dengan sistem.
4. Activity Diagram digunakan untuk menunjukkan alur aktivitas atau proses bisnis dalam sistem.
5. Sequence Diagram digunakan untuk menggambarkan urutan interaksi antar objek.
6. Class Diagram digunakan untuk memodelkan struktur class dan hubungan antar class dalam sistem.
---

## Langkah Praktikum
1. Menganalisis kebutuhan sistem Agri-POS berdasarkan studi kasus yang diberikan.
2. Menentukan aktor dan fungsi utama sistem.
3. Membuat Use Case Diagram menggunakan PlantUML.
4. Membuat Activity Diagram untuk menggambarkan alur proses checkout.
5. Membuat Sequence Diagram untuk menggambarkan interaksi antar objek saat pembayaran.
6. Membuat Class Diagram untuk merancang struktur class dalam sistem.
7. Menjalankan PlantUML di Visual Studio Code untuk memastikan diagram dapat dirender dengan benar.

---

## Kode Perancangan Sistem (PlantUML)

**Use Case Diagram**
```PlantUML
@startuml
left to right direction
actor Kasir
actor Admin
actor "Payment Gateway" as PG


rectangle "Agri-POS System" {
Kasir --> (Login)
Admin --> (Login)
Admin --> (Kelola Produk)
Admin --> (Lihat Laporan)
Kasir --> (Buat Transaksi)
Kasir --> (Tambah Produk ke Keranjang)
Kasir --> (Checkout)
(Checkout) --> (Pilih Metode Pembayaran) : <<include>>
(Checkout) --> (Cetak Struk) : <<include>>
(Pilih Metode Pembayaran) --> PG
}
@enduml
---
```

**Activity Diagram**
```PlantUML
@startuml
title Activity Diagram - Proses Checkout Agri-POS

|Kasir|
start
:Pilih produk;
:Masukkan produk ke keranjang;

|Sistem|
:Periksa stok;
if (Stok tersedia?) then (Ya)

  |Kasir|
  :Pilih metode pembayaran;

  |Sistem|
  if (Metode pembayaran?) then (Tunai)
      :Proses pembayaran tunai;
      :Update status transaksi;
  else (E-Wallet)
      |Payment Gateway|
      :Validasi saldo;

      if (Saldo cukup?) then (Ya)
          :Konfirmasi pembayaran;
          |Sistem|
          :Update status transaksi;
      else (Tidak)
          |Sistem|
          :Pembayaran gagal;
          stop
      endif
  endif

  |Sistem|
  :Cetak struk;
  stop

else (Tidak)
  :Stok habis;
  stop
endif
@enduml
---
```

**Sequence Diagram**
```PlantUML
@startuml
actor Kasir
participant POSController
participant PaymentService
participant PaymentMethod
participant CashPayment
participant EWalletPayment
participant "Payment Gateway" as PG

Kasir -> POSController : checkout()
POSController -> PaymentService : processPayment(total, metode)

alt Pembayaran Tunai
    PaymentService -> CashPayment : pay(total)
    CashPayment --> PaymentService : sukses
else Pembayaran E-Wallet
    PaymentService -> EWalletPayment : pay(total)
    EWalletPayment -> PG : requestPayment(total)

    alt Saldo cukup
        PG --> EWalletPayment : success
        EWalletPayment --> PaymentService : sukses
    else Saldo tidak cukup
        PG --> EWalletPayment : failed
        EWalletPayment --> PaymentService : gagal
    end
end

PaymentService --> POSController : status pembayaran
POSController --> Kasir : tampilkan hasil
@enduml
---
```

**Class Diagram**
```PlantUML
@startuml
title Class Diagram - Agri-POS (Based on Example)

skinparam classAttributeIconSize 0
skinparam linetype ortho

' =========================
' Product & Repository
' =========================
class Product {
  -code : String
  -name : String
  -category : String
  -price : Money
  -stock : int
  +reduceStock(qty : int) : void
  +getInfo() : String
}

interface ProductRepository {
  +save(p : Product) : void
  +findByCode(code : String) : Product
}

class JdbcProductRepository {
  -connection : Database
  +save(p : Product) : void
  +findByCode(code : String) : Product
}

JdbcProductRepository ..|> ProductRepository

class ProductService {
  -repo : ProductRepository
  +findProduct(code : String) : Product
  +updateStock(code : String, qty : int) : void
}

ProductService --> ProductRepository : uses
ProductService --> Product : manages

' =========================
' Payment
' =========================
interface PaymentMethod {
  +pay(amount : Money) : Receipt
}

class CashPayment {
  -amount : Money
  +pay(amount : Money) : Receipt
}

class EWalletPayment {
  -account : String
  -fee : double
  +validate() : boolean
  +pay(amount : Money) : Receipt
}

CashPayment ..|> PaymentMethod
EWalletPayment ..|> PaymentMethod

class PaymentFactory {
  +create(type : PaymentType) : PaymentMethod
}

class PaymentService {
  -factory : PaymentFactory
  +pay(amount : Money, type : PaymentType) : Receipt
}

PaymentService --> PaymentFactory : uses
PaymentFactory --> PaymentMethod : creates

' =========================
' Checkout
' =========================
class CheckoutService {
  -productService : ProductService
  -paymentService : PaymentService
  +checkout(items : List, type : PaymentType) : Receipt
}

CheckoutService --> ProductService : uses
CheckoutService --> PaymentService : uses

@enduml
---
```

## Hasil Eksekusi
**Screenshot**
<img width="997" height="634" alt="image" src="https://github.com/user-attachments/assets/92a15af4-4a05-4cb2-8b81-b15bc34799f5" />



---

## Analisis
Desain sistem Agri-POS menunjukkan penerapan prinsip OOP dan SOLID. Use Case Diagram menggambarkan batasan hak akses antara kasir dan admin. Activity dan Sequence Diagram memperlihatkan alur proses checkout secara detail termasuk skenario kegagalan. Class Diagram menerapkan abstraksi melalui interface sehingga sistem mudah dikembangkan.

**Penerapan Prinsip SOLID**
1. Single Responsibility Principle (SRP): Setiap kelas memiliki satu tanggung jawab utama, misalnya PaymentService hanya menangani proses pembayaran.
2. Open/Closed Principle (OCP): Penambahan metode pembayaran baru dapat dilakukan tanpa mengubah kelas PaymentService.
3. Liskov Substitution Principle (LSP): CashPayment dan EWalletPayment dapat menggantikan PaymentMethod tanpa mengubah perilaku sistem.
4. Interface Segregation Principle (ISP): Interface PaymentMethod hanya berisi method yang relevan.
5. Dependency Inversion Principle (DIP): PaymentService bergantung pada interface PaymentMethod, bukan implementasi konkret.
---

## Kesimpulan
Berdasarkan praktikum yang telah dilakukan, dapat disimpulkan bahwa perancangan sistem menggunakan UML dan prinsip SOLID menghasilkan desain sistem yang terstruktur, modular, dan mudah dikembangkan. Sistem Agri-POS yang dirancang telah memenuhi kebutuhan fungsional serta non-fungsional yang ditetapkan.

---

## Quiz
1. Jelaskan perbedaan aggregation dan composition serta berikan contoh penerapannya pada desain Anda.  
   **Jawaban:**  Aggregation dan composition sama-sama merupakan hubungan has-a antar kelas, namun memiliki tingkat ketergantungan yang berbeda.
   - Aggregation adalah hubungan kepemilikan lemah, di mana objek masih dapat berdiri sendiri meskipun objek induknya tidak ada.
   **Contoh pada desain Agri-POS:**
   PaymentService memiliki relasi aggregation terhadap PaymentMethod. Objek CashPayment atau EWalletPayment dapat digunakan atau diganti tanpa harus menghapus PaymentService.
   - Composition adalah hubungan kepemilikan kuat, di mana objek tidak dapat eksis tanpa objek induknya.
   **Contoh pada desain Agri-POS:**
   CheckoutService memiliki relasi composition terhadap data transaksi checkout. Jika proses checkout dihapus, maka detail transaksi di dalamnya juga ikut hilang.

2. Bagaimana prinsip Open/Closed dapat memastikan sistem mudah dikembangkan?  
   **Jawaban:**  Prinsip Open/Closed menyatakan bahwa sebuah kelas harus terbuka untuk pengembangan tetapi tertutup untuk perubahan. Artinya, fitur baru dapat ditambahkan tanpa mengubah kode yang sudah ada.
   Pada desain Agri-POS, prinsip ini diterapkan pada modul pembayaran. Interface PaymentMethod memungkinkan penambahan metode pembayaran baru, seperti BankTransferPayment, tanpa perlu mengubah kelas PaymentService. Cukup dengan membuat kelas baru yang mengimplementasikan PaymentMethod, sistem dapat diperluas tanpa merusak fungsi yang sudah berjalan.

3. Mengapa Dependency Inversion Principle (DIP) meningkatkan testability? Berikan contoh penerapannya.
   **Jawaban:**  Dependency Inversion Principle meningkatkan testability karena kelas tingkat tinggi tidak bergantung langsung pada implementasi konkret, melainkan pada abstraksi (interface). Hal ini memungkinkan penggunaan mock object atau dummy object saat pengujian.
   Pada desain Agri-POS, PaymentService tidak bergantung langsung pada CashPayment atau EWalletPayment, melainkan pada interface PaymentMethod. Saat melakukan pengujian, dapat digunakan implementasi palsu (mock PaymentMethod) untuk mensimulasikan pembayaran tanpa benar-benar terhubung ke sistem pembayaran nyata, sehingga pengujian menjadi lebih mudah dan terkontrol.
