package com.jcalzas.groupeat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.jcalzas.groupeat.databinding.FragmentCiudadBinding;

import java.util.ArrayList;
import java.util.List;

public class FragmentCiudad extends Fragment {

    private FragmentCiudadBinding binding;
    private Rowitem_ciudad ciudad;
    private List<Rowitem_ciudad> listaCiudades = new ArrayList<>();

    public FragmentCiudad() {
        this.inicializarCiudades();
    }

    private void inicializarCiudades(){
        // TODO: Guardar los amigos cuando conecte la API
        Rowitem_ciudad ciu1 = new Rowitem_ciudad("Ciudad 1");
        this.listaCiudades.add(ciu1);
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

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonCiudad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FragmentCiudad.this)
                        .navigate(R.id.action_nav_fragmentCiudad_to_nav_fragmentResultados);
                guardarAmigo();
            }
        });
    }

    private void guardarAmigo(){
        int ciudadElegida = binding.listViewCiudad.getCheckedItemPosition();
        if (ciudadElegida != ListView.INVALID_POSITION) {
            // Guarda el índice del elemento seleccionado, por ejemplo, en SharedPreferences
            this.ciudad = this.listaCiudades.get(ciudadElegida);
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