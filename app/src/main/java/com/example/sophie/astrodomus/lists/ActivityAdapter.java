package com.example.sophie.astrodomus.lists;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sophie.astrodomus.R;
import com.example.sophie.astrodomus.models.Reporte;

import java.util.ArrayList;


public class ActivityAdapter extends BaseAdapter {

    private ArrayList<Reporte> list;
    private Context context;
    private int blade;


    public ActivityAdapter(ArrayList<Reporte> list, Context context, int blade) {
        this.list = list;
        this.context = context;
        this.blade = blade;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        Holder holder = new Holder();
        if(view==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v= inflater.inflate(blade, null);

            holder.image = v.findViewById(R.id.id_image_item_activity);
            holder.usuario = v.findViewById(R.id.id_text_item_activity);
            holder.detalle = v.findViewById(R.id.id_detalle_item_activity);

            Reporte reporte = list.get(i);
            holder.usuario.setText(reporte.getNombre());

            if(reporte.getDetalle().equals("null")){
                holder.detalle.setText(reporte.getTipoReporteDet());
            }else{
                holder.detalle.setText(reporte.getDetalle());
            }


            switch (reporte.getTipoReporte()){
                case "01":
                    holder.image.setBackgroundResource(R.drawable.ic_update);
                    break;
                case "02":
                    holder.image.setBackgroundResource(R.drawable.ic_in);
                    break;
                case "03":
                    holder.image.setBackgroundResource(R.drawable.ic_out);
                    break;
                case "04":
                    holder.image.setBackgroundResource(R.drawable.ic_component);
                    break;
                case "05":
                    holder.image.setBackgroundResource(R.drawable.ic_alert);
                    break;
            }

        }else{
             holder = (Holder) v.getTag();
        }

        return v;
    }

    public class Holder{
        ImageView image;
        TextView usuario, detalle;
    }
}

