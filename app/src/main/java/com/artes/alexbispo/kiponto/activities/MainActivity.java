package com.artes.alexbispo.kiponto.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.artes.alexbispo.kiponto.R;
import com.artes.alexbispo.kiponto.model.Company;

public class MainActivity extends AppCompatActivity {

    private ListView listViewCompanies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fabCompanyNew = (FloatingActionButton) findViewById(R.id.fab_company_new);
        listViewCompanies = (ListView) findViewById(R.id.list_companies);
        loadListViewCompanies();

        fabCompanyNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CompanyFormActivity.class);
                startActivity(intent);
            }
        });

        listViewCompanies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Company company = (Company) listViewCompanies.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this, CompanyShowActivity.class);
                intent.putExtra("company_id", Long.valueOf(company.getId()));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadListViewCompanies();
    }

    private void loadListViewCompanies() {
        Company company = new Company(this);
        listViewCompanies.setAdapter(new ArrayAdapter<Company>(this, android.R.layout.simple_list_item_1, company.all()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
