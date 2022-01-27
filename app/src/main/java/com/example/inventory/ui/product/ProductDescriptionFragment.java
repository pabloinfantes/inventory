package com.example.inventory.ui.product;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.inventory.databinding.FragmentProductDescriptionBinding;

public class ProductDescriptionFragment extends Fragment {

    private FragmentProductDescriptionBinding binding;

    public static Fragment newInstance(Bundle bundle){
        ProductDescriptionFragment fragment = new ProductDescriptionFragment();
        if(bundle != null){
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProductDescriptionBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }
}
