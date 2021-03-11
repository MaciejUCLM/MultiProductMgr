package com.ipo.example;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private int contactoSeleccionado;

    private ArrayList<Contacto> contactos;
    private RecyclerView lstContactos;

    private AdaptadorLista adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //Obtener una referencia a la lista gráfica
        lstContactos = findViewById(R.id.lstContactos);
        //Crear la lista de contactos y añadir algunos datos de prueba
        contactos = new ArrayList<Contacto>();
        //Método que rellena el array con datos de prueba
        rellenarDatosPrueba();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        lstContactos.setLayoutManager(mLayoutManager);
        adaptador = new AdaptadorLista(contactos);
        lstContactos.setAdapter(adaptador);

        Intent i = new Intent(this, ActivityDetallesContacto.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent
            data){
        super.onActivityResult(requestCode, resultCode, data);
        Bundle b = data.getExtras();
        if (requestCode == 1234){
            if (resultCode == RESULT_OK){
                Contacto contactoModificado = new
                        Contacto(b.getString("nombre"),b.getString("telefono"), b.getInt("tipo"),
                        b.getString("email"), b.getString("direccion"));
                contactos.set(contactoSeleccionado, contactoModificado);
                adaptador.notifyDataSetChanged();
            }
        }
    }

    public void rellenarDatosPrueba()
    {
        contactos.add(new Contacto("María Rodríguez", "234 123 411",1, "mariarodriguez@correo.com", "C/Ronda, 10"));
        contactos.add(new Contacto("José Pérez", "234 234 234",1, "joseperez@correo.com", "C/Huertas, 1"));
        contactos.add(new Contacto("José Ruíz", "545 342 455",2, "joseruiz@correo.com", "C/Ancha, 7"));
        contactos.add(new Contacto("Carmen López", "666 433 566",0, "carmenlopez@correo.com", "C/Luz, 12"));
        contactos.add(new Contacto("María Pérez", "444 564 331",1, "mariapezar@correo.com", "C/Ciudad, 11"));
        contactos.add(new Contacto("José García", "233 223 411",1, "josegarcia@correo.com", "C/Darro, 4"));
        contactos.add(new Contacto("José Ruíz", "211 234 234",2, "joseruiz@correo.com", "C/Ronda, 6"));
        contactos.add(new Contacto("Carmen Ruíz", "544 442 425",2, "carmenrodriguez@correo.com", "C/Principal, 13"));
        contactos.add(new Contacto("Carmen Rodríguez", "623 453 335",0, "carmenrodriguez@correo.com", "C/Rodero, 5"));
        contactos.add(new Contacto("María García", "432 456 331",1, "mariagarcia@correo.com", "C/Paseo, 8"));
    }
}