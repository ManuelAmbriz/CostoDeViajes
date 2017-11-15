package com.example.hcprogrammer06.costodeviajes;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class gastosxdia extends AppCompatActivity {
    private  gastosSQLiteHelper gastosHelper;
    private SQLiteDatabase db;

    private ListView listView;
    List<String> gastos2;
    List<gastosSQL> gastos;
    private MyAdapter2 myAdapter;



    Button agregarViaje;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gastosxdia);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Bundle idviajer = getIntent().getExtras();
        String idviajes = idviajer.getString("idviajes");
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle idviajer = getIntent().getExtras();
                String idviajes = idviajer.getString("idviajes");
                Bundle idviaje = new Bundle();
                Intent nuevogasto2 = new Intent(gastosxdia.this, gastos.class);
                idviaje.putString("idviajeskey",idviajes);
                nuevogasto2.putExtras(idviaje);
                startActivity(nuevogasto2);

            }
        });



        listView = (ListView) findViewById(R.id.Viajes);
        gastos2 = new ArrayList<String>();
        gastos2.add("Hospedaje");

        gastos = new ArrayList<gastosSQL>();
        gastosHelper = new gastosSQLiteHelper(this, "DBGastosEnViajes222", null, 1);
        db = gastosHelper.getWritableDatabase();

        Bundle datosr = getIntent().getExtras();
        if(datosr != null) {
            String monto = datosr.getString("lugarkey");
            String concepto = datosr.getString("conceptokey");
            String fecha = datosr.getString("fechakey");
            String viaje_idviaje = datosr.getString("idviajes");

            Log.d("prueba", "Creacion de base de datos " + db + " " + monto + concepto);
            if (monto != null && concepto != null && fecha != null)
                create(monto, concepto, fecha, viaje_idviaje );
        }

        myAdapter = new MyAdapter2(this, R.layout.gastos_item , gastos);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               // Toast.makeText(gastosxdia.this, "Pinchado en "+ gastos.get(position), Toast.LENGTH_LONG).show();
            }
        });


        listView.setAdapter(myAdapter);
        update(idviajes);
    }




    public List<gastosSQL> getAllGastos(String idviajes) {
        // Seleccionamos todos los registros de la tabla Cars
        Log.d("Gastos", "Entro a la funcion mostrar gasto con el idviajes "+idviajes);
        Cursor cursor = db.rawQuery("select * from gastos where viaje_idviaje = "+idviajes, null);
        List<gastosSQL> list = new ArrayList<gastosSQL>();

        if (cursor.moveToFirst()) {
            // iteramos sobre el cursor de resultados,
            // y vamos rellenando el array que posteriormente devolveremos
            while (cursor.isAfterLast() == false) {

                int idgastos= cursor.getInt(cursor.getColumnIndex("idgastos"));
                String monto = cursor.getString(cursor.getColumnIndex("monto"));
                String concepto = cursor.getString(cursor.getColumnIndex("concepto"));
                String fecha = cursor.getString(cursor.getColumnIndex("fecha"));
                String viaje_idviaje = cursor.getString(cursor.getColumnIndex("viaje_idviaje"));

                list.add(new gastosSQL(idgastos, monto,  concepto,  fecha, viaje_idviaje));
                cursor.moveToNext();
            }
        }
        return list;
    }
    public void create(String monto, String concepto, String fecha, String viaje_idviaje) {
        //Si hemos abierto correctamente la base de datos
        if (db != null) {
            //Creamos el registro a insertar como objeto ContentValues
            ContentValues nuevoRegistro = new ContentValues();
            // El ID es auto incrementable como declaramos en nuestro CarsSQLiteHelper

            nuevoRegistro.put("monto", monto);
            nuevoRegistro.put("concepto", concepto);
            nuevoRegistro.put("fecha", fecha);
            nuevoRegistro.put("viaje_idviaje", viaje_idviaje);



            //Insertamos el registro en la base de datos
            db.insert("gastos", null, nuevoRegistro);
        }
        Log.d("update" , "idviaje "+viaje_idviaje);
        update(viaje_idviaje);
    }


    private void removeAll() {
        db.delete("gastos", "", null);
    }

    private void update(String idviajes) {
        Log.d("update a ver que llega" , "idviaje "+idviajes);
        // borramos todos los elementos
        gastos.clear();
        // cargamos todos los elementos
        gastos.addAll(getAllGastos(idviajes));
        // refrescamos el adaptador
        //myAdapter.notifyDataSetChanged(idviajes);
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
