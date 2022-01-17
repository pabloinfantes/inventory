package com.example.inventory.ui.dependency;

import com.example.inventory.data.model.Dependency;
import com.example.inventory.ui.base.BasePresenter;
import com.example.inventory.ui.base.OnRepositoryCallback;
import com.example.inventory.ui.base.OnRepositoryListCallback;

public interface DependencyManageContract {


    interface View extends OnRepositoryCallback{
        void setShortNameEmpty();
        void setNameEmpty();
        void setDescriptionEmpty();
        void setImageEmpty();

    }

    interface Presenter extends BasePresenter {
        void add(Dependency dependency);
        void edit(Dependency dependency);
    }

    interface OnInteractorManageListener extends OnRepositoryCallback {
        void onShortNameEmpty();
        void onNameEmpty();
        void onDescriptionEmpty();
        void onImageEmpty();
    }

    interface Repository{
        void add(Dependency dependency ,OnRepositoryCallback callback);
        void edit(Dependency dependency, OnRepositoryCallback callback);
    }

}
