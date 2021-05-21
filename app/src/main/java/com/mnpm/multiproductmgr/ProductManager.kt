package com.mnpm.multiproductmgr

import android.content.Intent
import java.util.ArrayList

object ProductManager {

    private var productSelected: Int = 0
    var products: ArrayList<Product> = ArrayList()

    init {
        loadExampleData()
    }

    fun removeSelectedProduct() {
        products.removeAt(productSelected)
    }

    fun addNewProduct(p: Product) {
        products.add(p)
    }

    fun getProduct(): Product {
        return products[productSelected]
    }

    fun getProductSelected(): Int {
        return productSelected
    }

    fun setProductSelected(position: Int) {
        productSelected = position
    }

    fun productToIntent(intent: Intent): Intent {
        val p = getProduct()
        intent.apply {
            putExtra("carName", p.name)
            putExtra("carType", p.type!!.ordinal)
            putExtra("carYear", p.productionYear)
            putExtra("carPower", p.power)
            putExtra("carSpeed", p.maxVelocity)
            putExtra("carMass", p.mass)
        }
        return intent
    }

    fun intentToProduct(intent: Intent?): Product? {
        if (intent == null)
            return null
        for (s in listOf("carName", "carType", "carYear", "carPower", "carSpeed", "carMass")) {
            if (!intent.hasExtra(s))
                return null
        }
        val bundle = intent.extras
        val name = bundle?.getString("carName")
        val type = bundle?.getInt("carType")
        val year = bundle?.getInt("carYear")
        val power = bundle?.getInt("carPower")
        val speed = bundle?.getInt("carSpeed")
        val mass = bundle?.getInt("carMass")
        return Product(name!!, ProductTypes.values()[type!!], power!!, speed!!, mass!!, year!!)
    }

    private fun loadExampleData() {
        products.add(Product("Ford Focus Mk2",
                ProductTypes.HATCHBACK, 110, 180, 1900, 2003))
        products.add(Product("Mazda MX-5",
                ProductTypes.CONVERTIBLE, 160, 240, 950, 2018))
        products.add(Product("BMW M2",
                ProductTypes.COUPE, 220, 260, 1500, 2013))
        products.add(Product("Volvo V70",
                ProductTypes.WAGON, 130, 180, 1800, 2005))
    }

}