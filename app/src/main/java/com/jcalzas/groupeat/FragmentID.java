package com.jcalzas.groupeat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.jcalzas.groupeat.databinding.FragmentIdBinding;

public class FragmentID extends Fragment {

    private FragmentIdBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentIdBinding.inflate(inflater, container, false);
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
                    NavHostFragment.findNavController(FragmentID.this)
                            .navigate(R.id.action_nav_fragmentId_to_nav_fragmentId);
                } else{
                    ((MainActivity)getActivity()).setUser_id(id);
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
        //TODO: completar método comprobarId
        //Error = true --> hay algo mal
        boolean error;
        if (!binding.textInputID.getEditText().getText().toString().isEmpty()){
            error=true;
        } else if (false){

        } else {

        }
        return false;
    }
}