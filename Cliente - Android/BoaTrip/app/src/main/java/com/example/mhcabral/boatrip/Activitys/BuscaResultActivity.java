package com.example.mhcabral.boatrip.Activitys;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.mhcabral.boatrip.Controllers.Stub2;
import com.example.mhcabral.boatrip.R;

import java.util.ArrayList;
import java.util.List;

public class BuscaResultActivity extends BaseInternalActivity {

    private Toolbar mToolbar;
    private ListView listview;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca_result);

        //CUSTOM TOOLBAR
        mToolbar = (Toolbar) findViewById(R.id.tb_busca_result);
        mToolbar.setLogo(R.mipmap.ic_boatrip_logo);
        mToolbar.setTitle("BoaTrip");
        mToolbar.setSubtitle("Resultados da Busca");
        mToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        mToolbar.setSubtitleTextColor(getResources().getColor(android.R.color.white));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        List<String> list = getSetProfileList();
        adapter = new ArrayAdapter(this,R.layout.simple_list_item2,list);
        listview = (ListView) findViewById(R.id.listViewBuscaResult);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                Log.i("Script", "Position: " + position);

            }

        });
    }

    public List<String> getSetProfileList(){
        int i;
        List<String> listAux = new ArrayList<String>();
        for(i=0;i<Stub2.getListviagens().size();i++){
            String item = "Origem: "+Stub2.getListviagens().get(i).getOrigem().getNome()+"\nData da saida: "+Stub2.getListviagens().get(i).getData_saida()+"\n"+
                    "Destino: "+Stub2.getListviagens().get(i).getDestino().getNome()+"\nData da chegada: "+Stub2.getListviagens().get(i).getData_chegada()+"\n"+
                    "Valor: "+Stub2.getListviagens().get(i).getValor()+"\n"+"Barco: "+Stub2.getListviagens().get(i).getBarco().getNome();
            listAux.add(item);
        }
        return(listAux);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_busca_result, menu);
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
