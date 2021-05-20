package com.mnpm.multiproductmgr

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.view.*

class EditorActivity : AppCompatActivity() {
    private var txtNombreC: EditText? = null
    private var txtTelefonoC: EditText? = null
    private var spinnerTipo: Spinner? = null
    private var txtEmailC: EditText? = null
    private var txtDireccionC: EditText? = null

    private var btnGuardarC: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editor)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        var layout = findViewById<LinearLayout>(R.id.mainEditorLayout)
        val unwrappedDrawable = resources.getDrawable(R.drawable.custom_item)
        val wrappedDrawable: Drawable = DrawableCompat.wrap(unwrappedDrawable)

        layout.forEach { row ->
            if (row is LinearLayout && row.size > 2){
                val field = row[1] as EditText
                val label = row[2] as TextView
                field.setOnFocusChangeListener { view, b ->
                    if (b) {
                        DrawableCompat.setTint(wrappedDrawable, R.attr.colorControlActivated)
                        view.setBackgroundResource(R.drawable.frame_desactivated)
                        label.setText("activado")
                        label.setTextColor(R.attr.colorControlActivated)
                    } else {
                        DrawableCompat.setTint(wrappedDrawable, R.attr.colorControlNormal)
                        view.setBackgroundResource(R.drawable.custom_item)
                        label.setText("desactivado")
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
                "carCarbonDioxideEmisionField",
                "carMassField",
                "carProductionYearField"
        )
        val carFields = arrayOf<EditText>(
                findViewById<EditText>(R.id.carNameField),
                findViewById<EditText>(R.id.carPowerField),
                findViewById<EditText>(R.id.carMaxVelocityField),
                findViewById<EditText>(R.id.carCarbonDioxideEmisionField),
                findViewById<EditText>(R.id.carMassField),
                findViewById<EditText>(R.id.carProductionYearField)
        )

        //Recoger los datos enviados por la primera actividad y mostrarlos en la GUI
        val bundle = intent.extras
        for ((i, name) in carFieldName.withIndex())
            carFields[i].setText(bundle!!.getString(name))

        btnGuardarC = findViewById(R.id.btnGuardarC) as Button
        btnGuardarC!!.setOnClickListener(View.OnClickListener {
            val carData = Array<String>(carFields.size) { i -> ""}
            for((i, field) in carFields.withIndex())
                carData[i] = field.text.toString()

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
