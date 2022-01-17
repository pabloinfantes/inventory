package com.example.inventory.ui.inventory;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inventory.R;
import com.example.inventory.databinding.FragmentAddInventoryBinding;
import com.example.inventory.databinding.FragmentDashboardBinding;


public class AddInventoryFragment extends Fragment {
    private FragmentAddInventoryBinding binding;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentAddInventoryBinding.inflate(inflater, container, false);
        Log.d(TAG, "AddInventoryFragment -> onCreateView()");
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnOK.setOnClickListener(view1 -> {
            NavHostFragment.findNavController(this).navigateUp();
        });
    }
}