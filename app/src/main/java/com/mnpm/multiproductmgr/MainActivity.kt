package com.mnpm.multiproductmgr

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import java.util.*

class MainActivity : AppCompatActivity() {

    private val contactoSeleccionado = 0

    private var contactos: ArrayList<Contacto>? = null
    private var lstContactos: RecyclerView? = null

    private var adaptador: AdaptadorLista? = null

    /*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        //Obtener una referencia a la lista gráfica
        lstContactos = findViewById(R.id.lstContactos)
        //Crear la lista de contactos y añadir algunos datos de prueba
        contactos = ArrayList()
        //Método que rellena el array con datos de prueba
        rellenarDatosPrueba()
        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(applicationContext)
        lstContactos.setLayoutManager(mLayoutManager)
        adaptador = AdaptadorLista(contactos)
        lstContactos.setAdapter(adaptador)
        val i = Intent(this, ActivityDetallesContacto::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId
        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)
    }

    protected fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        val b = data.extras
        if (requestCode == 1234) {
            if (resultCode == Activity.RESULT_OK) {
                val contactoModificado = Contacto(b!!.getString("nombre"), b.getString("telefono"), b.getInt("tipo"),
                        b.getString("email"), b.getString("direccion"))
                contactos!![contactoSeleccionado] = contactoModificado
                adaptador!!.notifyDataSetChanged()
            }
        }
    }

    fun rellenarDatosPrueba() {
        contactos!!.add(Contacto("María Rodríguez", "234 123 411", 1, "mariarodriguez@correo.com", "C/Ronda, 10"))
        contactos!!.add(Contacto("José Pérez", "234 234 234", 1, "joseperez@correo.com", "C/Huertas, 1"))
        contactos!!.add(Contacto("José Ruíz", "545 342 455", 2, "joseruiz@correo.com", "C/Ancha, 7"))
        contactos!!.add(Contacto("Carmen López", "666 433 566", 0, "carmenlopez@correo.com", "C/Luz, 12"))
        contactos!!.add(Contacto("María Pérez", "444 564 331", 1, "mariapezar@correo.com", "C/Ciudad, 11"))
        contactos!!.add(Contacto("José García", "233 223 411", 1, "josegarcia@correo.com", "C/Darro, 4"))
        contactos!!.add(Contacto("José Ruíz", "211 234 234", 2, "joseruiz@correo.com", "C/Ronda, 6"))
        contactos!!.add(Contacto("Carmen Ruíz", "544 442 425", 2, "carmenrodriguez@correo.com", "C/Principal, 13"))
        contactos!!.add(Contacto("Carmen Rodríguez", "623 453 335", 0, "carmenrodriguez@correo.com", "C/Rodero, 5"))
        contactos!!.add(Contacto("María García", "432 456 331", 1, "mariagarcia@correo.com", "C/Paseo, 8"))
    }
    */

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}