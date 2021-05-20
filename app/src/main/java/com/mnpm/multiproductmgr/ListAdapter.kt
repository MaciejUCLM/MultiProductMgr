package com.mnpm.multiproductmgr

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class ListAdapter : RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private var contactos: ArrayList<Product>? = null

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val lblNombre: TextView
        val lblTelefono: TextView
        private val imagContacto: ImageView

        init {
            lblNombre = view.findViewById(R.id.lblNombre)
            lblTelefono = view.findViewById(R.id.lblTelefono)
            imagContacto = view.findViewById(R.id.imagContacto)
        }
    }

    constructor(contactos: ArrayList<Product>?) {
        this.contactos = contactos
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contacto = contactos!![position]
        /*
        holder.lblNombre.text = contactos!![position].getNombre()
        holder.lblTelefono.text = contactos!![position].getTelefono()
        when (contactos!![position].getTipo()) {
            0 -> {
            }
            1 -> {
            }
            2 -> {
            }
        }
         */
    }

    override fun getItemCount(): Int {
        return contactos!!.size
    }
}