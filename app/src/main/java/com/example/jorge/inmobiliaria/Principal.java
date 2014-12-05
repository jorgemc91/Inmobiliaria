package com.example.jorge.inmobiliaria;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class Principal extends Activity {
    private ArrayList<Inmueble> alInmueble = new ArrayList<Inmueble>();
    private AdaptadorInmueble ain;
    private int resultado=1;

    private final int ACTIVIDADDOS = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);

        ArchivoXML xml = new ArchivoXML();
        alInmueble = xml.leerXML(getApplicationContext());

        ain = new AdaptadorInmueble(this, R.layout.detalle, alInmueble);
        final ListView lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(ain);
        registerForContextMenu(lv);
        ain.notifyDataSetChanged();

        //Para saber en que orientación estamos
        final FragmentoDetalle fdetalle = (FragmentoDetalle)getFragmentManager().findFragmentById(R.id.FDetalle);
        final boolean horizontal = fdetalle != null && fdetalle.isInLayout();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (horizontal){
                    fdetalle.setInmueble(getApplicationContext(),alInmueble,i);
                }else {
                    Intent intent = new Intent(Principal.this, Secundaria.class);
                    intent.putExtra("inmuebles",alInmueble);
                    intent.putExtra("posicion",i);
                    startActivityForResult(intent, ACTIVIDADDOS);
                }
            }
        });
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }else if (id == R.id.action_anadir){
            Intent i = new Intent(this,Anadir.class);
            Bundle b = new Bundle();
            b.putParcelableArrayList("inmuebles",alInmueble);
            i.putExtras(b);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int index = info.position;
        if (id == R.id.action_borrar) {
            alInmueble.remove(index);
            ain.notifyDataSetChanged();
            ArchivoXML xml = new ArchivoXML();
            xml.escribirXML(getApplicationContext(),alInmueble);
            tostada("Inmueble eliminado");
        } else if (id == R.id.action_editar) {
            return editar(index);
        }
        return super.onContextItemSelected(item);
    }

    private boolean editar(final int index) {
        final String idInmueble, direc, num, precio, tipo, desc, foto;
        Inmueble inmuebleA = new Inmueble();
        inmuebleA = alInmueble.get(index);
        idInmueble = inmuebleA.getIdInmueble();
        direc = inmuebleA.getDireccion();
        num = inmuebleA.getNumcalle();
        precio = inmuebleA.getPrecio();
        tipo = inmuebleA.getTipo();
        desc = inmuebleA.getDesc();
        foto = inmuebleA.getImg();
        Intent i = new Intent(this,Editar.class);
        Bundle b=new Bundle();
        b.putParcelableArrayList("inmuebles",alInmueble);
        i.putExtras(b);
        i.putExtra("id",index);
        i.putExtra("idInmueble",idInmueble);
        i.putExtra("direccion",direc);
        i.putExtra("numero",num);
        i.putExtra("precio",precio);
        i.putExtra("tipo",tipo);
        i.putExtra("descripcion",desc);
        i.putExtra("foto",foto);
        startActivity(i);
        return true;
    }

    private void tostada(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
