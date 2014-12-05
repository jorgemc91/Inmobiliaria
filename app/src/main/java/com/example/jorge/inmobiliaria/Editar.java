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


public class Editar extends Activity {
    private ArrayList<Inmueble> alInmueble = new ArrayList<Inmueble>();
    private EditText etDirec, etNum, etDescri, etPrecio;
    private TextView tvNomfoto;
    private Spinner spTipo;
    private int index, idInmueble;
    private String nomFoto="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialogo_anadir);
        String direc, num, precio, tipo, desc, foto;

        Bundle inm = getIntent().getExtras();
        alInmueble = inm.getParcelableArrayList("inmuebles");

        index = inm.getInt("id");
        idInmueble = inm.getInt("idInmueble");
        direc = inm.getString("direccion");
        num = inm.getString("numero");
        precio = inm.getString("precio");
        tipo = inm.getString("tipo");
        desc = inm.getString("descripcion");
        foto = inm.getString("foto");

        etDirec = (EditText) findViewById(R.id.etDireccion);
        etNum = (EditText) findViewById(R.id.etNumero);
        etDescri = (EditText) findViewById(R.id.etDesc);
        etPrecio = (EditText) findViewById(R.id.etPrecio);
        tvNomfoto = (TextView) findViewById(R.id.tvNomfoto);
        spTipo = (Spinner) findViewById(R.id.sTipo);
        Spinner spinner = (Spinner) this.findViewById(R.id.sTipo);
        ArrayAdapter<CharSequence> adTipos = ArrayAdapter.createFromResource(this, R.array.tipos,
                android.R.layout.simple_spinner_item);
        adTipos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adTipos);
        etDirec.setText(direc);
        etNum.setText(num);
        etPrecio.setText(precio);
        etDescri.setText(desc);
        tvNomfoto.setText(foto);
        if (tipo.equals("Piso")) {
            spTipo.setSelection(0);
        } else if (tipo.equals("Casa")) {
            spTipo.setSelection(1);
        } else if (tipo.equals("Chalet")) {
            spTipo.setSelection(2);
        } else if (tipo.equals("Cortijo")) {
            spTipo.setSelection(3);
        } else if (tipo.equals("Ático")) {
            spTipo.setSelection(4);
        }
    }

    public void anadir(View v) {
        final Inmueble inmueble = new Inmueble();
        try {
            String id = String.valueOf(idInmueble);
            String direc = etDirec.getText().toString().trim();
            String num = etNum.getText().toString().trim();
            String precio = etPrecio.getText().toString().trim();
            String tipo = String.valueOf(spTipo.getSelectedItem());
            String desc = etDescri.getText().toString().trim();
            String foto = tvNomfoto.getText().toString();
            if (direc.length() > 0 || num.length() > 0 || desc.length() > 0) {
                inmueble.setIdInmueble(id);
                inmueble.setDireccion(direc);
                inmueble.setNumcalle(num);
                inmueble.setPrecio(precio);
                inmueble.setTipo(tipo);
                inmueble.setDesc(desc);
                if(nomFoto.equals("")){
                    inmueble.setImg(foto);
                }else {
                    inmueble.setImg(nomFoto);
                }
                alInmueble.set(index,inmueble);
                tostada("Inmueble modificado");
                ArchivoXML xml = new ArchivoXML();
                xml.escribirXML(getApplicationContext(),alInmueble);
                Intent i = new Intent(this, Principal.class);
                startActivity(i);
            }
        }catch (Exception e){
            tostada("No se ha podido modificar");
        }
    }

    private static final int IDACTIVIDADFOTO=1;
    public void foto(View v){
        Intent i = new Intent (MediaStore.ACTION_IMAGE_CAPTURE);
        String nombreImg = "inmueble_"+getDatePhone()+".jpg";
        File f = new File(getExternalFilesDir(null), nombreImg);
        i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
        startActivityForResult(i, IDACTIVIDADFOTO);
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
