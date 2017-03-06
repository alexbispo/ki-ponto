package com.artes.alexbispo.kiponto.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.artes.alexbispo.kiponto.R;
import com.artes.alexbispo.kiponto.model.Company;

import static com.artes.alexbispo.kiponto.utils.DateUtils.DD_MM_YYYY;
import static com.artes.alexbispo.kiponto.utils.DateUtils.format;

public class CompanyShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_show);

        Intent intent = getIntent();
        Long company_id = (Long) intent.getSerializableExtra("company_id");
        Company company = new Company(this);
        company = company.find(company_id.longValue());

        if (company != null) fillView(company);
    }

    private void fillView(Company company){
        TextView tvName = (TextView) findViewById(R.id.company_show_name);
        TextView tvStartAt = (TextView) findViewById(R.id.company_show_start_at);
        TextView tvLeavingAt = (TextView) findViewById(R.id.company_show_leaving_at);

        tvName.setText(tvName.getText().toString() + company.getName());
        tvStartAt.setText(tvStartAt.getText().toString() + format(company.getStartAt(), DD_MM_YYYY));

        String leavingAtText = company.getLeavingAt() > 0 ? format(company.getLeavingAt(), DD_MM_YYYY) : "hoje";
        tvLeavingAt.setText(tvLeavingAt.getText().toString() + leavingAtText);
    }
}
