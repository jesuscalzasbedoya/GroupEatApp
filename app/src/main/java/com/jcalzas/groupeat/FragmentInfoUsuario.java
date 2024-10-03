package com.jcalzas.groupeat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.gson.JsonArray;
import com.jcalzas.groupeat.databinding.FragmentInfoUsuarioBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentInfoUsuario extends Fragment {
    private FragmentInfoUsuarioBinding binding;
    public FragmentInfoUsuario() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentInfoUsuarioBinding.inflate(inflater, container, false);
        binding.contenidoNombre.setText(((MainActivity)getActivity()).getUsuario().getNombre());
        binding.contenidoId.setText(((MainActivity)getActivity()).getUsuario().getId());
        binding.contenidoAmigos.setText(((MainActivity)getActivity()).getUsuario().amigos());
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.guardarAmigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!comprobarId(binding.textInputID.getEditText().getText().toString())){
                    navegarInfoUsuario();
                }
            }
        });
        binding.botonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FragmentInfoUsuario.this)
                        .navigate(R.id.action_nav_fragmentInfoUsuario_to_nav_fragmentMenuUsuario);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void navegarInfoUsuario(){
        NavHostFragment.findNavController(FragmentInfoUsuario.this)
                .navigate(R.id.action_nav_fragmentInfoUsuario_to_nav_fragmentInfoUsuario);
    }
    private boolean comprobarId(String amigoId){
        boolean resultado = false;
        if (!amigoId.isEmpty()){
            ((MainActivity)getActivity()).getService().aniadirAmigo(
                    (((MainActivity) getActivity()).getUsuario().getId()), amigoId)
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
            ((MainActivity)getActivity()).getUsuario().aniadirAmigo(
                    binding.textInputID.getEditText().getText().toString());

        }
        return resultado;
    }
}