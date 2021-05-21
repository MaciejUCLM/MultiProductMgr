package com.mnpm.multiproductmgr

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.view.*

class EditorActivity : AppCompatActivity() {
    private var btnGuardarC: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editor)

        val layout = findViewById<LinearLayout>(R.id.insideMainLayout)
        val unwrappedDrawable = resources.getDrawable(R.drawable.custom_item)
        val wrappedDrawable: Drawable = DrawableCompat.wrap(unwrappedDrawable)

        layout.forEach { row ->
            if (row is LinearLayout && row.size > 2){
                val field = row[1] as View
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

        // Obtenemos las referencias a los elementos gráficos de la GUI
        val carFieldName = arrayOf<String>(
                "carNameField",
                "carPowerField",
                "carMaxVelocityField",
                "carTypeField",
                "carProductionYearField",
                "carMassField"
        )

        val nameField = findViewById<EditText>(R.id.carNameField)
        val powerField = findViewById<EditText>(R.id.carPowerField)
        val maxVelocityField = findViewById<EditText>(R.id.carMaxVelocityField)
        val typeField = findViewById<Spinner>(R.id.carTypeField)
        val productionYearField = findViewById<EditText>(R.id.carProductionYearField)
        val massField = findViewById<EditText>(R.id.carMassField)

        /*
        nameField
        powerField
        maxVelocityField
        massField
        productionYearField
         */

        //Recoger los datos enviados por la primera actividad y mostrarlos en la GUI
        val bundle = intent.extras
        if(bundle != null) {
            nameField.setText(bundle.getString(carFieldName[0]))
            powerField.setText(bundle.getString(carFieldName[1]))
            maxVelocityField.setText(bundle.getString(carFieldName[2]))
            typeField.setSelection(bundle.getInt(carFieldName[3]))
            productionYearField.setText(bundle.getString(carFieldName[4]))
            massField.setText(bundle.getString(carFieldName[5]))
        }


        btnGuardarC = findViewById<Button>(R.id.btnGuardarC)
        btnGuardarC!!.setOnClickListener(View.OnClickListener {
            Intent(this, MainActivity::class.java).apply {
                putExtra("name", nameField.text.toString())
                putExtra("power", powerField.text.toString().toInt())
                putExtra("maxVelocity", maxVelocityField.text.toString().toInt())
                putExtra("type", typeField.selectedItemPosition)
                putExtra("mass", massField.text.toString().toInt())
                putExtra("productionYear", productionYearField.text.toString().toInt())
            }

            val text = R.string.edit_saved
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(applicationContext, text, duration)
            toast.show()
            //val newCar = Product(*carData)
            //setResult(Activity.RESULT_OK, newCar)
            finish()
        })

    }
}
