@startuml
title Use Case Diagram
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