package com.mnpm.multiproductmgr

import android.os.Bundle
import android.view.ActionMode
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

import android.graphics.drawable.Drawable



class EditorActivity : AppCompatActivity() {
    private var txtNombreC: EditText? = null
    private var txtTelefonoC: EditText? = null
    private var spinnerTipo: Spinner? = null
    private var txtEmailC: EditText? = null
    private var txtDireccionC: EditText? = null

    private var btnGuardarC: Button? = null

    fun getShape() {
        val StrokeWidth = 2
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editor)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        var layout = findViewById(R.id.mainEditorLayout) as LinearLayout
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
        val carFieldName = arrayOf<EditText>(
                "carNameField",
                "carPowerField",
                "carMaxVelocityField",
                "carCarbonDioxideEmisionField",
                "carMassField",
                "carProductionYearField"
        )
        val carFields = arrayOf<EditText>(
                findViewById(R.id.carNameField) as EditText,
                findViewById(R.id.carPowerField) as EditText,
                findViewById(R.id.carMaxVelocityField) as EditText,
                findViewById(R.id.carCarbonDioxideEmisionField) as EditText,
                findViewById(R.id.carMassField) as EditText,
                findViewById(R.id.carProductionYearField) as EditText
        )

        //Recoger los datos enviados por la primera actividad y mostrarlos en la GUI
        val bundle = intent.extras
        for ((i, name) in carFieldName.withIndex())
            carFields[i].setText(bundle.getString(name))

        btnGuardarC = findViewById(R.id.btnGuardarC) as Button
        btnGuardarC.setOnClickListener(View.OnClickListener {
            val carData = Array<String>(carFields.size, {i-> null})
            for((i, field) in carFields.withIndex())
                carData[i] = field.text.toString()

            val newCar = Product(*carData)
            setResult(Activity.RESULT_OK, newCar)
            finish()
        })

    }

    override fun onActionModeFinished(mode: ActionMode?) {
        //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show()
        val text = "Hello toast!"
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
        super.onActionModeFinished(mode)
    }
}
