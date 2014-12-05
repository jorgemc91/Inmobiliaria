package com.example.jorge.inmobiliaria;


import android.content.Context;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ArchivoXML{

    private ArrayList<Inmueble> alInmueble = new ArrayList<Inmueble>();

    public void escribirXML(Context context, ArrayList arrayList){
        alInmueble = arrayList;
        FileOutputStream fxml= null;


        try {
            fxml = new FileOutputStream(new File(context.getExternalFilesDir(null),"inmobiliaria.xml"));
            XmlSerializer docxml = Xml.newSerializer();
            docxml.setOutput(fxml, "UTF-8");
            docxml.startDocument(null, Boolean.valueOf(true));
            docxml.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
            docxml.startTag(null, "inmuebles");
            for (int i = 0; i < alInmueble.size() ; i++) {
                docxml.startTag(null, "inmueble");
                docxml.startTag(null, "id");
                docxml.text(alInmueble.get(i).getIdInmueble());
                docxml.endTag(null, "id");
                docxml.startTag(null, "direccion");
                docxml.text(alInmueble.get(i).getDireccion());
                docxml.endTag(null, "direccion");
                docxml.startTag(null, "numero");
                docxml.text(alInmueble.get(i).getNumcalle());
                docxml.endTag(null, "numero");
                docxml.startTag(null, "precio");
                docxml.text(alInmueble.get(i).getPrecio());
                docxml.endTag(null, "precio");
                docxml.startTag(null, "tipo");
                docxml.text(alInmueble.get(i).getTipo());
                docxml.endTag(null, "tipo");
                docxml.startTag(null, "descripcion");
                docxml.text(alInmueble.get(i).getDesc());
                docxml.endTag(null, "descripcion");
                docxml.startTag(null, "foto");
                docxml.text(alInmueble.get(i).getImg());
                docxml.endTag(null, "foto");
                docxml.endTag(null,"inmueble");
            }
            docxml.endTag(null,"inmuebles");
            docxml.endDocument();
            docxml.flush();
            fxml.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList leerXML(Context context){
        String id="", direc="",num="",precio="",tipo="",descri="",foto="";
        XmlPullParser leerxml=Xml.newPullParser();
        try {
            leerxml.setInput(new FileInputStream(new File(context.getExternalFilesDir(null),"inmobiliaria.xml")),"utf-8");
            int evento=leerxml.getEventType();

            while (evento!=XmlPullParser.END_DOCUMENT){
                if(evento==XmlPullParser.START_TAG){
                    String etiqueta=leerxml.getName();
                    if(etiqueta.compareTo("id")==0){
                        id = leerxml.nextText();
                    }else if(etiqueta.compareTo("direccion")==0){
                        direc = leerxml.nextText();
                    }else if(etiqueta.compareTo("numero")==0){
                        num = leerxml.nextText();
                    }else if(etiqueta.compareTo("precio")==0){
                        precio = leerxml.nextText();
                    }else if(etiqueta.compareTo("tipo")==0){
                        tipo = leerxml.nextText();
                    }else if(etiqueta.compareTo("descripcion")==0){
                        descri = leerxml.nextText();
                    }else if(etiqueta.compareTo("foto")==0) {
                        foto = leerxml.nextText();
                        alInmueble.add(new Inmueble(id, direc, num, precio, tipo, descri, foto));
                    }
                }
                evento=leerxml.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return alInmueble;
    }
}
