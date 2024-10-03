package com.jcalzas.groupeat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.gson.JsonArray;
import com.jcalzas.groupeat.databinding.FragmentIdBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentID extends Fragment {

    private FragmentIdBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        binding = FragmentIdBinding.inflate(inflater, container, false);
        binding.labelError.setVisibility(View.INVISIBLE);  // Por defecto, lo oculta
        return binding.getRoot();
    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = binding.textInputID.getEditText().getText().toString();
                System.out.println(id);
                if (comprobarId(id)){
                    binding.labelError.setVisibility(View.VISIBLE);
                } else{
                    ((MainActivity)getActivity()).getUsuario().setId(id);
                    NavHostFragment.findNavController(FragmentID.this)
                            .navigate(R.id.action_nav_fragmentId_to_nav_fragmentMenuUsuario);
                }
            }
        });
        binding.botonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FragmentID.this)
                        .navigate(R.id.action_nav_fragmentId_to_nav_fragmentInicio);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private boolean comprobarId(String id){
        //Error = true --> hay algo mal
        boolean error = false;
        if (binding.textInputID.getEditText().getText().toString().isEmpty()){
            error =true;
        } else {
            Call<JsonArray> call = ((MainActivity)getActivity()).getService().getInfoUsuario(id);
            call.enqueue(new Callback<JsonArray>() {
                @Override
                public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                    if (response.isSuccessful()) {
                        // Obtener el JsonArray de la respuesta
                        JsonArray jsonArray = response.body();

                        if (jsonArray != null && jsonArray.size() >= 2) {
                            // Extraer el primer campo como un String para el nombre
                            ((MainActivity)getActivity()).getUsuario().setNombre(jsonArray.get(0).getAsString());

                            // Extraer el segundo campo como una lista de Strings (amigos)
                            JsonArray amigosJsonArray = jsonArray.get(1).getAsJsonArray();
                            ((MainActivity)getActivity()).getUsuario().inicializarAmigos();
                            for (int i = 0; i < amigosJsonArray.size(); i++) {
                                ((MainActivity)getActivity()).getUsuario().getAmigos().add(amigosJsonArray.get(i).getAsString());
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<JsonArray> call, Throwable t) {

                }

            });

        }
        return error;
    }
}