package com.example.inventory.ui.sections;

import com.example.inventory.data.model.Dependency;
import com.example.inventory.data.model.Section;
import com.example.inventory.data.repository.SectionRepository;
import com.example.inventory.ui.base.OnRepositoryListCallback;

import java.util.ArrayList;
import java.util.List;

public class SectionsFragmentInteractor implements OnRepositoryListCallback {

    private SectionsFragmentContract.OnInteractorListener listener;
    private OnRepositoryListCallback callback;

    public SectionsFragmentInteractor(SectionsFragmentContract.OnInteractorListener listener) {
        this.listener = listener;
        this.callback = this;
    }

    @Override
    public <T> void onSuccess(List<T> list) {
        listener.onSuccess((ArrayList<Dependency>)list);
    }

    @Override
    public void onFailure(String message) {
        listener.onFailure(message);
    }

    @Override
    public void onDeleteSucces(String message) {
        listener.onDeleteSucces(message);
    }

    @Override
    public void onUndoSuccess(String message) {
        listener.onUndoSuccess(message);
    }



    public void load(){
        SectionRepository.getInstance().getList(callback);
    }


    public void delete(Section section) {
        SectionRepository.getInstance().delete(section,callback);
    }


    public void undo(Section section) {
        SectionRepository.getInstance().undo(section,callback);
    }


}
