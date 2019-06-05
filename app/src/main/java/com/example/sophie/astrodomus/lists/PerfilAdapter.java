package com.example.sophie.astrodomus.lists;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sophie.astrodomus.R;
import com.example.sophie.astrodomus.models.Perfil;

import java.util.ArrayList;


public class PerfilAdapter extends BaseAdapter {

    private ArrayList<Perfil> list;
    private Context context;
    private int blade;


    public PerfilAdapter(ArrayList<Perfil> list, Context context, int blade) {
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
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(blade, null);

            holder.image = v.findViewById(R.id.id_image_item_perfil);
            holder.usuario = v.findViewById(R.id.id_user_item_perfil);
            holder.ambiente = v.findViewById(R.id.id_ambiente_item_perfil);
            holder.tag = v.findViewById(R.id.id_tag_item_perfil);

            Perfil perfil = list.get(i);

            if(list == null){
                holder.image.setBackgroundResource(R.drawable.ic_empty);
                holder.ambiente.setText("No se encontraron perfiles");
                holder.ambiente.setGravity(View.TEXT_ALIGNMENT_CENTER);
                holder.ambiente.setTextSize(40);
                holder.usuario.setVisibility(View.INVISIBLE);
                holder.tag.setVisibility(View.INVISIBLE);

            }else{
                holder.usuario.setText(perfil.getUsuario() + "");
                holder.ambiente.setText(perfil.getAmbiente() + "");
                holder.tag.setText(perfil.getTag() + "");
            }

        }else{
            holder = (Holder) v.getTag();
        }
        return v;
    }
    public class Holder{
        ImageView image;
        TextView usuario, ambiente, tag;
    }
}

