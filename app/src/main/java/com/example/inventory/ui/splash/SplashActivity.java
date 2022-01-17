package com.example.inventory.ui.splash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.inventory.R;
import com.example.inventory.data.model.User;
import com.example.inventory.databinding.ActivitySplashBinding;
import com.example.inventory.ui.MainActivity;
import com.example.inventory.ui.login.LoginActivity;


public class SplashActivity extends AppCompatActivity {
    ActivitySplashBinding binding;

    //Declarar variable constante privada
    private static final long WAIT_TIME = 2000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

    }


    /**
     * Vamos a simular un tiempo de espera con un hilo que duerme 2 segundos y
     * cuando despierta se ejecutara un metodo startLogin() que inicia la activity login
     */
    @Override
    protected void onStart() {
        super.onStart();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (saveSession()) {
                    startApp();
                } else {
                    starLogin();
                }
            }
        }, WAIT_TIME);
    }
    /**
     * Metodo que comprueba si el usuario ha iniciado sesion y se ha guardado
     * su email en el fichero de preferencias DefaultSharePreferences
     * @return
     */
    private boolean saveSession() {
        return (PreferenceManager.getDefaultSharedPreferences(this).contains(User.TAG));
    }





    private void starLogin() {
        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        //Voy a llamar de forma explicita al metodo finish() de una activity, para eliminar
        //esta activity de la pila de actividades, porque si el usuario pulsa back no que se visualice de nuevo.
        finish();
    }

    private void startApp() {
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        //Voy a llamar de forma explicita al metodo finish de una activity para
        //eliminar esta activity de la pila de actividades porque si el usuario
        //pulsa back, no queremos que se visualice
        finish();
    }
}