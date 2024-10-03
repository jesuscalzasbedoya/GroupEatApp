package com.jcalzas.groupeat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class AdapterResenias extends BaseAdapter {

    private final Context context;
    private final List<Rowitem_Resenia> rowItems;

    public AdapterResenias(Context context, List<Rowitem_Resenia> rowItems) {
        this.context = context;
        this.rowItems = rowItems;
    }

    @Override
    public int getCount() {
        return rowItems.size();
    }

    @Override
    public Object getItem(int position) {
        return rowItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.row_item_resenia, parent, false);

            holder = new ViewHolder();
            holder.tvNombre = convertView.findViewById(R.id.tvNombre);
            holder.tvCalificacion = convertView.findViewById(R.id.tvCalificacion);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Rowitem_Resenia rowItem = (Rowitem_Resenia) getItem(position);
        holder.tvNombre.setText(rowItem.getRestaurante());
        holder.tvCalificacion.setText(String.valueOf(rowItem.getValoracion()));

        return convertView;
    }

    private static class ViewHolder {
        TextView tvNombre;
        TextView tvCalificacion;
    }
}
