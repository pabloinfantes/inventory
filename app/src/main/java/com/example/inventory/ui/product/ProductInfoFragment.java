package com.example.inventory.ui.product;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.inventory.databinding.FragmentProductBinding;
import com.example.inventory.databinding.FragmentProductInfoBinding;

public class ProductInfoFragment extends Fragment {

    private FragmentProductInfoBinding binding;

    public static Fragment newInstance(Bundle bundle){
        ProductInfoFragment fragment = new ProductInfoFragment();
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
        binding = FragmentProductInfoBinding.inflate(inflater,container,false);
        return  binding.getRoot();
    }
}
