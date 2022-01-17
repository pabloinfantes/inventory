package com.example.inventory.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.inventory.R;
import com.example.inventory.databinding.FragmentDashboardBinding;


public class DashBoardFragment extends Fragment implements View.OnClickListener{

    private FragmentDashboardBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        binding.imageButtonInventory.setOnClickListener(this);
        binding.imageButtonDependency.setOnClickListener(this);
        binding.imageButtonSections.setOnClickListener(this);
        binding.imageButtonProductos.setOnClickListener(this);
        binding.imageButtonSettings.setOnClickListener(this);
        binding.imageButtonAboutUs.setOnClickListener(this);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    @Override
    public void onClick(View view) {

    }
}