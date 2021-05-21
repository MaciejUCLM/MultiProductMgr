package com.mnpm.multiproductmgr

import android.content.Intent
import java.util.ArrayList

object ProductManager {

    private var productSelected: Int = 0
    var products: ArrayList<Product> = ArrayList()

    fun getProduct(): Product {
        return products[productSelected]
    }

    fun getProductSelected(): Int {
        return productSelected
    }

    fun setProductSelected(position: Int) {
        productSelected = position
    }

    fun putProductToIntent(intent: Intent): Intent {
        intent.apply {
            putExtra("xd", "")
        }
        return intent
    }

}