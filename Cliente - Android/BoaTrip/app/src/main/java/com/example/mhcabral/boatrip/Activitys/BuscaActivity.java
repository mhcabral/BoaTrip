package com.example.mhcabral.boatrip.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mhcabral.boatrip.ModelsClasses.Usuario;
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
    private Drawer navegationDrawerLeft;
    private AccountHeader headerNavegationLeft;
    private ArrayList<String> opcoes;
    private ArrayAdapter adapter;
    private ListView listview;
    private Intent it;
    PopupWindow popupWindow;
    private String result;
    private Usuario user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca);

        user = new Usuario("Novo Usuario","Adicionar Usuario");

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
                .withSelectedItem(1)
                .withAccountHeader(headerNavegationLeft)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {

                    @Override
                    public boolean onItemClick(AdapterView<?> adapterView, View view, int i, long l, IDrawerItem iDrawerItem) {
                        Toast.makeText(BuscaActivity.this, "Caso "+i, Toast.LENGTH_SHORT).show();
                        switch (i) {
                            case 0:
                                break;
                            case 1:
                                break;
                            case 2:
                                break;
                            case 3:
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

        //SETANDO LISTA DO POPUPLIST 1
        Date data = new Date();
        GregorianCalendar dataCal = new GregorianCalendar();
        dataCal.setTime(data);
        int mes = dataCal.get(Calendar.MONTH);
        int ano = dataCal.get(Calendar.YEAR);
        Log.i("Script", "Mês: " + mes);
        Log.i("Script", "Ano: " + ano);
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
            String frase = meses.get(mes)+"/"+String.valueOf(ano);
            popupListitem1.add(frase);
            mes++;
        }

        //SETAR LISTVIEW
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
                        break;
                    case 1:
                        it = new Intent(BuscaActivity.this, BuscaDestinoActivity.class);
                        startActivity(it);
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

    }

    private PopupWindow popupWindowBuilder(final List<String> lista) {

        // initialize a pop up window type
        final PopupWindow popupWindow = new PopupWindow(this);

        // the drop down list is a list view
        ListView popuplistView = new ListView(this);

        // set our adapter and pass our pop up window contents
        ArrayAdapter adapter = new ArrayAdapter(this,R.layout.simple_list_item,lista);
        popuplistView.setAdapter(adapter);

        // set the item click listener
        popuplistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                result = lista.get(position);
                popupWindow.dismiss();
            }
        });

        // some other visual settings
        popupWindow.setFocusable(true);
        popupWindow.setWidth(700);
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.fundo_branco));

        // set the list view as pop up window content
        popupWindow.setContentView(popuplistView);

        return popupWindow;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_busca, menu);
        return true;
    }
}
