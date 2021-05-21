package com.mnpm.multiproductmgr

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.view.*

class EditorActivity : AppCompatActivity() {

    private var btnSave: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editor)

        val layout = findViewById<LinearLayout>(R.id.insideMainLayout)
        val unwrappedDrawable = resources.getDrawable(R.drawable.custom_item)
        val wrappedDrawable: Drawable = DrawableCompat.wrap(unwrappedDrawable)

        layout.forEach { row ->
            if (row is LinearLayout && row.size > 2){
                val field = row[1]
                val label = row[2] as TextView
                field.setOnFocusChangeListener { view, b ->
                    if (b) {
                        DrawableCompat.setTint(wrappedDrawable, R.attr.colorControlActivated)
                        view.setBackgroundResource(R.drawable.frame_desactivated)
                        label.setText("activated")
                        label.setTextColor(R.attr.colorControlActivated)
                    } else {
                        DrawableCompat.setTint(wrappedDrawable, R.attr.colorControlNormal)
                        view.setBackgroundResource(R.drawable.custom_item)
                        label.setText("")
                        label.setTextColor(R.attr.colorControlNormal)
                    }
                }
            }
        }

        val nameField = findViewById<EditText>(R.id.carNameField)
        val powerField = findViewById<EditText>(R.id.carPowerField)
        val maxVelocityField = findViewById<EditText>(R.id.carMaxVelocityField)
        val typeField = findViewById<Spinner>(R.id.carTypeField)
        val productionYearField = findViewById<EditText>(R.id.carProductionYearField)
        val massField = findViewById<EditText>(R.id.carMassField)

        // load provided product
        val product = ProductManager.intentToProduct(intent)
        product?.let {
            nameField.setText(it.name)
            powerField.setText(it.power.toString())
            maxVelocityField.setText(it.maxVelocity.toString())
            typeField.setSelection(it.type!!.ordinal)
            productionYearField.setText(it.productionYear.toString())
            massField.setText(it.mass.toString())
        }

        btnSave = findViewById<Button>(R.id.btnSave)
        btnSave?.setOnClickListener(View.OnClickListener {
            if (validateFields()) {
                val p = Product(nameField.text.toString(),
                        ProductTypes.values()[typeField.selectedItemPosition],
                        powerField.text.toString().toInt(),
                        maxVelocityField.text.toString().toInt(),
                        massField.text.toString().toInt(),
                        productionYearField.text.toString().toInt())
                val data = ProductManager.productToIntent(Intent(), p)

                val text = R.string.edit_saved
                val toast = Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT)
                toast.show()

                setResult(Activity.RESULT_OK, data)
                finish()
            } else {
                val text = R.string.data_incorrect
                val toast = Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT)
                toast.show()
            }
        })
    }

    fun validateFields(): Boolean {
        // TODO fix me
        return false
    }
}
