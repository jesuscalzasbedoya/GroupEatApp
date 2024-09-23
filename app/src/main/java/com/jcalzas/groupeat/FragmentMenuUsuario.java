package com.jcalzas.groupeat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.jcalzas.groupeat.databinding.FragmentMenuUsuarioBinding;

public class FragmentMenuUsuario extends Fragment {

    private FragmentMenuUsuarioBinding binding;
    public FragmentMenuUsuario() {

    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentMenuUsuarioBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.botonPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FragmentMenuUsuario.this)
                        .navigate(R.id.action_nav_fragmentMenuUsuario_to_nav_fragmentInfoUsuario);
            }
        });
        binding.botonResenias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FragmentMenuUsuario.this)
                        .navigate(R.id.action_nav_fragmentMenuUsuario_to_nav_fragmentResenias);
            }
        });
        binding.botonRecomendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FragmentMenuUsuario.this)
                        .navigate(R.id.action_nav_fragmentMenuUsuario_to_nav_fragmentAmigos);
            }
        });
        binding.botonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FragmentMenuUsuario.this)
                        .navigate(R.id.action_nav_fragmentMenuUsuario_to_nav_fragmentId);
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}