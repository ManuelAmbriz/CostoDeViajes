package com.example.hcprogrammer06.costodeviajes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by HCProgrammer 06 on 14/11/2017.
 */

public class gastosSQLiteHelper  extends SQLiteOpenHelper {

    //Sentencia SQL para crear la tabla Cars
    String sqlCreate = "CREATE TABLE gastos (idgastos INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, monto TEXT, concepto TEXT, fecha TEXT, viaje_idviaje TEXT)";


    public gastosSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS gastos");

        //Se crea la nueva versión de la tabla
        db.execSQL(sqlCreate);

    }

}
