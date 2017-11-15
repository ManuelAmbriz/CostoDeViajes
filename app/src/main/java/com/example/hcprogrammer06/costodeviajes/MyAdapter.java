package com.example.hcprogrammer06.costodeviajes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public  class MyAdapter extends BaseAdapter {
    private Context contex;
    private int layout;
    private List<viajesSQL> viajes;

    public MyAdapter (Context contex, int layout, List<viajesSQL> viajes){
        this.contex = contex;
        this.layout = layout;
        this.viajes = viajes;
    }
    @Override
    public int getCount() {
        return viajes.size();
    }

    @Override
    public Object getItem(int position) {
        return viajes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            LayoutInflater layoutInflater = LayoutInflater.from(this.contex);
            convertView = layoutInflater.inflate(R.layout.viajes_iteam, null);
            holder = new ViewHolder();
            holder.lugarTextView  = (TextView) convertView.findViewById(R.id.lugar);
            holder.fechaInicio  = (TextView) convertView.findViewById(R.id.fechainicio);
            holder.fechaFin  = (TextView) convertView.findViewById(R.id.fechatermino);

            convertView.setTag(holder);
        }else{holder= (ViewHolder) convertView.getTag();
        }
        viajesSQL currentName = viajes.get(position);
       holder.lugarTextView.setText(currentName.getlugar());
        holder.fechaInicio.setText(currentName.getfechainicio());
        holder.fechaFin.setText(currentName.getfechafin());

        return convertView;
    }

    static class ViewHolder{
        private TextView lugarTextView;
        private TextView fechaInicio;
        private TextView fechaFin;
        private TextView total;

        public TextView concepto;
    }
}