package com.example.inventory.ui.sections;

import com.example.inventory.data.model.Dependency;
import com.example.inventory.data.model.Section;
import com.example.inventory.ui.base.BasePresenter;
import com.example.inventory.ui.base.IProgressView;
import com.example.inventory.ui.base.OnRepositoryListCallback;

import java.util.ArrayList;

public interface SectionsFragmentContract {


    interface View extends OnRepositoryListCallback, IProgressView {
        void showNoData();
        void showData(ArrayList<Section> list);

    }


    interface Presenter extends BasePresenter {

        //1.- Cargar los datos
        void load();
        //2.- Cuando se realiza una pulsacion larga se elimina
        void delete(Section section);
        //3.- Cuando el usuario pulsa la opcion undo del Snackbar
        void undo(Section section);


    }


    interface OnInteractorListener extends OnRepositoryListCallback {
    }


    interface Repository{
        //1.- Cargar los datos
        void getList(OnRepositoryListCallback callback);
        //2.- Cuando se realiza una pulsacion larga se elimina
        void delete(Section section, OnRepositoryListCallback callback);
        //3.- Cuando el usuario pulsa la opcion undo del Snackbar
        void undo(Section section ,OnRepositoryListCallback callback);
    }
}
