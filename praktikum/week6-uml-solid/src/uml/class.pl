@startuml
title Class Diagram

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