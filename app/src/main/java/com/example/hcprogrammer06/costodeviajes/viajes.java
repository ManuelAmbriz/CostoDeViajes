package com.example.hcprogrammer06.costodeviajes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class viajes extends AppCompatActivity {
    private Button btnCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viajes);



        btnCreate = (Button) findViewById(R.id.button);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                EditText lugart = (EditText) findViewById(R.id.editText);
                String lugar = lugart.getText().toString();
                EditText fechainiciot = (EditText) findViewById(R.id.editText7);
                String fechainicio = fechainiciot.getText().toString();
                EditText fechafint = (EditText) findViewById(R.id.editText9);
                String fechafin = fechafint.getText().toString();


                Intent nuevoViaje2 = new Intent(viajes.this, MainActivity.class);
                Bundle datosviajes = new Bundle();
                datosviajes.putString("lugarkey", lugar);
                datosviajes.putString("fechainiciokey", fechainicio);
                datosviajes.putString("fechafinkey", fechafin);
                nuevoViaje2.putExtras(datosviajes);
                startActivity(nuevoViaje2);
                //mainActivity.create();
               // mainActivity.update();



            }
        });


    }
}
