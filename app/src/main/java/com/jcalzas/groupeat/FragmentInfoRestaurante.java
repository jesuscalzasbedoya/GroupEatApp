package com.jcalzas.groupeat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.jcalzas.groupeat.databinding.FragmentInfoRestauranteBinding;

public class FragmentInfoRestaurante extends Fragment {

    private FragmentInfoRestauranteBinding binding;
    private static final String ARG_STRING1 = "SoBou";
    private static final Double ARG_DOUBLE = 3.33;
    private static final String ARG_STRING2 = "310 Chartres St";
    private static final String ARG_STRING3 = "string3";

    private String nombre;
    private Double valoracion;
    private String direccion;
    private String caracteristicas;

    public FragmentInfoRestaurante(){
        //TODO: Borrar
        this.inicializarResultados();
    }

    private void inicializarResultados(){
        this.nombre = ARG_STRING1;
        this.valoracion = ARG_DOUBLE;
        this.direccion = ARG_STRING2;
        this.caracteristicas = ARG_STRING3;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info_restaurante, container, false);
        view.findViewById(R.id.nombre).setTag(this.nombre);
        view.findViewById(R.id.valoracion).setTag(this.valoracion);
        view.findViewById(R.id.direccion).setTag(this.direccion);
        view.findViewById(R.id.caracteristicas).setTag(this.caracteristicas);
        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        binding.botonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FragmentInfoRestaurante.this)
                        .navigate(R.id.action_nav_fragmentInfoRestaurante_to_Resultados);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}