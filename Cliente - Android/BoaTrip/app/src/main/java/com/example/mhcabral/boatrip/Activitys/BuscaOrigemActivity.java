package com.example.mhcabral.boatrip.Activitys;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mhcabral.boatrip.Controllers.Stub2;
import com.example.mhcabral.boatrip.R;

import java.util.ArrayList;
import java.util.List;

public class BuscaOrigemActivity extends BaseInternalActivity {
    private Toolbar mToolbar;

    private ListView listview;
    private ArrayAdapter adapter;
    List<String> resultados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca_origem);



        //CUSTOM TOOLBAR
        mToolbar = (Toolbar) findViewById(R.id.tb_busca_origem);
        mToolbar.setLogo(R.mipmap.ic_boatrip_logo);
        mToolbar.setTitle("BoaTrip");
        mToolbar.setSubtitle("Origem");
        mToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        mToolbar.setSubtitleTextColor(getResources().getColor(android.R.color.white));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listview = (ListView) findViewById(R.id.listView_busca_origem);
        resultados = new ArrayList<String>();

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Stub2.setDbusca_origem(((TextView) view).getText().toString());
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_busca_origem, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint("De onde vou sair");

        SearchView.OnQueryTextListener textChangeListener = new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                //Log.i("Filter", "on query submit: " + query);
                //Toast.makeText(BuscaOrigemActivity.this, query, Toast.LENGTH_SHORT).show();
                boolean found = false;
                resultados.clear();
                listview.setAdapter(null);
                int i;
                for(i=0;i<Stub2.getListlocalidade().size();i++) {
                    if (Stub2.getListlocalidade().get(i).getNome().equalsIgnoreCase(query)) {
                        resultados.add(Stub2.getListlocalidade().get(i).getNome());
                        found = true;
                        break;
                    }
                }
                if(found == false){
                    Toast.makeText(BuscaOrigemActivity.this,"Localidade não encontrada",Toast.LENGTH_SHORT).show();
                }
                adapter = new ArrayAdapter(BuscaOrigemActivity.this,R.layout.simple_list_item,resultados);
                listview.setAdapter(adapter);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Log.i("Filter","on change text: "+newText);
                //Toast.makeText(BuscaOrigemActivity.this, newText, Toast.LENGTH_SHORT).show();
                /*
                resultados.clear();
                listview.setAdapter(null);
                int i;
                for(i=0;i<Stub2.getListlocalidade().size();i++) {
                    if ((Stub2.getListlocalidade().get(i).getNome().contains(newText))||(i==0)) {
                        resultados.add(Stub2.getListlocalidade().get(i).getNome());
                    }
                }
                adapter = new ArrayAdapter(BuscaOrigemActivity.this,R.layout.simple_list_item,resultados);
                listview.setAdapter(adapter);
                */
                return false;

            }

        };
        searchView.setOnQueryTextListener(textChangeListener);
        return super.onCreateOptionsMenu(menu);
    }
}