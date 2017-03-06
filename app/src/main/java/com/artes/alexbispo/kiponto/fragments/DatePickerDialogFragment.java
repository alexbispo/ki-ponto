package com.artes.alexbispo.kiponto.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

/**
 * Created by alex on 05/03/17.
 */

public class DatePickerDialogFragment extends DialogFragment {

    private int year, month, day;
    private DatePickerDialog.OnDateSetListener listener;

    public static DatePickerDialogFragment getInstance(DatePickerDialog.OnDateSetListener listener, int year, int month, int day){
        DatePickerDialogFragment instance = new DatePickerDialogFragment();
        instance.year = year;
        instance.month = month;
        instance.day = day;
        instance.listener = listener;
        return instance;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new android.app.DatePickerDialog(getActivity(), listener, year, month, day);
    }

}
