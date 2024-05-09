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

import com.jcalzas.groupeat.databinding.FragmentResultadosBinding;

import java.util.ArrayList;
import java.util.List;

public class FragmentResultados extends Fragment {

    private FragmentResultadosBinding binding;

    private List<Rowitem_resultado> listaResultados = new ArrayList<>();

    public FragmentResultados() {
        inicializarResultados();
    }

    private void inicializarResultados(){
        Rowitem_resultado res1 = new Rowitem_resultado("Restaurante1", 4, "Calle Falsa 123");
        Rowitem_resultado res2 = new Rowitem_resultado("Restaurante2", 4.5, "Calle Falsa 123");
        this.listaResultados.add(res1);
        this.listaResultados.add(res2);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentResultadosBinding.inflate(inflater, container, false);
        binding.listViewResultados.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        ArrayAdapter<Rowitem_resultado> adapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_list_item_1, this.listaResultados);
        binding.listViewResultados.setAdapter(adapter);
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
}