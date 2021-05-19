package com.mnpm.multiproductmgr

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

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

        // Obtenemos las referencias a los elementos gráficos de la GUI
        txtNombreC = findViewById(R.id.txtNombreC)
        txtTelefonoC = findViewById(R.id.txtTelefonoC)
        spinnerTipo = findViewById(R.id.spinnerTipo)

        // Llenar de contenido el Spinner
        val opciones = arrayOf("Familia", "Amigo", "Trabajo")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, opciones)
        //spinnerTipo.setAdapter(adapter)
        txtEmailC = findViewById(R.id.txtEmailC)
        txtDireccionC = findViewById(R.id.txtDireccionC)

        //Recoger los datos enviados por la primera actividad y mostrarlos en la GUI
        val bundle = intent.extras
        //txtNombreC.setText(bundle!!.getString("nombre"))
        //txtTelefonoC.setText(bundle.getString("telefono"))
        //spinnerTipo.setSelection(bundle.getInt("tipo"))
        //txtEmailC.setText(bundle.getString("email"))
        //txtDireccionC.setText(bundle.getString("direccion"))
        btnGuardarC = findViewById(R.id.btnGuardarC)
        /*btnGuardarC.setOnClickListener(View.OnClickListener {
            val nuevoContacto = Intent()
            nuevoContacto.putExtra("nombre", txtNombreC.getText().toString())
            nuevoContacto.putExtra("telefono", txtTelefonoC.getText().toString())
            nuevoContacto.putExtra("tipo", spinnerTipo.getSelectedItemPosition())
            nuevoContacto.putExtra("email", txtEmailC.getText().toString())
            nuevoContacto.putExtra("direccion", txtDireccionC.getText().toString())
            setResult(Activity.RESULT_OK, nuevoContacto)
            finish()
        })*/
    }
}