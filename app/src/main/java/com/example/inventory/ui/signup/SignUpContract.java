package com.example.inventory.ui.signup;

import com.example.inventory.ui.base.BasePresenter;
import com.example.inventory.ui.base.IProgressView;
import com.example.inventory.ui.base.OnRepositoryCallback;
import com.example.inventory.ui.login.LoginContract;

public interface SignUpContract {

    interface View extends LoginContract.View , IProgressView {
        void setUserEmptyError();
        void setConfirmPasswordEmptyError();
        void setPasswordDontMatch();
        void setEmailError();
    }

    interface Presenter extends BasePresenter {
        void validateSignUp(String user,String email,String password,String comfirmPassword);

    }

    interface Repository{
        void SignUp(String user,String email,String password,String comfirmPassword);
    }

    interface OnSignUpInteractorListener extends LoginContract.OnInteractorListener , OnRepositoryCallback{
        void onUserEmptyError();
        void onConfirmPasswordEmptyError();
        void onEmailError();
        void onPasswordDontMatch();
    }




}
