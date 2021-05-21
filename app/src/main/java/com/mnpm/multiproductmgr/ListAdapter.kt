package com.mnpm.multiproductmgr

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class ListAdapter : RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private var itemSelectedListener: OnItemSelectedListenerI? = null
    private var elementsList: ArrayList<Product>? = null

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val lblName: TextView = view.findViewById(R.id.lblNombre)
        val lblYear: TextView = view.findViewById(R.id.lblTelefono)
        val imgType: ImageView = view.findViewById(R.id.imagContacto)
    }

    constructor(list: ArrayList<Product>?) {
        this.elementsList = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val p: Product = elementsList!![position]
        holder.lblName.text = p.name.toString()
        holder.lblYear.text = p.productionYear.toString()
        holder.imgType.setImageResource(p.type!!.getIcon())
        holder.itemView.setOnClickListener {
            itemSelectedListener!!.onItemSelected(it, holder.adapterPosition)
        }
    }

    override fun getItemCount(): Int {
        return elementsList!!.size
    }

    fun setListener(listener: OnItemSelectedListenerI) {
        itemSelectedListener = listener
    }

    fun sortElements() {
        elementsList!!.sortBy { p -> p.name }
    }
}