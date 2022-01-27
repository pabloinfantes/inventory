package com.example.inventory.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;

import com.example.inventory.data.dao.DependencyDAO;
import com.example.inventory.data.model.Dependency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//1.- Definir la configuracion de la base de datos
@Database(entities = {Dependency.class}, version = 1)
public abstract class InventoryDatabase extends androidx.room.RoomDatabase {


    //2.- Crear los metodos de obtencion de los DAO.
    public abstract DependencyDAO dependencyDAO();

    private static volatile InventoryDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);


    static InventoryDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (InventoryDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            InventoryDatabase.class, "inventory")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

  
    public static void create(final Context context) {
        if (INSTANCE == null) {
            synchronized (InventoryDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            InventoryDatabase.class, "inventory")
                            .build();
                }
            }
        }
    }

    public static InventoryDatabase getDatabase() {
        return INSTANCE;

    }
}
