package com.example.jorge.inmobiliaria;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Anadir extends Activity{
    private ArrayList<Inmueble> alInmueble = new ArrayList<Inmueble>();
    private EditText etDirec, etNum, etPrecio, etDescri;
    private TextView tvNomFoto;
    private Spinner spTipo;
    private String nomFoto="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialogo_anadir);

        Bundle inm = getIntent().getExtras();
        alInmueble = inm.getParcelableArrayList("inmuebles");

        etDirec = (EditText) findViewById(R.id.etDireccion);
        etNum = (EditText) findViewById(R.id.etNumero);
        etPrecio = (EditText) findViewById(R.id.etPrecio);
        etDescri = (EditText) findViewById(R.id.etDesc);
        tvNomFoto = (TextView) findViewById(R.id.tvNomfoto);
        spTipo = (Spinner) findViewById(R.id.sTipo);
        Spinner spinner = (Spinner) this.findViewById(R.id.sTipo);
        ArrayAdapter<CharSequence> adTipos = ArrayAdapter.createFromResource(this, R.array.tipos,
                android.R.layout.simple_spinner_item);
        adTipos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adTipos);
    }

    public void anadir(View v) {
        final Inmueble inmueble = new Inmueble();
        try {
            String id = String.valueOf(alInmueble.size()+1);
            String direc = etDirec.getText().toString();
            String numero = etNum.getText().toString();
            String precio = etPrecio.getText().toString();
            String descr = etDescri.getText().toString();
            String tipo = String.valueOf(spTipo.getSelectedItem());
            inmueble.setIdInmueble(id);
            inmueble.setDireccion(direc);
            inmueble.setNumcalle(numero);
            inmueble.setPrecio(precio);
            inmueble.setTipo(tipo);
            inmueble.setDesc(descr);
            inmueble.setImg(nomFoto);
            alInmueble.add(inmueble);
            tostada("Inmueble añadido");
            ArchivoXML xml = new ArchivoXML();
            xml.escribirXML(getApplicationContext(),alInmueble);
            Intent i = new Intent(this,Principal.class);
            startActivity(i);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static final int IDACTIVIDADFOTO=1;
    public void foto(View v){
        Intent i = new Intent (MediaStore.ACTION_IMAGE_CAPTURE);
        String nombreImg = "inmueble_"+getDatePhone()+".jpg";
        File f = new File(getExternalFilesDir(null), nombreImg);
        i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
        startActivityForResult(i, IDACTIVIDADFOTO);
        tvNomFoto.setText(nombreImg);
        nomFoto = nombreImg;
    }

    private String getDatePhone(){
        Calendar cal = new GregorianCalendar();
        Date date = cal.getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss");
        String formatteDate = df.format(date);
        return formatteDate;
    }

    private void tostada(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
