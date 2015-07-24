package com.example.mhcabral.boatrip.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.android.volley.toolbox.Volley;
import com.example.mhcabral.boatrip.Controllers.Listfragment;
import com.example.mhcabral.boatrip.Controllers.Stub2;
import com.example.mhcabral.boatrip.R;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class BuscaActivity extends BaseNavegationDrawerActivity {

    private Toolbar mToolbar;
    private static Drawer navegationDrawerLeft;
    private AccountHeader headerNavegationLeft;
    //private ArrayList<String> opcoes;
    //private ArrayAdapter adapter;
    //private ListView listview;
    //private Intent it;
    private static PopupWindow popupWindow;
    //private String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca);

        //CUSTOM TOOLBAR
        mToolbar = (Toolbar) findViewById(R.id.tb_busca);
        mToolbar.setLogo(R.mipmap.ic_boatrip_logo);
        mToolbar.setTitle("BoaTrip");
        mToolbar.setSubtitle("Busca");
        mToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        mToolbar.setSubtitleTextColor(getResources().getColor(android.R.color.white));
        setSupportActionBar(mToolbar);


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
                .withSelectedItem(3)
                .withAccountHeader(headerNavegationLeft)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {

                    @Override
                    public boolean onItemClick(AdapterView<?> adapterView, View view, int i, long l, IDrawerItem iDrawerItem) {
                        //Toast.makeText(BuscaActivity.this, "Caso "+i, Toast.LENGTH_SHORT).show();
                        switch (i) {
                            case 0:
                                Intent it = new Intent(BuscaActivity.this, LoginActivity.class);
                                startActivity(it);
                                break;
                            case 1:
                                break;
                            case 2:
                                break;
                            case 3:
                                Toast.makeText(BuscaActivity.this,"Você está na Busca",Toast.LENGTH_SHORT).show();
                                break;
                            case 4:
                                break;
                        }
                        return false;
                    }
                })
                .withOnDrawerItemLongClickListener(new Drawer.OnDrawerItemLongClickListener() {

                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l, IDrawerItem iDrawerItem) {
                        Toast.makeText(BuscaActivity.this, "onItemLongClick", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                })
                .build();

        init_NavegationDrawerLeft(navegationDrawerLeft);

        List<String> popuplist = createPopupListItem();
        popupWindow = popupWindowBuilder(popuplist);

        //SETAR LISTVIEW
        /*
        opcoes = new ArrayList<>();
        opcoes.add("De onde vou sair");
        opcoes.add("Para onde vou");
        opcoes.add("Selecione o mês");
        opcoes.add("Buscar");

        adapter = new ArrayAdapter(this,R.layout.simple_list_item,opcoes);

        listview = (ListView) findViewById(R.id.listViewBusca);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                Log.i("Script", "Position: " + position);
                //Toast.makeText(BuscaActivity.this, "Caso "+position, Toast.LENGTH_SHORT).show();
                switch (position) {
                    case 0:
                        it = new Intent(BuscaActivity.this, BuscaOrigemActivity.class);
                        startActivity(it);
                        if(Stub2.getDbusca_origem()!=null) {
                            ((TextView) view).setText(Stub2.getDbusca_origem());
                        }
                        break;
                    case 1:
                        it = new Intent(BuscaActivity.this, BuscaDestinoActivity.class);
                        startActivity(it);
                        if(Stub2.getDbusca_destino()!=null) {
                            ((TextView) view).setText(Stub2.getDbusca_destino());
                        }
                        break;
                    case 2:
                        popupWindow = popupWindowBuilder(popupListitem1);
                        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
                        ((TextView) view).setText(result);
                        //listview.setAdapter(null);
                        //listview.setAdapter(adapter);
                        break;
                    case 3:
                        break;
                }

            }

        });
        */

        //SETANDO UMA LISTA DE FRAGMENTS
        Listfragment frag = (Listfragment) getSupportFragmentManager().findFragmentByTag("mainFrag");
        if(frag == null) {
            frag = new Listfragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.rl_fragment_container, frag, "mainFrag");
            ft.commit();
        }
        frag.setLastposition(-1);

        frag.setRq(Volley.newRequestQueue(BuscaActivity.this));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_busca, menu);
        return true;
    }

    public List<String> getSetProfileList(){
        List<String> listAux = new ArrayList<String>();
        listAux.add("De onde vou sair");
        listAux.add("Para onde vou");
        listAux.add("Selecione o mês");
        listAux.add("Buscar");
        return(listAux);
    }

    public PopupWindow popupWindowBuilder(final List<String> lista) {

        // initialize a pop up window type
        final PopupWindow popupWindow = new PopupWindow(this);

        // the drop down list is a list view
        ListView popuplistView = new ListView(this);

        // set our adapter and pass our pop up window contents
        ArrayAdapter adapter2 = new ArrayAdapter(this, R.layout.simple_list_item,lista);
        popuplistView.setAdapter(adapter2);

        // set the item click listener
        popuplistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Stub2.setMesAno(lista.get(position));
                popupWindow.dismiss();
            }
        });

        // some other visual settings
        popupWindow.setFocusable(true);
        popupWindow.setWidth(800);
        popupWindow.setHeight(800);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.fundo_branco));

        // set the list view as pop up window content
        popupWindow.setContentView(popuplistView);

        return popupWindow;
    }

    public static List<String> createPopupListItem(){
        //SETANDO LISTA DO POPUPLIST 1
        Date data = new Date();
        GregorianCalendar dataCal = new GregorianCalendar();
        dataCal.setTime(data);
        int mes = dataCal.get(Calendar.MONTH);
        int ano = dataCal.get(Calendar.YEAR);
        //Log.i("Script", "Mês: " + mes);
        //Log.i("Script", "Ano: " + ano);
        List<String> meses = new ArrayList<String>();
        meses.add("Janeiro");
        meses.add("Fevereiro");
        meses.add("Março");
        meses.add("Abril");
        meses.add("Maio");
        meses.add("Junho");
        meses.add("Julho");
        meses.add("Agosto");
        meses.add("Setembro");
        meses.add("Outubro");
        meses.add("Novembro");
        meses.add("Dezembro");

        final List<String> popupListitem1 = new ArrayList<String>();
        int i;
        for(i=0;i<12;i++) {
            if(mes > 11){
                mes = 0;
                ano++;
            }
            String frase = meses.get(mes)+String.valueOf(ano);
            popupListitem1.add(frase);
            mes++;
        }
        return  popupListitem1;
    }

    public static PopupWindow getPopupWindow() {
        return popupWindow;
    }

    public static void setPopupWindow(PopupWindow popupWindow) {
        BuscaActivity.popupWindow = popupWindow;
    }

    public static Drawer getNavegationDrawerLeft() {
        return navegationDrawerLeft;
    }

    public static void setNavegationDrawerLeft(Drawer navegationDrawerLeft) {
        BuscaActivity.navegationDrawerLeft = navegationDrawerLeft;
    }
}
