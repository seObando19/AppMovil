package com.example.proyectoapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorProductos extends RecyclerView.Adapter<AdaptadorProductos.ViewHolderProductos>{

    ArrayList<Imagenes>listaProductos;

    public AdaptadorProductos(ArrayList<Imagenes> listaProductos) {
        this.listaProductos = listaProductos;
    }

    @NonNull
    @Override
    public ViewHolderProductos onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_productos,null,false);
        return new ViewHolderProductos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderProductos viewHolderProductos, int i) {
        viewHolderProductos.nom.setText(listaProductos.get(i).getNombre());
        viewHolderProductos.tip.setText(listaProductos.get(i).getTipo());
        viewHolderProductos.val.setText(listaProductos.get(i).getValor());
        viewHolderProductos.imag.setImageResource(listaProductos.get(i).getFoto());
    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public class ViewHolderProductos extends RecyclerView.ViewHolder {

        TextView nom,tip,val;
        ImageView imag;

        public ViewHolderProductos(@NonNull View itemView) {
            super(itemView);
            nom=(TextView)itemView.findViewById(R.id.idNombre);
            tip=(TextView)itemView.findViewById(R.id.idTipo);
            val=(TextView) itemView.findViewById(R.id.idValor);
            imag=(ImageView) itemView.findViewById(R.id.idImagen);
        }
    }
}
