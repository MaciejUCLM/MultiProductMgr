package com.mnpm.multiproductmgr

import android.content.Intent
import android.os.Bundle
import android.view.Menu
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

class MainActivity : AppCompatActivity(), PopupMenu.OnMenuItemClickListener {

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

        products = ArrayList()
        loadExampleData()

        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(applicationContext)
        lstProducts = findViewById(R.id.lstProducts)
        lstProducts!!.layoutManager = mLayoutManager
        lsAdapter = ListAdapter(products)
        lstProducts!!.adapter = lsAdapter
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

    override fun onMenuItemClick(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_view -> {
                // TODO start intent
                true
            }
            R.id.action_edit -> {
                true
            }
            R.id.action_delete -> {
                true
            }
            else -> false
        }
    }

    fun showPopup(v: View) {
        PopupMenu(this, v).apply {
            setOnMenuItemClickListener(this@MainActivity)
            inflate(R.menu.popup)
            show()
        }
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

    private fun loadExampleData() {
        products!!.add(Product("Ford Focus Mk2", ProductTypes.HATCHBACK, 110, 180, 1900, 2003))
        products!!.add(Product("Mazda MX-5", ProductTypes.CONVERTIBLE, 160, 240, 950, 2018))
        products!!.add(Product("BMW M2", ProductTypes.COUPE, 220, 260, 1500, 2013))
        products!!.add(Product("Volvo V70", ProductTypes.WAGON, 130, 180, 1800, 2005))
    }
}