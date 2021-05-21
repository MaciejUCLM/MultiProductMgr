package com.mnpm.multiproductmgr

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), SortDialogListenerI, DeleteDialogListenerI {

    private var lsProducts: RecyclerView? = null
    private var lsAdapter: ListAdapter? = null

    fun openDetails() {
        val intent = ProductManager.productToIntent(
                Intent(this, DetailsActivity::class.java), ProductManager.getProduct())
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        lsAdapter?.notifyDataSetChanged()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            val i = Intent(this, EditorActivity::class.java).apply {
                putExtra("new", true)
            }
            startActivity(i)
        }

        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(applicationContext)
        lsAdapter = ListAdapter(ProductManager.products)
        lsProducts = findViewById(R.id.lstProducts)
        lsProducts!!.layoutManager = mLayoutManager
        lsProducts!!.adapter = lsAdapter
        lsProducts!!.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

        lsAdapter!!.setListener(object : OnItemSelectedListenerI {
            override fun onItemSelected(view: View, position: Int) {
                ProductManager.setProductSelected(position)
                openDetails()
            }
        })
        lsAdapter!!.setLongListener(object : OnItemSelectedListenerI {
            override fun onItemSelected(view: View, position: Int) {
                ProductManager.setProductSelected(position)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                lsAdapter?.filterList(newText)
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_sort -> {
                val dialog = SortDialog()
                dialog.setParent(this)
                dialog.show(supportFragmentManager, "sort")
                true
            }
            else -> {
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_view -> {
                openDetails()
                true
            }
            R.id.action_edit -> {
                val i = ProductManager.productToIntent(
                        Intent(this, EditorActivity::class.java), ProductManager.getProduct())
                startActivityForResult(i, 1234)
                true
            }
            R.id.action_delete -> {
                val dialog = DeleteDialog()
                dialog.setParent(this)
                dialog.show(supportFragmentManager, "delete")
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    override fun deleteDialogAccepted(dialog: DialogInterface) {
        ProductManager.removeSelectedProduct()
        lsAdapter?.notifyDataSetChanged()
        val toast = Toast.makeText(applicationContext, R.string.deleted, Toast.LENGTH_SHORT)
        toast.show()
    }

    override fun deleteDialogCancelled(dialog: DialogInterface) {
        val toast = Toast.makeText(applicationContext, R.string.cancel, Toast.LENGTH_SHORT)
        toast.show()
    }

    override fun sortDialogSelected(dialog: DialogInterface, mode: Int) {
        lsAdapter?.sortElements(mode)
        var text = when (mode) {
            SortDialogListenerI.SORT_NAME -> R.string.sorted_name
            SortDialogListenerI.SORT_YEAR -> R.string.sorted_year
            SortDialogListenerI.SORT_TYPE -> R.string.sorted_type
            else -> R.string.cancel
        }
        val toast = Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT)
        toast.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1234) {
            if (resultCode == Activity.RESULT_OK) {
                val product = ProductManager.intentToProduct(data)
                if (product != null)
                    ProductManager.products[ProductManager.getProductSelected()] = product
                lsAdapter?.notifyDataSetChanged()
            }
        }
    }
}