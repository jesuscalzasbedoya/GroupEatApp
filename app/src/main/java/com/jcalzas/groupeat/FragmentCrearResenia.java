package com.jcalzas.groupeat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.gson.JsonArray;
import com.jcalzas.groupeat.databinding.FragmentCrearReseniaBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentCrearResenia extends Fragment {
    private FragmentCrearReseniaBinding binding;
    public FragmentCrearResenia() {

    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentCrearReseniaBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.crearResenia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!comprobarResenia(binding.idInput.getEditText().getText().toString(),
                        binding.valoracionInput.getEditText().getText().toString())){
                    NavHostFragment.findNavController(FragmentCrearResenia.this)
                            .navigate(R.id.action_nav_fragmentCrearResenia_to_nav_fragmentCrearResenia);
                } else{
                    navegarResenias();
                }

            }
        });
        binding.botonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navegarResenias();
            }
        });
    }

    private void navegarResenias(){
        NavHostFragment.findNavController(FragmentCrearResenia.this)
                .navigate(R.id.action_nav_fragmentCrearResenia_to_nav_fragmentMenuUsuario);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private boolean comprobarResenia(String restauranteId, String valoracion){
        boolean resultado = false;
        if(!restauranteId.isEmpty()){
            Double stars = Double.parseDouble(valoracion);
            String user_id=((MainActivity) getActivity()).getUsuario().getId();
            if (stars >=1 && stars<=5){
                ((MainActivity)getActivity()).getService().crearResenia(
                        user_id, restauranteId, stars)
                        .enqueue(new Callback<JsonArray>() {
                        @Override
                        public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                            if (response.isSuccessful()) {
                                JsonArray respuesta = response.body();
                            }
                        }

                        @Override
                        public void onFailure(Call<JsonArray> call, Throwable t) {
                            // Error en la llamada
                        }
                });

            }
            resultado = true;
        }
        return resultado;
    }
}