package com.jcalzas.groupeat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.jcalzas.groupeat.databinding.FragmentReseniasBinding;

public class FragmentResenias extends Fragment {
    private FragmentReseniasBinding binding;
    public FragmentResenias() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentReseniasBinding.inflate(inflater, container, false);
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}