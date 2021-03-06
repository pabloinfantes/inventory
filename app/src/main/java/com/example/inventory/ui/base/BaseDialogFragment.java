package com.example.inventory.ui.base;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.icu.text.RelativeDateTimeFormatter;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class BaseDialogFragment extends DialogFragment {

    public static final String REQUEST ="requestDialog";
    public static final String KEY_BUNDLE ="result";
    public static final String TITLE ="title";
    public static final String MESSAGE ="message";

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        if (getArguments() != null){
            //Patron builder
            String title = getArguments().getString(TITLE);
            String message = getArguments().getString(MESSAGE);
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle(title);
            builder.setMessage(message);
            builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Bundle bundle = new Bundle();
                    bundle.putBoolean(KEY_BUNDLE,true);
                 getActivity().getSupportFragmentManager().setFragmentResult(REQUEST,bundle);
                }
            });


            builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dismiss();
                }
            });
            return builder.create();
        }
        return null;
    }
}
