package com.jcalzas.groupeat;

import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
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
import com.jcalzas.groupeat.databinding.FragmentAmigosBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentAmigos extends Fragment {

    private FragmentAmigosBinding binding;
    private List<Rowitem_Amigos> listaAmigos = new ArrayList<>();
    private ArrayAdapter<Rowitem_Amigos> adapter;
    private ListView listView;
    public FragmentAmigos() {
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
        binding = FragmentAmigosBinding.inflate(inflater, container, false);
        Callback<JsonArray>callback = crearCallback();
        ((MainActivity)getActivity()).getService().getAmigos(((MainActivity)getActivity()).getUsuario().getId()).enqueue(callback);
        // Establecer eleccion multiple
        binding.listViewAmigos.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        return binding.getRoot();
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonAmigos.setOnClickListener(view1 -> {
            guardarAmigos();
            NavHostFragment.findNavController(FragmentAmigos.this)
                    .navigate(R.id.action_nav_fragmentAmigos_to_nav_fragmentCiudad);
        });
    }

    private Callback<JsonArray> crearCallback(){
        return new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                JsonArray respuesta = response.body();
                listaAmigos = crearRowItems(respuesta);
                if (getActivity()!=null)
                    adapter = new ArrayAdapter<>(requireContext(),
                            android.R.layout.simple_list_item_multiple_choice, listaAmigos);
                listView = getParentFragment().getView().findViewById(R.id.listViewAmigos);
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

    private List<Rowitem_Amigos> crearRowItems(JsonArray respuesta) {
        List<Rowitem_Amigos> listaFilas = new ArrayList<>();
        JsonArray amigos = respuesta.getAsJsonArray();
        for(int i = 0; i < amigos.size(); i++){
            JsonObject datosA = amigos.get(i).getAsJsonObject();
            listaFilas.add(new Rowitem_Amigos(datosA.get("name").getAsString(),
                    datosA.get("user_id").getAsString())
                    );
        }
        return listaFilas;
    }

    private void guardarAmigos() {
        SparseBooleanArray checked = binding.listViewAmigos.getCheckedItemPositions();
        for (int i = 0; i < checked.size(); i++) {
            int position = checked.keyAt(i);  // Obtener la posición real en la lista
            if (checked.valueAt(i)) {  // Verificar si está seleccionado
                ((MainActivity)getActivity()).aniadirAmigo(this.listaAmigos.get(position));  // Usar la posición para obtener el elemento
            }
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}