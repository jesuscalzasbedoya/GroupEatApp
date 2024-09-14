package com.jcalzas.groupeat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.navigation.fragment.NavHostFragment;

import java.util.List;

public class ResultadoAdapter extends ArrayAdapter<Rowitem_resultado> {
    private FragmentResultados mFragment;
    private List<Rowitem_resultado> mListaResultados;

    public ResultadoAdapter(FragmentResultados fragment, List<Rowitem_resultado> listaResultados) {
        super(fragment.getContext(), 0, listaResultados);
        mFragment = fragment;
        mListaResultados = listaResultados;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mFragment.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        Rowitem_resultado currentItem = mListaResultados.get(position);
        TextView textView = convertView.findViewById(android.R.id.text1);
        textView.setText(currentItem.getRestaurante());

        // Añadir listener para navegar al nuevo fragmento
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putString("nombre", currentItem.getRestaurante());
                args.putDouble("stars", currentItem.getValoracion());
                args.putString("direccion", currentItem.getDireccion());

                NavHostFragment.findNavController((FragmentResultados) mFragment)
                        .navigate(R.id.action_nav_fragmentResultados_to_nav_fragmentId, args);
            }
        });

        return convertView;
    }
}