package com.mnpm.multiproductmgr

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

class MainActivity : AppCompatActivity() {

    private val productSelected = 0

    private var products: ArrayList<Product>? = null
    private var lstProducts: RecyclerView? = null

    private var lsAdapter: ListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            val i = Intent(this, EditorActivity::class.java)
            startActivity(i)
        }

        lstProducts = findViewById(R.id.lstProducts)
        products = ArrayList()
        // TODO load example data here
        products!!.add(Product("Ford Focus Mk1", ProductTypes.HATCHBACK, 110, 180, 1900, 2003))
        // TODO improve
        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(applicationContext)
        lstProducts!!.layoutManager = mLayoutManager
        lsAdapter = ListAdapter(products)
        lstProducts!!.adapter = lsAdapter

        val i = Intent(this, EditorActivity::class.java).apply {
            putExtra("product", productSelected)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        // TODO search event
        /*
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView
        searchView.setOnQueryTextListener({ i -> null })
        */
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_sort -> {
                // TODO execute sorting action
                val text = R.string.action_sort
                val duration = Toast.LENGTH_SHORT
                val toast = Toast.makeText(applicationContext, text, duration)
                toast.show()
                true
            }
            else -> {
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                super.onOptionsItemSelected(item)
            }
        }
    }

    fun showPopup(v: View) {
        val popup = PopupMenu(this, v)
        val inflater: MenuInflater = popup.menuInflater
        inflater.inflate(R.menu.popup, popup.menu)
        popup.show()
    }

    // TODO save new element
    /*
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
    */
}