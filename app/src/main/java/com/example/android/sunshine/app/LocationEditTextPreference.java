package com.example.android.sunshine.app;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by WSIM on 11/11/2016.
 */

public class LocationEditTextPreference extends EditTextPreference{

    static final private int DEFAULT_MINIMUM_LOCATION_LENGTH = 2;
    int minLength;

    public LocationEditTextPreference(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.LocationEditTextPreference,
                0,
                0);

        try{
            if(!a.hasValue(R.styleable.LocationEditTextPreference_minLength)){
                throw new RuntimeException("minLThe en required");
            }

            minLength = a.getInteger(R.styleable.LocationEditTextPreference_minLength, DEFAULT_MINIMUM_LOCATION_LENGTH);

        }finally{
            a.recycle();
        }
    }

    @Override
    protected void showDialog(Bundle state) {
        super.showDialog(state);

        EditText editText = getEditText();
       editText.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {

           }

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {

           }

           @Override
           public void afterTextChanged(Editable s) {
                Dialog d = getDialog();
                if(d instanceof AlertDialog){
                    AlertDialog dialog = (AlertDialog) d;
                    Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                    if(s.length()< minLength){
                        positiveButton.setEnabled(false);
                    }else{
                        positiveButton.setEnabled(true);
                    }
                }
           }
       });

    }
}
