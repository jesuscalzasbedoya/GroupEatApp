package com.jcalzas.groupeat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.gson.JsonArray;
import com.jcalzas.groupeat.databinding.FragmentCrearUsuarioBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentCrearUsuario extends Fragment {
    private FragmentCrearUsuarioBinding binding;

    public FragmentCrearUsuario () {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentCrearUsuarioBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.botonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!comprobarUsuarioId(binding.idUsuario.getEditText().getText().toString(),
                        binding.nombreUsuario.getEditText().getText().toString())){
                    NavHostFragment.findNavController(FragmentCrearUsuario.this)
                            .navigate(R.id.action_nav_fragmentCrearUsuario_to_nav_fragmentCrearUsuario);
                } else{
                    volverInicio();
                }
            }
        });
        binding.botonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                volverInicio();
            }
        });
    }
    private void volverInicio(){
        NavHostFragment.findNavController(FragmentCrearUsuario.this)
                .navigate(R.id.action_nav_fragmentCrearUsuario_to_nav_fragmentInicio);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public boolean comprobarUsuarioId(String usuarioId, String nombre){
        boolean resultado=false;
        if (!usuarioId.isEmpty() && !nombre.isEmpty()){
            ((MainActivity)getActivity())
                    .getService().crearUsuario(usuarioId, nombre).enqueue(new Callback<JsonArray>() {
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
            resultado= true;
        }
        return resultado;
    }
}