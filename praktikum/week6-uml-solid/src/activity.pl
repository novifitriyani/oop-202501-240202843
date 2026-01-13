@startuml
title Activity Diagram

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