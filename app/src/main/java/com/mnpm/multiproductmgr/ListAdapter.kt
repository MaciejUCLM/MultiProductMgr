package com.mnpm.multiproductmgr

import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class ListAdapter : RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private var itemSelectedListener: OnItemSelectedListenerI? = null
    private var longItemSelectedListener: OnItemSelectedListenerI? = null
    private var elementsList: ArrayList<Product>? = null

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnCreateContextMenuListener {
        val lblName: TextView = view.findViewById(R.id.lblNombre)
        val lblYear: TextView = view.findViewById(R.id.lblTelefono)
        val imgType: ImageView = view.findViewById(R.id.imagContacto)

        init {
            view.setOnCreateContextMenuListener(this)
        }

        override fun onCreateContextMenu(menu: ContextMenu, view: View, menuInfo: ContextMenu.ContextMenuInfo?) {
            menu.add(Menu.NONE, R.id.action_view, Menu.NONE, R.string.action_view)
            menu.add(Menu.NONE, R.id.action_edit, Menu.NONE, R.string.action_edit)
            menu.add(Menu.NONE, R.id.action_delete, Menu.NONE, R.string.action_delete)
        }
    }

    constructor(list: ArrayList<Product>?) {
        setList(list)
    }

    fun setList(list: ArrayList<Product>?) {
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
        holder.itemView.setOnLongClickListener {
            longItemSelectedListener!!.onItemSelected(it, holder.adapterPosition)
            false
        }
        holder.itemView.setOnClickListener {
            itemSelectedListener!!.onItemSelected(it, holder.adapterPosition)
        }
    }

    override fun onViewRecycled(holder: ViewHolder) {
        holder.itemView.setOnLongClickListener(null)
        super.onViewRecycled(holder)
    }

    override fun getItemCount(): Int {
        return elementsList!!.size
    }

    fun setLongListener(listener: OnItemSelectedListenerI) {
        longItemSelectedListener = listener
    }

    fun setListener(listener: OnItemSelectedListenerI) {
        itemSelectedListener = listener
    }

    fun sortElements(mode: Int) {
        when (mode) {
            SortDialogListenerI.SORT_NAME -> elementsList!!.sortBy { p -> p.name }
            SortDialogListenerI.SORT_YEAR -> elementsList!!.sortBy { p -> p.productionYear }
            SortDialogListenerI.SORT_TYPE -> elementsList!!.sortBy { p -> p.type }
        }
        notifyDataSetChanged()
    }

    fun filterList(text: String?) {
        // TODO search filter
        notifyDataSetChanged()
    }
}