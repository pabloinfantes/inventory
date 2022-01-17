package com.example.inventory.ui.dependency;

import android.text.TextUtils;

import com.example.inventory.data.model.Dependency;
import com.example.inventory.data.repository.DependencyRepository;
import com.example.inventory.data.repository.LoginRepositoryImpl;
import com.example.inventory.ui.base.OnRepositoryCallback;
import com.example.inventory.ui.login.LoginInteractor;
import com.example.inventory.utils.CommonUtils;

public class DependencyManageInteractor implements OnRepositoryCallback {

    DependencyManageContract.OnInteractorManageListener listener;
    OnRepositoryCallback callback;

    public DependencyManageInteractor(DependencyManageContract.OnInteractorManageListener listener) {
        this.listener = listener;
        callback = this;
    }


    public void add(Dependency dependency){
        if (TextUtils.isEmpty(dependency.getShortname())){
            listener.onShortNameEmpty();
            return;
        }
        if (TextUtils.isEmpty(dependency.getName())){
            listener.onNameEmpty();
            return;
        }
        if (TextUtils.isEmpty(dependency.getDescription())){
            listener.onDescriptionEmpty();
            return;
        }
        if (TextUtils.isEmpty(dependency.getImage())){
            listener.onImageEmpty();
            return;
        }

        DependencyRepository.getInstance().add(dependency,callback);
    }


    public void edit(Dependency dependency){
        if (TextUtils.isEmpty(dependency.getShortname())){
            listener.onShortNameEmpty();
            return;
        }
        if (TextUtils.isEmpty(dependency.getName())){
            listener.onNameEmpty();
            return;
        }
        if (TextUtils.isEmpty(dependency.getDescription())){
            listener.onDescriptionEmpty();
            return;
        }
        if (TextUtils.isEmpty(dependency.getImage())){
            listener.onImageEmpty();
            return;
        }

        DependencyRepository.getInstance().edit(dependency,callback);
    }

    @Override
    public void onSuccess(String message) {
        listener.onSuccess(message);
    }

    @Override
    public void onFailure(String message) {
        listener.onFailure(message);
    }
}
