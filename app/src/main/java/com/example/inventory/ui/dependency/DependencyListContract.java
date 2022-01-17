package com.example.inventory.ui.dependency;

import com.example.inventory.data.model.Dependency;
import com.example.inventory.ui.base.BasePresenter;
import com.example.inventory.ui.base.IProgressView;
import com.example.inventory.ui.base.OnRepositoryCallback;
import com.example.inventory.ui.base.OnRepositoryListCallback;

import java.util.ArrayList;
import java.util.List;

public interface DependencyListContract {

    /**
     * Esta interfaz tiene los siguientes metodos
     * - La respuesta del repositorio
     * - Los metodos necesarios para mostrar un progreso
     * - Los metodos necesarios para gestionar los datos de un ReciclerView
     */
    interface View extends OnRepositoryListCallback , IProgressView {
        void showNoData();
        void showData(ArrayList<Dependency> list);
        //muestra el orden A-Z
        void showDataOrder();
        //Ordena de la Z a la A
        void showDataInverseOrder();
    }

    /**
     * Interfaz que debe implementar el presenter
     */
    interface Presenter extends BasePresenter {

        //1.- Cargar los datos
        void load();
        //2.- Cuando se realiza una pulsacion larga se elimina
        void delete(Dependency dependency);
        //3.- Cuando el usuario pulsa la opcion undo del Snackbar
        void undo(Dependency dependency);

        //4.- Que la lista se ordene por nombre
        void order();

    }

    /**
     * Interfaz que debe implementar el Listener del interactor
     * Esta interfaz muestra las posibles alternativa de los casos de uso
     * LISTAR ELEMENTOS (getList)
     * ELIMINAR (delete)
     * DESHACER (undo)
     */
    interface OnInteractorListener extends OnRepositoryListCallback {
    }

    /**
     * Interfaz que debe implementar toda clase que quiera ser un repositorio
     */
    interface Repository{
        //1.- Cargar los datos
        void getList(OnRepositoryListCallback callback);
        //2.- Cuando se realiza una pulsacion larga se elimina
        void delete(Dependency dependency, OnRepositoryListCallback callback);
        //3.- Cuando el usuario pulsa la opcion undo del Snackbar
        void undo(Dependency dependency ,OnRepositoryListCallback callback);
    }



}
