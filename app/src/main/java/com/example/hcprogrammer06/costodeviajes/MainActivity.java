package com.example.hcprogrammer06.costodeviajes;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase db;
    private  viajesSQLiteHelper viajesHelper;
    private ListView listView;
    //List<String> viajes;
    private List<viajesSQL> viajes;

    private MyAdapter myAdapter;
    Button agregarViaje;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nuevoVieje = new Intent(MainActivity.this, viajes.class);
                startActivity(nuevoVieje);

                //create();
                update();
                myAdapter.notifyDataSetChanged();
            }
        });

        listView = (ListView) findViewById(R.id.Viajes);
        viajes = new ArrayList<viajesSQL>();
        //viajes = new ArrayList<String>();
        //Abrimos la base de datos 'DBTest1' en modo escritura
        viajesHelper = new viajesSQLiteHelper(this, "DBGastosEnViajes22", null, 1);
        db = viajesHelper.getWritableDatabase();

        Bundle datosr = getIntent().getExtras();
        if(datosr != null) {
            String lugar2 = datosr.getString("lugarkey");
            String fechainicio2 = datosr.getString("fechainiciokey");
            String fechafin2 = datosr.getString("fechafinkey");

            Log.d("prueba", "Creacion de base de datos " + db + " " + lugar2 + fechafin2);
            if (lugar2 != null && fechainicio2 != null && fechafin2 != null)
                create(lugar2, fechainicio2, fechafin2);
        }
        myAdapter = new MyAdapter(this, R.layout.viajes_iteam , viajes);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                viajesSQL currentName = viajes.get(position);
                Intent nuevoVieje2 = new Intent(MainActivity.this, gastosxdia.class);
                Bundle idviaje = new Bundle();
                idviaje.putString("idviajes", currentName.getidviajes()+"");
                nuevoVieje2.putExtras(idviaje);
                startActivity(nuevoVieje2);
                //Toast.makeText(MainActivity.this, "Pinchado en "+ viajes.get(position), Toast.LENGTH_LONG).show();

            }
        });


        listView.setAdapter(myAdapter);
        update();
    }

    private List<viajesSQL> getAllViajes() {
        // Seleccionamos todos los registros de la tabla Cars
        Cursor cursor = db.rawQuery("select * from viajes", null);
        List<viajesSQL> list = new ArrayList<viajesSQL>();
        if(cursor != null) {
            if (cursor.moveToFirst()) {
                // iteramos sobre el cursor de resultados,
                // y vamos rellenando el array que posteriormente devolveremos
                while (cursor.isAfterLast() == false) {

                    int idviajes = cursor.getInt(cursor.getColumnIndex("idviajes"));
                    String lugar = cursor.getString(cursor.getColumnIndex("lugar"));
                    String fechainicio = cursor.getString(cursor.getColumnIndex("fechainicio"));
                    String fechafin = cursor.getString(cursor.getColumnIndex("fechafin"));

                    list.add(new viajesSQL(idviajes, lugar, fechainicio, fechafin));
                    cursor.moveToNext();
                }
            }
        }
        return list;
    }

    public void create(String Lugar, String fechainicio, String fechafin) {

        //Toast.makeText(MainActivity.this, "Crear ", Toast.LENGTH_LONG).show();
        //Si hemos abierto correctamente la base de datos String lugar, String fechaincio, String fechafin
        Log.d("prueba", "Set creart con " + Lugar+ " "+  fechainicio + " " +fechafin + " " + db);
        if (db != null && Lugar != null && fechainicio != null && fechafin != null) {
            Log.d("prueba", "Set creart con " + Lugar+ " "+  fechainicio + " " +fechafin);
            //Creamos el registro a insertar como objeto ContentValues
            ContentValues nuevoRegistro = new ContentValues();
            // El ID es auto incrementable como declaramos en nuestro CarsSQLiteHelper
            nuevoRegistro.put("lugar",Lugar);
            nuevoRegistro.put("fechainicio",fechainicio);
            nuevoRegistro.put("fechafin", fechafin);



            //Insertamos el registro en la base de datos
            db.insert("viajes", null, nuevoRegistro);
        }
    }


    private void removeAll() {
        db.delete("viajes", "", null);
    }

    public void update() {
        // borramos todos los elementos
        viajes.clear();
        // cargamos todos los elementos
        viajes.addAll(getAllViajes());
        // refrescamos el adaptador
        myAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        // cerramos conexión base de datos antes de destruir el activity
        db.close();
        super.onDestroy();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
