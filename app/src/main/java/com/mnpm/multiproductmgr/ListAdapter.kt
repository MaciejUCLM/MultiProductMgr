package com.mnpm.multiproductmgr

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import java.util.*


class ListAdapter : RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private var contactos: ArrayList<Product>? = null
    private var clickListener: OnItemSelectedListenerI<Product>? = null

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val lblNombre: TextView
        val lblTelefono: TextView
        val imagContacto: ImageView
        var cardView: CardView? = null

        init {
            lblNombre = view.findViewById(R.id.lblNombre)
            lblTelefono = view.findViewById(R.id.lblTelefono)
            imagContacto = view.findViewById(R.id.imagContacto)
            cardView = view.findViewById(R.id.carview)
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

    fun setOnItemClickListener(productClickListener: OnItemSelectedListenerI<Product>) {
        this.clickListener = productClickListener
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contacto = contactos!![position]
        holder.lblNombre.text = contactos!![position].getNombre()
        //holder.lblTelefono.text = contactos!![position].getTelefono()
        holder.lblTelefono.text = contactos!![position].getYear().toString()

        when (contactos!![position].getTipo()) {
            ProductTypes.HATCHBACK -> {
                holder.imagContacto.setBackgroundResource(ProductTypes.HATCHBACK.getIcon())
            }
            ProductTypes.WAGON -> {
                holder.imagContacto.setBackgroundResource(ProductTypes.WAGON.getIcon())
            }
            ProductTypes.COUPE -> {
                holder.imagContacto.setBackgroundResource(ProductTypes.COUPE.getIcon())
            }
            ProductTypes.CONVERTIBLE -> {
                holder.imagContacto.setBackgroundResource(ProductTypes.CONVERTIBLE.getIcon())
            }
        }

        holder.cardView?.setOnClickListener(View.OnClickListener {
            clickListener?.onContactoSeleccionado(contacto)
        })

    }

    override fun getItemCount(): Int {
        return contactos!!.size
    }
}