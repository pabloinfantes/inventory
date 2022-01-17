package com.example.inventory;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Color;
import android.os.Build;

public class InventoryAplication extends Application {

    public static final String IDCHANEL = "243424211";

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
    }

    /**
     * Este metodo crea el canal que se utilizara en las notificaciones de la aplicacion
     */
    private void createNotificationChannel() {

        //Se crea una clase NotificationChannel() solo que la API sea 26 o mas
        //pq no se ha incluido en las librerias de soporte
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            //1.- Definir la importancia
            int importancia = NotificationManager.IMPORTANCE_DEFAULT;
            //2.- Definir el nombre del canal
            String nameChannel = getString(R.string.name_channel);
            //3.- Se crea el canal
            NotificationChannel notificationChannel = new NotificationChannel(IDCHANEL,nameChannel,importancia);

            //OPCIONAL , configurar modo de luces,vibracion
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);

            //4.- Añadir este canal al notification manager
            getSystemService(NotificationManager.class).createNotificationChannel(notificationChannel);
        }
    }
}
