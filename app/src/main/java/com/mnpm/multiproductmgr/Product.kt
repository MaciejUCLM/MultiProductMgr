package com.mnpm.multiproductmgr

enum class ProductTypes {
    WAGON {
        override fun getIcon(): Int = R.drawable.ic_menu_camera
        override fun getString(): String = "Station Wagon"
    },
    HATCHBACK {
        override fun getIcon(): Int = R.drawable.ic_menu_camera
        override fun getString(): String = "Hatchback"
    },
    COUPE {
        override fun getIcon(): Int = R.drawable.ic_menu_camera
        override fun getString(): String = "Coupe"
    },
    CONVERTIBLE {
        override fun getIcon(): Int = R.drawable.ic_menu_camera
        override fun getString(): String = "Convertible"
    };

    abstract fun getIcon(): Int
    abstract fun getString(): String
}

class Product {

    var name: String? = null
    var type: ProductTypes? = null
    var power: Int? = null
    var maxVelocity: Int? = null
    var mass: Int? = null
    var productionYear: Int? = null

    constructor(name: String, type: ProductTypes, power: Int, maxVelocity: Int, mass: Int, productionYear: Int) {
        this.name = name
        this.type = type
        this.power = power
        this.maxVelocity = maxVelocity
        this.mass = mass
        this.productionYear = productionYear
    }

}