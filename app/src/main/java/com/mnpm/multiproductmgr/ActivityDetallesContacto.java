package com.mnpm.multiproductmgr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class ActivityDetallesContacto extends AppCompatActivity {

    private EditText txtNombreC;
    private EditText txtTelefonoC;
    private Spinner spinnerTipo;
    private EditText txtEmailC;
    private EditText txtDireccionC;

    private Button btnGuardarC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        //Obtenemos las referencias a los elementos gráficos de la GUI
        txtNombreC=findViewById(R.id.txtNombreC);
        txtTelefonoC=findViewById(R.id.txtTelefonoC);
        spinnerTipo=findViewById(R.id.spinnerTipo);

        //Llenar de contenido el Spinner
        String []opciones={"Familia", "Amigo", "Trabajo"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, opciones);
        spinnerTipo.setAdapter(adapter);
        txtEmailC=findViewById(R.id.txtEmailC);
        txtDireccionC=findViewById(R.id.txtDireccionC);

        //Recoger los datos enviados por la primera actividad y mostrarlos en la GUI
        Bundle bundle=getIntent().getExtras();
        txtNombreC.setText(bundle.getString("nombre"));
        txtTelefonoC.setText(bundle.getString("telefono"));
        spinnerTipo.setSelection(bundle.getInt("tipo"));
        txtEmailC.setText(bundle.getString("email"));
        txtDireccionC.setText(bundle.getString("direccion"));

        btnGuardarC = findViewById(R.id.btnGuardarC);
        btnGuardarC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nuevoContacto = new Intent();
                nuevoContacto.putExtra("nombre",txtNombreC.getText().toString());
                nuevoContacto.putExtra("telefono",txtTelefonoC.getText().toString());
                nuevoContacto.putExtra("tipo",spinnerTipo.getSelectedItemPosition());
                nuevoContacto.putExtra("email",txtEmailC.getText().toString());
                nuevoContacto.putExtra("direccion",txtDireccionC.getText().toString());
                setResult(RESULT_OK, nuevoContacto);
                finish();
            }
        });
    }
}