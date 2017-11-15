package com.example.hcprogrammer06.costodeviajes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class gastos extends AppCompatActivity {

    private Button btnCreate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gastos);

        btnCreate = (Button) findViewById(R.id.button2);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle idviaje = getIntent().getExtras();
                String IDViaje = idviaje.getString("idviajeskey");

                EditText montot = (EditText) findViewById(R.id.editText8);
                String monto = montot.getText().toString();
                EditText conceptot = (EditText) findViewById(R.id.editText2);
                String concepto = conceptot.getText().toString();
                EditText fechat = (EditText) findViewById(R.id.editText3);
                String fecha = fechat.getText().toString();

                Intent nuevoViaje2 = new Intent(gastos.this, gastosxdia.class);
                Bundle datosgastos = new Bundle();
                datosgastos.putString("lugarkey", monto);
                datosgastos.putString("conceptokey", concepto);
                datosgastos.putString("fechakey", fecha);
                datosgastos.putString("idviajes", IDViaje);
                nuevoViaje2.putExtras(datosgastos);
                startActivity(nuevoViaje2);

            }
        });
    }
}
