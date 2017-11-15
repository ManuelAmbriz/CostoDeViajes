package com.example.hcprogrammer06.costodeviajes;

/**
 * Created by HCProgrammer 06 on 14/11/2017.
 */

public class gastosSQL {
    private  int  idgasto;
    private  String monto;
    private  String concepto;
    private  String fecha;
    private  String viaje_idviaje;

    public gastosSQL(int idgasto, String monto, String concepto, String fecha, String viaje_idviaje){
        this.idgasto = idgasto;
        this.monto = monto;
        this.fecha = fecha;
        this.concepto = concepto;
        this.viaje_idviaje = viaje_idviaje;
    }
    public int getidgasto() {
        return idgasto;
    }

    public void setidgasto(int idgasto) {
        this.idgasto = idgasto;
    }

    public String getmonto() {
        return monto;
    }

    public void setmonto(String monto) {
        this.monto = monto;
    }

    public String getconcepto() {
        return concepto;
    }

    public void setconcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getfecha() {
        return fecha;
    }

    public void sefecha(String fecha) {
        this.fecha = fecha;
    }

    public String getviaje_idviaje() {
        return viaje_idviaje;
    }

    public void setviaje_idviaje(String viaje_idviaje) {
        this.viaje_idviaje = viaje_idviaje;
    }
}
