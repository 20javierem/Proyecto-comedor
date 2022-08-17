package com.moreno.comedor.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.moreno.comedor.R;
import com.moreno.comedor.models.Diner;

import java.util.ArrayList;
import java.util.List;

public class DinerAdapter extends ArrayAdapter<Diner> {
    private List<Diner> diners;
    private Context context;

    public DinerAdapter(Context context, int resource) {
        super(context, resource , new ArrayList<>());
        this.context=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View layout= LayoutInflater.from(context).inflate(R.layout.item_diner,parent,false);
        Diner diner=getItem(position);
        TextView name=layout.findViewById(R.id.nameDiner);
        TextView dni=layout.findViewById(R.id.dniDiner);
        name.setText(diner.getLastNames()+", "+diner.getNames());
        dni.setText(diner.getDni());
        return layout;
    }
}
