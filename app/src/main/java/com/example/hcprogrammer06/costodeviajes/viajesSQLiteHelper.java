package com.example.hcprogrammer06.costodeviajes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by HCProgrammer 06 on 14/11/2017.
 */

public class viajesSQLiteHelper extends SQLiteOpenHelper {

    //Sentencia SQL para crear la tabla Cars
    String sqlCreate = "CREATE TABLE viajes (idviajes INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, lugar TEXT, fechainicio TEXT, fechafin TEXT); " +
            "CREATE TABLE gastos (idviajes INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, lugar TEXT, fechainicio TEXT, fechafin TEXT)";


    public viajesSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS viajes");

        //Se crea la nueva versión de la tabla
        db.execSQL(sqlCreate);
        //db.execSQL(sqlCreate2);
    }
}
