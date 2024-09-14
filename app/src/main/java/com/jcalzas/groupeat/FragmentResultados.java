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
import com.jcalzas.groupeat.databinding.FragmentResultadosBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentResultados extends Fragment {

    private FragmentResultadosBinding binding;

    private List<Rowitem_resultado> listaResultados = new ArrayList<>();

    private ArrayAdapter<Rowitem_resultado> adapter;
    private ListView listView;

    public FragmentResultados() {
        // TODO: Borrar
        //inicializarResultados();
    }

    private void inicializarResultados(){
        Rowitem_resultado res1 = new Rowitem_resultado("SoBou", 3.33, "310 Chartres St");
        Rowitem_resultado res2 = new Rowitem_resultado("Cane and Table", 3, "1113 Decatur St");
        Rowitem_resultado res3 = new Rowitem_resultado("Meauxbar", 3, "942 N Rampart St");
        Rowitem_resultado res4 = new Rowitem_resultado("Amelie", 2.67, "912 Royal St");
        Rowitem_resultado res5 = new Rowitem_resultado("The Rum House", 2.5, "3128 Magazine St");

        this.listaResultados.add(res1);
        this.listaResultados.add(res2);
        this.listaResultados.add(res3);
        this.listaResultados.add(res4);
        this.listaResultados.add(res5);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentResultadosBinding.inflate(inflater, container, false);
        binding.listViewResultados.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        // this hace referencia al fragment
        ResultadoAdapter adapter = new ResultadoAdapter(this, this.listaResultados);
        binding.listViewResultados.setAdapter(adapter);

        Callback<JsonArray> callback = crearCallback();
        ((MainActivity) getActivity()).getService().getResultados(
                ((MainActivity) getActivity()).getUser_id(),
                ((MainActivity) getActivity()).listaAmigos(),
                ((MainActivity) getActivity()).getCiudad().toString()
        ).enqueue(callback);

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.buttonRecomendarDeNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FragmentResultados.this)
                        .navigate(R.id.action_nav_fragmentResultados_to_nav_fragmentId);
            }
        });
    }

    private Callback<JsonArray> crearCallback(){
        return new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                JsonArray respuesta = response.body();
                listaResultados = crearRowItems(respuesta);
                if (getActivity()!=null)
                    adapter = new ArrayAdapter<>(requireContext(),
                            android.R.layout.simple_list_item_1, listaResultados);
                listView = getParentFragment().getView().findViewById(R.id.listViewResultados);
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

    // TODO: Revisar atributos
    private List<Rowitem_resultado> crearRowItems(JsonArray respuesta) {
        List<Rowitem_resultado> listaFilas = new ArrayList<>();
        for(int i = 0; i < respuesta.size(); i++){
            JsonObject datosR = respuesta.get(i).getAsJsonObject();
            listaFilas.add(new Rowitem_resultado(
                    datosR.get("Nombre").getAsString(),
                    datosR.get("Stars").getAsDouble(),
                    datosR.get("Direccion").getAsString()
            ));
        }
        return listaFilas;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}