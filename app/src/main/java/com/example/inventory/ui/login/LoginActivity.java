package com.example.inventory.ui.login;

import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inventory.R;
import com.example.inventory.data.model.User;
import com.example.inventory.databinding.ActivityLoginBinding;
import com.example.inventory.ui.MainActivity;
import com.example.inventory.ui.base.Event;
import com.example.inventory.ui.signup.SignUpActivity;
import com.example.inventory.utils.CommonUtils;
import com.google.android.gms.common.internal.service.Common;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity implements LoginContract.View{

    private ActivityLoginBinding binding;
    private LoginContract.Presenter presenter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btSignUp.setOnClickListener(view -> startActivityLogin());
        binding.btSignIn.setOnClickListener(view -> presenter.validateCredentials(new User(binding.tieEmail.getText().toString(),binding.tiePassword.getText().toString())));
        //Se inicializa el listener que escucha los eventos de los campos editables
        binding.tieEmail.addTextChangedListener(new LoginTextWatcher(binding.tieEmail));
        binding.tiePassword.addTextChangedListener(new LoginTextWatcher(binding.tiePassword));
        presenter = new LoginPresenter(this);
        //la vista se registra como subscriptor del EventBus
        EventBus.getDefault().register(this);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Se evitaria un futuro memory leaks
        presenter.onDestroy();
        //Se quita como subcriptor del EventBus
        EventBus.getDefault().unregister(this);
    }

    private void startMainActivity() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }

    private void startActivityLogin() {
        startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
    }


    //region Metodos del contrato LoginContract.View

    /**
     * Este metodo activa el error en el componente TextInputLayout y mostrar el texto oportuno
     */
    @Override
    public void setEmailEmptyError() {
        binding.tilEmail.setError(getString(R.string.errEmailEmpty));
    }

    @Override
    public void setPasswordEmptyError() {
        binding.tilPassword.setError(getString(R.string.errorPasswordEmpty));
    }

    @Override
    public void setPasswordError() {
        binding.tilPassword.setError(getString(R.string.errorPassword));
    }

    @Override
    public void showProgress() {
        binding.progressHorizontal.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        binding.progressHorizontal.setVisibility(View.INVISIBLE);
    }


    //endregion


    //region Metodos del contrato con LoginContract.View extend OnLoginListener es decir son los metodos que obtiene por herencia de la otra interfaz

    /**
     * El usuario existe en la base de datos, usuario contraseña correctos
     */
    @Override
    public void onSuccess(String message) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(this).edit();

        if (binding.chkRemember.isChecked()){
            editor.putString(User.TAG,binding.tieEmail.getText().toString());
            editor.apply();
            //O BIEN APPLY O BIEN COMMIT QUE SINO NO SE HACEN LOS CAMBIOS EN EK FICHERO

        }

        startMainActivity();
    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
    //endregion


    //region Clase interna que controla cada vez que el usuario introduce un caracter en un editable
    // TExtInputLayout si cumple o no las reglas de negocio

    class LoginTextWatcher implements TextWatcher{
        private View view;

        private LoginTextWatcher(View view){
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            //se deja vacio
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            //se deja vacio
        }

        @Override
        public void afterTextChanged(Editable editable) {
            switch (view.getId()){
                case R.id.tieEmail:
                    validateEmail(((EditText)view).getText().toString());
                    break;
                case R.id.tiePassword:
                    validatePassword(((EditText)view).getText().toString());
                    break;
            }
        }
    }

    /**
     *Metodo que valida que la contraseña mediante el metodo ya creado  en la clase CommonUtils
     * @param password
     */
    private void validatePassword(String password) {

        if (TextUtils.isEmpty(password)){
            binding.tilPassword.setError(getString(R.string.errorPasswordEmpty));
        } else if (!CommonUtils.isPasswordValid(password)){
            binding.tilPassword.setError(getString(R.string.errorPassword));
        } else{
            //desaparece el error
            binding.tilPassword.setError(null);
        }
    }


    /**
     * Metodo aue valida el email
     * 1.- no puede ser vacio
     * 2.- vamos a utilzar el patron por defecto de email para comprobar que es correcto
     * @param email
     */
    private void validateEmail(String email) {
        if (TextUtils.isEmpty(email)){
            binding.tilEmail.setError(getString(R.string.errEmailEmpty));
        }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.tilEmail.setError(getString(R.string.errEmail));
        } else{
            //desaparece el error
            binding.tilEmail.setError(null);
        }
    }


    @Subscribe
    public void onEvent(Event event){
        hideProgress();
        Toast.makeText(this,event.getMessage(),Toast.LENGTH_SHORT).show();

    }


    //endregion

}