package com.mnpm.multiproductmgr;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class AdaptadorLista extends RecyclerView.Adapter<AdaptadorLista.ViewHolder> {
    private ArrayList<Contacto> contactos;
    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView lblNombre;
        private TextView lblTelefono;
        private ImageView imagContacto;
        ViewHolder(View view) {
            super(view);
            lblNombre = view.findViewById(R.id.lblNombre);
            lblTelefono = view.findViewById(R.id.lblTelefono);
            imagContacto = view.findViewById(R.id.imagContacto);
        }
    }
    public AdaptadorLista(ArrayList<Contacto> contactos){
        this.contactos = contactos;
    }

    @Override
    public AdaptadorLista.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false);
        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(AdaptadorLista.ViewHolder holder, int position) {
        Contacto contacto = contactos.get(position);
        holder.lblNombre.setText(contactos.get(position).getNombre());
        holder.lblTelefono.setText(contactos.get(position).getTelefono());
        switch (contactos.get(position).getTipo())
        {
            case 0: //Cargar imagen de contactos tipo "familia"
                //holder.imagContacto.setImageResource(R.drawable.familia);
                break;
            case 1: //Cargar imagen de los contactos tipo "amigos"
                //holder.imagContacto.setImageResource(R.drawable.amigo);
                break;
            case 2: //Cargar imagen de los contactos tipo "trabajo"
                //holder.imagContacto.setImageResource(R.drawable.trabajo);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return contactos.size();
    }
}
