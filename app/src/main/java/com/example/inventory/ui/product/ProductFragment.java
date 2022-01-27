package com.example.inventory.ui.product;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.inventory.R;
import com.example.inventory.databinding.FragmentProductBinding;

public class ProductFragment extends Fragment {

    private FragmentProductBinding binding;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProductBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initNavigation();
    }

    /**
     * Este metodo configura rl listener del componente BottomNavigationView
     */
    private void initNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener(item -> {
         switch (item.getItemId()){
             case R.id.action_product_info:
                 loadFragment(ProductInfoFragment.newInstance(null));
                 break;
             case R.id.action_product_map:
                 loadFragment(ProductMapFragment.newInstance(null));
                 break;
             case R.id.action_product_description:
                 loadFragment(ProductDescriptionFragment.newInstance(null));
                 break;
         }
         return true;
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        loadFragment(ProductInfoFragment.newInstance(null));
    }

    /**
     * Este metodo carga un fragment hijo dentro de otros fragments.
     * Para ello se utiliza el metodo getChildFragmentManager
     * @param newInstance
     */
    private void loadFragment(Fragment newInstance) {
        if (newInstance != null){
            getChildFragmentManager().beginTransaction().replace(R.id.productContent,newInstance).commit();
        }
    }


}
