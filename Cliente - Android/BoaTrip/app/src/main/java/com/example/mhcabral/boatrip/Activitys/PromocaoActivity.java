package com.example.mhcabral.boatrip.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mhcabral.boatrip.Controllers.Stub2;
import com.example.mhcabral.boatrip.R;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.ArrayList;
import java.util.List;

public class PromocaoActivity extends BaseNavegationDrawerActivity {

    private Toolbar mToolbar;
    private static Drawer navegationDrawerLeft;
    private AccountHeader headerNavegationLeft;
    private ListView listview;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promocao);

        //CUSTOM TOOLBAR
        mToolbar = (Toolbar) findViewById(R.id.tb_promocao);
        mToolbar.setLogo(R.mipmap.ic_boatrip_logo);
        mToolbar.setTitle("BoaTrip");
        mToolbar.setSubtitle("Promoções");
        mToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        mToolbar.setSubtitleTextColor(getResources().getColor(android.R.color.white));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // NAVEGATION DRAWER
        // CABEÇALHO DO DRAWER
        headerNavegationLeft = new AccountHeaderBuilder()
                .withActivity(this)
                .withCompactStyle(false)
                .withSavedInstance(savedInstanceState)
                .withThreeSmallProfileImages(false)
                .withHeaderBackground(R.drawable.background_image_boat)
                .build();

        // CORPO DO DRAWER
        navegationDrawerLeft = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(mToolbar)
                .withDisplayBelowToolbar(true)
                .withActionBarDrawerToggleAnimated(true)
                .withDrawerGravity(Gravity.LEFT)
                .withSavedInstance(savedInstanceState)
                .withSelectedItem(2)
                .withAccountHeader(headerNavegationLeft)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {

                    @Override
                    public boolean onItemClick(AdapterView<?> adapterView, View view, int i, long l, IDrawerItem iDrawerItem) {
                        //Toast.makeText(BuscaActivity.this, "Caso "+i, Toast.LENGTH_SHORT).show();
                        Intent it;
                        switch (i) {
                            case 0:
                                it = new Intent(PromocaoActivity.this, LoginActivity.class);
                                startActivity(it);
                                break;
                            case 2:
                                Toast.makeText(PromocaoActivity.this, "Você está na Promoção", Toast.LENGTH_SHORT).show();
                                break;
                            case 3:
                                it = new Intent(PromocaoActivity.this, BuscaActivity.class);
                                startActivity(it);
                                break;
                            case 4:
                                break;
                        }
                        return false;
                    }
                })
                .build();

        init_NavegationDrawerLeft(navegationDrawerLeft);

        listview = (ListView) findViewById(R.id.listViewPromocao);
        List<String> list = getSetProfileList();
        adapter = new ArrayAdapter(PromocaoActivity.this,R.layout.simple_list_item2,list);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                Log.i("Script", "Position: " + position);
                Intent it;
                if (Stub2.getUser().getId() == -1) {
                    if (Stub2.getUser().getUserProfile().getId() == -1) {

                    } else {

                    }
                } else {
                    it = new Intent(PromocaoActivity.this, LoginActivity.class);
                    startActivity(it);
                }
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_promocao, menu);
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

    public List<String> getSetProfileList(){
        int i,j;
        float valorTotal;
        String item = null;
        List<String> listAux = new ArrayList<String>();
        for(i=0;i< Stub2.getListpromocoes().size();i++){
            for(j=0;j< Stub2.getListpromocoes().get(i).getListPassagem().size();j++) {
                valorTotal = Stub2.getListpromocoes().get(i).getListPassagem().get(j).getValor() - Stub2.getListpromocoes().get(i).getListPassagem().get(j).getValor_desconto();
                Log.i("ScriptSystem","Tipo de Passagem: "+Stub2.getListpromocoes().get(i).getListPassagem().get(j).getTpassagem());
                Log.i("ScriptSystem","Valor total a pagar: "+valorTotal);
                item = "Origem: "+Stub2.getListpromocoes().get(i).getOrigem().getNome()+"\nData da saida: "+Stub2.getListpromocoes().get(i).getData_saida()+"\n"+
                        "Destino: "+Stub2.getListpromocoes().get(i).getDestino().getNome()+"\nData da chegada: "+Stub2.getListpromocoes().get(i).getData_chegada()+"\n"+
                        "Tipo de Passagem: "+Stub2.getListpromocoes().get(i).getListPassagem().get(j).getTpassagem()+"\n"+"Valor: "+String.valueOf(valorTotal)+"\n"+"Barco: "+Stub2.getListpromocoes().get(i).getBarco().getNome();

                listAux.add(item);
            }

        }
        return(listAux);
    }
}
