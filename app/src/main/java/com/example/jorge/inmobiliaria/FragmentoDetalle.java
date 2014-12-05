package com.example.jorge.inmobiliaria;



import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class FragmentoDetalle extends Fragment {
    private ArrayList<Inmueble> alInmueble = new ArrayList<Inmueble>();
    private View v;
    private TextView tvDirec, tvNum, tvPrecio, tvTipo, tvDesc;
    private ImageView ivFoto;

    public FragmentoDetalle() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_fragmento_detalle, container, false);
        return v;
    }

    public void setInmueble(Context context,ArrayList al, int position){
        alInmueble = al;
        tvDirec = (TextView) v.findViewById(R.id.tvDireccion);
        tvNum = (TextView) v.findViewById(R.id.tvNumero);
        tvPrecio = (TextView) v.findViewById(R.id.tvPrecio);
        tvTipo = (TextView) v.findViewById(R.id.tvTipo);
        tvDesc = (TextView) v.findViewById(R.id.tvDescripcion);
        ivFoto = (ImageView) v.findViewById(R.id.ivFoto);
        tvDirec.setText(alInmueble.get(position).getDireccion());
        tvNum.setText(alInmueble.get(position).getNumcalle());
        tvPrecio.setText(alInmueble.get(position).getPrecio());
        tvTipo.setText(alInmueble.get(position).getTipo());
        tvDesc.setText(alInmueble.get(position).getDesc());
        File f = new File(context.getExternalFilesDir(null),alInmueble.get(position).getImg());
        Bitmap foto = BitmapFactory.decodeFile(f.getAbsolutePath());
        ivFoto.setImageBitmap(foto);
    }
}
