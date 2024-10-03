package com.jcalzas.groupeat;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jcalzas.groupeat.databinding.FragmentCiudadBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentCiudad extends Fragment {

    private FragmentCiudadBinding binding;
    private List<Rowitem_ciudad> listaCiudades = new ArrayList<>();
    private ArrayAdapter<Rowitem_ciudad> adapter;
    private ListView listView;
    public FragmentCiudad() {
        // TODO: Borrar
       // this.inicializarCiudades();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCiudadBinding.inflate(inflater, container, false);

        // Establecer eleccion multiple
        binding.listViewCiudad.setChoiceMode(ListView.CHOICE_MODE_SINGLE );

        ArrayAdapter<Rowitem_ciudad> adapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_list_item_single_choice, this.listaCiudades);

        binding.listViewCiudad.setAdapter(adapter);
        Callback<JsonArray> callback = crearCallback();
        //TODO: mirar amigos ""
        ((MainActivity)getActivity()).getService().getCiudades(((MainActivity)getActivity()).getUsuario().getId(), "u2").enqueue(callback);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonCiudad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FragmentCiudad.this)
                        .navigate(R.id.action_nav_fragmentCiudad_to_nav_fragmentResultados);
                guardarCiudad();
            }
        });
    }

    private Callback<JsonArray> crearCallback(){
        return new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                JsonArray respuesta = response.body();
                listaCiudades = crearRowItems(respuesta);
                if (getActivity()!=null)
                    adapter = new ArrayAdapter<>(requireContext(),
                            android.R.layout.simple_list_item_multiple_choice, listaCiudades);
                listView = getParentFragment().getView().findViewById(R.id.listViewCiudad);
                listView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Log.e("error", "error", t);
                Toast.makeText(
                        getActivity(),
                        "ERROR: " + t.getMessage(),
                        Toast.LENGTH_LONG
                ).show();
            }
        };
    }

    private List<Rowitem_ciudad> crearRowItems(JsonArray respuesta) {
        List<Rowitem_ciudad> listaFilas = new ArrayList<>();
        for(int i = 0; i < respuesta.size(); i++){
            JsonObject datosC = respuesta.get(i).getAsJsonObject();
            listaFilas.add(new Rowitem_ciudad(datosC.get("Ciudad").getAsString()));
        }
        return listaFilas;
    }


    private void guardarCiudad(){
        int ciudadElegida = binding.listViewCiudad.getCheckedItemPosition();
        if (ciudadElegida != ListView.INVALID_POSITION) {
            // Guarda el índice del elemento seleccionado, por ejemplo, en SharedPreferences
            ((MainActivity)getActivity()).setCiudad(this.listaCiudades.get(ciudadElegida));
        } else {
            // TODO : Manejar cuando no se seleccione ninguna ciudad
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}