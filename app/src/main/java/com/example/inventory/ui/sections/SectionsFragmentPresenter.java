package com.example.inventory.ui.sections;

import com.example.inventory.data.model.Section;

import java.util.ArrayList;
import java.util.List;

public class SectionsFragmentPresenter implements SectionsFragmentContract.Presenter, SectionsFragmentContract.OnInteractorListener {

    private SectionsFragmentContract.View view;
    private SectionsFragmentInteractor interactor;

    public SectionsFragmentPresenter(SectionsFragmentContract.View view) {
        this.view = view;
        this.interactor = new SectionsFragmentInteractor(this);
    }

    @Override
    public void onDestroy() {
        this.view = null;
        this.interactor = null;
    }

    @Override
    public <T> void onSuccess(List<T> list) {
        if (list.size() == 0){
            view.showNoData();
        }else {
            view.showData((ArrayList<Section>) list);
        }
    }

    @Override
    public void onFailure(String message) {
        view.onFailure(message);
    }

    @Override
    public void onDeleteSucces(String message) {
        view.onDeleteSucces(message);
    }

    @Override
    public void onUndoSuccess(String message) {
        view.onUndoSuccess(message);
    }

    @Override
    public void load() {
        interactor.load();
    }

    @Override
    public void delete(Section section) {
        interactor.delete(section);
    }

    @Override
    public void undo(Section section) {
        interactor.undo(section);
    }


}
