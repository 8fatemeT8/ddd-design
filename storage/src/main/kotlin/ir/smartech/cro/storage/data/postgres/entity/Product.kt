package ir.smartech.cro.storage.data.postgres.entity

import jakarta.persistence.*

@Entity
@Table(name = "products")
class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    var name: String? = null

    var description: String? = null
}