@startuml
title Sequence Diagram
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