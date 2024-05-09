package com.jcalzas.groupeat;

import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.jcalzas.groupeat.databinding.FragmentAmigosBinding;

import java.util.ArrayList;
import java.util.List;

public class FragmentAmigos extends Fragment {

    private FragmentAmigosBinding binding;
    List<Rowitem_Amigos> listaAmigos = new ArrayList<>();

    List<Rowitem_Amigos> amigosSeleccionados = new ArrayList<>();

    public FragmentAmigos() {
        inicializarAmigos();
    }

    private void inicializarAmigos(){
        // TODO: Guardar los amigos cuando conecte la API
        Rowitem_Amigos u1 = new  Rowitem_Amigos("NombreU1", "u1");
        this.listaAmigos.add(u1);
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

        // Establecer eleccion multiple
        binding.listViewAmigos.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        ArrayAdapter<Rowitem_Amigos> adapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_list_item_multiple_choice, this.listaAmigos);

        binding.listViewAmigos.setAdapter(adapter);
        return binding.getRoot();

    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonAmigos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FragmentAmigos.this)
                        .navigate(R.id.action_nav_fragmentAmigos_to_nav_fragmentCiudad);
                guardarAmigos();
            }
        });
    }

    private void guardarAmigos(){
        this.listaAmigos.clear();
        SparseBooleanArray checked = binding.listViewAmigos.getCheckedItemPositions();
        for (int i = 0; i < checked.size(); i++) {
            if (checked.valueAt(i)) {
                this.amigosSeleccionados.add(this.listaAmigos.get(i));
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}