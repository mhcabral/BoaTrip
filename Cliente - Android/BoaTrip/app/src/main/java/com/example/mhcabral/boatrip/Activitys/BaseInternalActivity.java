package com.example.mhcabral.boatrip.Activitys;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.mhcabral.boatrip.R;

import java.util.ArrayList;
import java.util.List;

public class BaseInternalActivity extends ActionBarActivity {
    private Toolbar mToolbar;

    private ListView listview;
    private ArrayAdapter adapter;
    List<String> resultados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_internal);

        //CUSTOM TOOLBAR
        mToolbar = (Toolbar) findViewById(R.id.tb_base_internal);
        mToolbar.setLogo(R.mipmap.ic_boatrip_logo);
        mToolbar.setTitle("BoaTrip");
        mToolbar.setSubtitle("Base Internal Activity");
        mToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        mToolbar.setSubtitleTextColor(getResources().getColor(android.R.color.white));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listview = (ListView) findViewById(R.id.listView_busca_origem);
        resultados = new ArrayList<String>();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_search) {
        //}

        //PARA PODER VOLTAR A ACTIVITY ANTERIOR
        if(id == android.R.id.home){
            finish();

        }

        return true;
    }
}
