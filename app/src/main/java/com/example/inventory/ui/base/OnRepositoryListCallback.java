package com.example.inventory.ui.base;

import java.util.List;

public interface OnRepositoryListCallback {


    <T> void onSuccess(List<T> list);
    void onFailure(String message);
    void onDeleteSucces(String message);
    void onUndoSuccess(String message);
}
