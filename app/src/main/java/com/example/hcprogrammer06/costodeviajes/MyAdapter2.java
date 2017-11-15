package com.example.hcprogrammer06.costodeviajes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;


public class MyAdapter2 extends BaseAdapter {
    private Context contex;
    private int layout;
    private List<gastosSQL> gastos;

    public MyAdapter2  (Context contex, int layout, List<gastosSQL> gastos){
        this.contex = contex;
        this.layout = layout;
        this.gastos = gastos;
    }
    @Override
    public int getCount() {
        return gastos.size();
    }

    @Override
    public Object getItem(int position) {
        return gastos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyAdapter2.ViewHolder holder;
        if(convertView == null){
            LayoutInflater layoutInflater = LayoutInflater.from(this.contex);
            convertView = layoutInflater.inflate(R.layout.gastos_item, null);
            holder = new MyAdapter2.ViewHolder();
            holder.concepto  = (TextView) convertView.findViewById(R.id.textView10);
            holder.monto  = (TextView) convertView.findViewById(R.id.textView11);
            holder.fecha  = (TextView) convertView.findViewById(R.id.textView12);

            convertView.setTag(holder);
        }else{holder= (MyAdapter2.ViewHolder) convertView.getTag();
        }
        gastosSQL currentName = gastos.get(position);
        holder.concepto.setText(currentName.getconcepto());
        holder.monto.setText(currentName.getmonto());
        holder.fecha.setText(currentName.getfecha());


        return convertView;
    }

    static class ViewHolder{
        private TextView concepto;
        private TextView monto;
        private TextView fecha;


    }
}
