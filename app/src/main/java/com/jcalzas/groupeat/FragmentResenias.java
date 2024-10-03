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
import com.jcalzas.groupeat.databinding.FragmentReseniasBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentResenias extends Fragment {
    private FragmentReseniasBinding binding;
    private List<Rowitem_Resenia> listaResenias = new ArrayList<>();
    private ArrayAdapter<Rowitem_Resenia> adapter;
    private ListView listView;
    public FragmentResenias() {
        // Required empty public constructor
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentReseniasBinding.inflate(inflater, container, false);
        Callback<JsonArray>callback = crearCallback();
        ((MainActivity)getActivity()).getService().getResenias(((MainActivity)getActivity())
                .getUsuario().getId()).enqueue(callback);
        return binding.getRoot();
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.botonRecomendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FragmentResenias.this)
                        .navigate(R.id.action_nav_fragmentResenias_to_nav_fragmentCrearResenia);
            }
        });
        binding.botonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FragmentResenias.this)
                        .navigate(R.id.action_nav_fragmentResenias_to_nav_fragmentMenuUsuario);
            }
        });
    }
    private Callback<JsonArray> crearCallback(){
        return new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                JsonArray respuesta = response.body();
                listaResenias = crearRowItems(respuesta);
                if (getActivity()!=null)
                    adapter = new ArrayAdapter<>(requireContext(),
                            android.R.layout.simple_list_item_1, listaResenias);
                listView = getParentFragment().getView().findViewById(R.id.listViewResenias);
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

    private List<Rowitem_Resenia> crearRowItems(JsonArray respuesta){
        List<Rowitem_Resenia> listaFilas = new ArrayList<>();
        JsonArray resenias = respuesta.getAsJsonArray();
        for(int i = 0; i < resenias.size(); i++){
            JsonObject datosR = resenias.get(i).getAsJsonObject();
            listaFilas.add(new Rowitem_Resenia(datosR.get("stars").getAsDouble(),
                    datosR.get("name").getAsString()));
        }
        return listaFilas;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}