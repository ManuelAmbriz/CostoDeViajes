package com.example.hcprogrammer06.costodeviajes;

/**
 * Created by HCProgrammer 06 on 14/11/2017.
 */

public class viajesSQL {
    private  int  idviajes;
    private  String lugar;
    private  String fechainicio;
    private  String fechafin;

    public viajesSQL(int idviajes, String lugar, String fechainicio, String fechafin){
        this.idviajes = idviajes;
        this.lugar = lugar;
        this.fechainicio = fechainicio;
        this.fechafin = fechafin;
    }
    public int getidviajes() {
        return idviajes;
    }

    public void setidviajes(int idviajes) {
        this.idviajes = idviajes;
    }

    public String getlugar() {
        return lugar;
    }

    public void setlugar(String lugar) {
        this.lugar = lugar;
    }

    public String getfechainicio() {
        return fechainicio;
    }

    public void setfechainicio(String fechainicio) {
        this.fechainicio = fechainicio;
    }
    public String getfechafin() {
        return fechafin;
    }

    public void sefechafin(String fechafin) {
        this.fechafin = fechafin;
    }
}
