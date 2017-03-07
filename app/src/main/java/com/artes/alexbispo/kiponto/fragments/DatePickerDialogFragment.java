package com.artes.alexbispo.kiponto.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

/**
 * Created by alex on 05/03/17.
 */

public class DatePickerDialogFragment extends DialogFragment {

    private int year, month, day;
    private Listener listener;

    public static DatePickerDialogFragment getInstance(Listener listener, int year, int month, int day){
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
        final DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), null, year, month, day);
        datePickerDialog.setCancelable(true);
        datePickerDialog.setCanceledOnTouchOutside(true);
        datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int dayOfMonth = datePickerDialog.getDatePicker().getDayOfMonth();
                int month = datePickerDialog.getDatePicker().getMonth();
                int year = datePickerDialog.getDatePicker().getYear();
                listener.onSelectDate(dayOfMonth, month, year);
            }
        });
        datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.onCancelSelectDate(dialog);
            }
        });
        return datePickerDialog;
    }

    public interface Listener {
        void onSelectDate(int d, int m, int y);
        void onCancelSelectDate(DialogInterface dialog);
    }

}
