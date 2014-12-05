package com.example.jorge.inmobiliaria;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class AdaptadorInmueble extends ArrayAdapter<Inmueble>{

    private static LayoutInflater i;
    private int recurso;
    private ArrayList<Inmueble> lista;
    private Context contexto;

    public AdaptadorInmueble(Context context, int resource, ArrayList<Inmueble> objects) {
        super(context, resource, objects);
        contexto = context;
        recurso = resource;
        lista = objects;
        this.i = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder vh = new viewHolder();
        convertView = i.inflate(recurso,null);
        vh.tvDirec = (TextView) convertView.findViewById(R.id.tvDireccion);
        vh.tvNum = (TextView) convertView.findViewById(R.id.tvNumero);
        vh.tvTipo = (TextView) convertView.findViewById(R.id.tvTipo);
        vh.ivImg = (ImageView) convertView.findViewById(R.id.ivDetalle);
        vh.tvDirec.setText(lista.get(position).getDireccion());
        vh.tvNum.setText(lista.get(position).getNumcalle());
        vh.tvTipo.setText(lista.get(position).getTipo());
        vh.ivImg.setImageDrawable(contexto.getResources().getDrawable(R.drawable.casa));
        return convertView;
    }

    static class viewHolder{
        public TextView tvDirec, tvNum, tvTipo;
        public ImageView ivImg;
    }
}
