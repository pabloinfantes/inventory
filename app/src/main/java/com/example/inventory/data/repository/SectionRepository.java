package com.example.inventory.data.repository;


import com.example.inventory.data.model.Dependency;
import com.example.inventory.data.model.Section;
import com.example.inventory.ui.base.OnRepositoryListCallback;
import com.example.inventory.ui.sections.SectionsFragmentContract;

import java.util.ArrayList;

public class SectionRepository implements SectionsFragmentContract.Repository {


    private static SectionRepository instance;
    private ArrayList<Dependency> list;
    //private DependencyDAO dependencyDAO;


    private SectionRepository(){
        list = new ArrayList<>();
        //dependencyDAO = InventoryDatabase.getDatabase().dependencyDAO();
    }

    public static SectionRepository getInstance(){
        if (instance == null){
            instance = new SectionRepository();
        }
        return instance;
    }


    @Override
    public void getList(OnRepositoryListCallback callback) {

    }

    @Override
    public void delete(Section section, OnRepositoryListCallback callback) {

    }

    @Override
    public void undo(Section section, OnRepositoryListCallback callback) {

    }
}
