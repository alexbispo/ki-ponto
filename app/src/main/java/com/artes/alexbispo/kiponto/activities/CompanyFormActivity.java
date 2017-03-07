package com.artes.alexbispo.kiponto.activities;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.artes.alexbispo.kiponto.R;
import com.artes.alexbispo.kiponto.fragments.DatePickerDialogFragment;
import com.artes.alexbispo.kiponto.model.Company;

import java.util.Calendar;
import java.util.HashMap;

import static com.artes.alexbispo.kiponto.utils.DateUtils.DD_MM_YYYY;
import static com.artes.alexbispo.kiponto.utils.DateUtils.format;
import static java.util.Calendar.*;

public class CompanyFormActivity extends AppCompatActivity implements DatePickerDialogFragment.Listener {

    private Button btnStartDate;
    private Button btnLeavingDate;
    private int btnDatePickerId;
    private Calendar startAt;
    private Calendar leavingAt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_companies_form);

        btnStartDate = (Button) findViewById(R.id.company_btn_start_date);
        btnLeavingDate = (Button) findViewById(R.id.company_btn_leaving_date);
        FloatingActionButton fabCompanyCreate = (FloatingActionButton) findViewById(R.id.fab_company_create);

//        startAt = Calendar.getInstance();
//        leavingAt = Calendar.getInstance();

        btnStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnDatePickerId = view.getId();

                Calendar c = startAt == null ? Calendar.getInstance() : startAt;

                DatePickerDialogFragment fragment = DatePickerDialogFragment.getInstance(
                        CompanyFormActivity.this, c.get(YEAR), c.get(MONTH),
                            c.get(DAY_OF_MONTH));
                FragmentManager manager = getSupportFragmentManager();
                fragment.show(manager, "");
            }
        });

        btnLeavingDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnDatePickerId = view.getId();
                Calendar c = leavingAt == null ? Calendar.getInstance() : leavingAt;

                DatePickerDialogFragment fragment = DatePickerDialogFragment.getInstance(
                        CompanyFormActivity.this, c.get(YEAR), c.get(MONTH),
                        c.get(DAY_OF_MONTH));
                FragmentManager manager = getSupportFragmentManager();
                fragment.show(manager, "");
            }
        });

        fabCompanyCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText edtName = (EditText) findViewById(R.id.company_txt_name);
                HashMap<String, String> attrbutes = new HashMap<>();
                attrbutes.put("name", edtName.getText().toString());
                if (startAt != null) attrbutes.put("startAt", String.valueOf(startAt.getTimeInMillis()));
                if (leavingAt != null) attrbutes.put("leavingAt", String.valueOf(leavingAt.getTimeInMillis()));

                Company company = new Company(CompanyFormActivity.this);
                company.setAttributes(attrbutes);
                if (company.create()) {
                    Snackbar.make(view, "Empresa salva com sucesso", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                    finish();
                    return;
                }

                Snackbar.make(view, "Erro ao tentar salvar empresa!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });
    }

    @Override
    public void onSelectDate(int d, int m, int y) {
        Calendar c = Calendar.getInstance();
        c.set(y, m, d);
//        String sDate = c.get(DAY_OF_MONTH) + "/"
//                + (c.get(MONTH) + 1) + "/" + c.get(YEAR);

        if (R.id.company_btn_start_date == btnDatePickerId) {
            btnStartDate.setText(format(c.getTimeInMillis(), DD_MM_YYYY));
            startAt = c;
        } else if (R.id.company_btn_leaving_date == btnDatePickerId) {
            btnLeavingDate.setText(format(c.getTimeInMillis(), DD_MM_YYYY));
            leavingAt = c;
        }
    }

    @Override
    public void onCancelSelectDate(DialogInterface dialog) {
        if (R.id.company_btn_start_date == btnDatePickerId) {
            btnStartDate.setText("Data de Início");
        } else if (R.id.company_btn_leaving_date == btnDatePickerId){
            btnLeavingDate.setText("Data de Saída");
        }
        dialog.cancel();
    }
}
