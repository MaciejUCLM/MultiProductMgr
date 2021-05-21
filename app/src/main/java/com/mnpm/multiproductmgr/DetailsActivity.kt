package com.mnpm.multiproductmgr

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar

class DetailsActivity : AppCompatActivity(), DeleteDialogListenerI {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        // Get a support ActionBar corresponding to this toolbar and enable the Up button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val carName = findViewById<TextView>(R.id.carNameText)
        val carImage = findViewById<ImageView>(R.id.carImage)
        val carType = findViewById<TextView>(R.id.carModelText)
        val carPower = findViewById<TextView>(R.id.carEngineText)
        val carOther = findViewById<TextView>(R.id.carDescrText)

        // load provided product
        val product = ProductManager.intentToProduct(intent)
        product?.let {
            carName.text = product.name
            carImage.setImageResource(product.type!!.getIcon())
            carType.text = product.type!!.getString()
            carPower.text = resources.getString(R.string.horsepower, product.power)
            carOther.text = resources.getString(R.string.other_details,
                    product.productionYear, product.mass, product.maxVelocity)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.details_view, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_edit -> {
                val i = ProductManager.productToIntent(
                        Intent(this, EditorActivity::class.java), ProductManager.getProduct())
                startActivityForResult(i, 1235)
                true
            }
            R.id.action_delete -> {
                val dialog = DeleteDialog()
                dialog.setParent(this)
                dialog.show(supportFragmentManager, "delete")
                true
            }
            else -> {
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1235) {
            if (resultCode == Activity.RESULT_OK) {
                val product = ProductManager.intentToProduct(data)
                if (product != null)
                    ProductManager.products[ProductManager.getProductSelected()] = product
            }
        }
        finish()
    }

    override fun deleteDialogAccepted(dialog: DialogInterface) {
        ProductManager.removeSelectedProduct()
        val toast = Toast.makeText(applicationContext, R.string.deleted, Toast.LENGTH_SHORT)
        toast.show()
        finish()
    }

    override fun deleteDialogCancelled(dialog: DialogInterface) {
        val toast = Toast.makeText(applicationContext, R.string.cancel, Toast.LENGTH_SHORT)
        toast.show()
    }
}