package com.artes.alexbispo.kiponto.activities.companies;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import com.artes.alexbispo.kiponto.R;
import com.artes.alexbispo.kiponto.fragments.DatePickerDialogFragment;

import java.util.Calendar;

public class CompaniesFormActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private int year, month, day;
    private Button btnStartDate;
    private Button btnLeavingDate;
    private int btnDatePickerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_companies_form);

        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        btnStartDate = (Button) findViewById(R.id.company_btn_start_date);
        btnLeavingDate = (Button) findViewById(R.id.company_btn_leaving_date);

        btnStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnDatePickerId = view.getId();
                DatePickerDialogFragment fragment = DatePickerDialogFragment.getInstance(CompaniesFormActivity.this, year, month, day);
                FragmentManager manager = getSupportFragmentManager();
                fragment.show(manager, "");
            }
        });

        btnLeavingDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnDatePickerId = view.getId();
                DatePickerDialogFragment fragment = DatePickerDialogFragment.getInstance(CompaniesFormActivity.this, year, month, day);
                FragmentManager manager = getSupportFragmentManager();
                fragment.show(manager, "");
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int y, int m, int d) {
        year = y;
        month = m;
        day = d;

        if (R.id.company_btn_start_date == btnDatePickerId) {
            btnStartDate.setText(day + "/" + (month + 1) + "/" + year);
        } else if (R.id.company_btn_leaving_date == btnDatePickerId) {
            btnLeavingDate.setText(day + "/" + (month + 1) + "/" + year);
        }
    }
}
