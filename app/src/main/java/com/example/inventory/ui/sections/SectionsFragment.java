package com.example.inventory.ui.sections;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.inventory.R;
import com.example.inventory.data.model.Section;
import com.example.inventory.databinding.FragmentSectionsBinding;

import java.util.ArrayList;
import java.util.List;


public class SectionsFragment extends Fragment implements SectionsFragmentContract.View{

    private FragmentSectionsBinding binding;
    private SectionsFragmentContract.Presenter presenter;
    private SectionAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new SectionsFragmentPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         binding = FragmentSectionsBinding.inflate(inflater, container, false);
         return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRv();
    }

    private void initRv() {
        adapter = new SectionAdapter(new ArrayList<>());

    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.load();
    }

    @Override
    public void showProgress() {
        //
    }


    @Override
    public void hideProgress() {
        //
    }

    @Override
    public <T> void onSuccess(List<T> list) {
        //
    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteSucces(String message) {

    }

    @Override
    public void onUndoSuccess(String message) {

    }

    @Override
    public void showNoData() {

    }

    @Override
    public void showData(ArrayList<Section> list) {

    }

}