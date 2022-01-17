package com.example.inventory.data.repository;

import com.example.inventory.data.model.User;
import com.example.inventory.ui.base.OnRepositoryCallback;
import com.example.inventory.ui.login.LoginContract;
import com.example.inventory.ui.signup.SignUpContract;

import java.util.ArrayList;


/**
 * Vamos a simular que la instancia de LoginRepository sea unica. Si es asi
 * PATRON SINGLETON
 * -- el primer requisito esque el constructor es privado
 * que todas las clases obtienen las instancia a traves de un metodo getInstance
 *
 */
public class LoginRepositoryStatic implements LoginContract.Repository, SignUpContract.Repository {

    private static LoginRepositoryStatic instance;
    private OnRepositoryCallback callback;
    private ArrayList<User> users;  //usuarios autorizados en mi app

    private LoginRepositoryStatic() {
        users = new ArrayList<User>();
        initialice();
    }

    /**
     * Es un metodo privado que inicializa  la estructura de datos de una clase
     */
    private void initialice() {
        users.add(new User("pabloinfantes@gmail.com","Pablo123&&"));
        users.add(new User("abc@gmail.com","Pablo123&&"));
    }


    public static LoginRepositoryStatic getInstance(OnRepositoryCallback listener) {
        if (instance == null) {
            instance = new LoginRepositoryStatic();
        }
        instance.callback = listener;
        return instance;
    }

    /**
     * Este es el metodo que comprueba si el usuario existe o no. Para ello hay que recorrer el arraylist
     * @param u
     */
    @Override
    public void login(User u) {
        for(User user : users){
            if (user.getEmail().equals(u.getEmail()) && user.getPassword().equals(u.getPassword())){
                callback.onSuccess("usuario correcto");
                return;
            }
        }
        //En caso contrario no existe
        callback.onFailure("Error en la autenticacion");
    }

    @Override
    public void SignUp(String user, String email, String password, String comfirmPassword) {
            for (User u : users){
                if (u.getEmail().equals(email) && u.getPassword().equals(password)){
                    callback.onFailure("usuario ya existente");
                    return;
                }else{
                    users.add(new User(email,password));
                    callback.onSuccess("usuario creado");
                    return;
                }
            }
        }



}
