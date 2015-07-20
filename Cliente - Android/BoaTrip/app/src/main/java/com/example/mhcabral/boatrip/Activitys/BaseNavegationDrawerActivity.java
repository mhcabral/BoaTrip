package com.example.mhcabral.boatrip.Activitys;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.mhcabral.boatrip.Controllers.Stub2;
import com.example.mhcabral.boatrip.R;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;


public class BaseNavegationDrawerActivity extends ActionBarActivity {
    private Toolbar mToolbar;
    //private Toolbar mToolbarBottom;
    private Drawer navegationDrawerLeft;
    //private Drawer navegationDrawerRight;
    private AccountHeader headerNavegationLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_navegation);

        //CUSTOM TOOLBAR
        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        mToolbar.setLogo(R.mipmap.ic_boatrip_logo);
        mToolbar.setTitle("BoaTrip");
        mToolbar.setSubtitle("Base Navegation Drawer Activity");
        mToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        mToolbar.setSubtitleTextColor(getResources().getColor(android.R.color.white));
        setSupportActionBar(mToolbar);

        /*getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);*/

        /*mToolbarBottom = (Toolbar) findViewById(R.id.inc_tb_bottom);
        mToolbarBottom.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent it = null;
                startActivity(it);
                return true;
            }
        });
        mToolbarBottom.inflateMenu(R.menu.menu_custom);*/


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
                .withSelectedItem(0)
                .withAccountHeader(headerNavegationLeft)
                //.withActionBarDrawerToggle(false)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {

                    @Override
                    public boolean onItemClick(AdapterView<?> adapterView, View view, int i, long l, IDrawerItem iDrawerItem) {
                        return false;
                    }
                })
                .withOnDrawerItemLongClickListener(new Drawer.OnDrawerItemLongClickListener() {

                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l, IDrawerItem iDrawerItem) {
                        Toast.makeText(BaseNavegationDrawerActivity.this, "onItemLongClick", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                })
                .build();

        init_NavegationDrawerLeft(navegationDrawerLeft);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_base, menu);
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

    public void init_NavegationDrawerLeft(Drawer navegationDrawerLeft){
        navegationDrawerLeft.addItem(new ProfileDrawerItem().withName(Stub2.getUser().getNome()).withEmail(Stub2.getUser().getEmail()).withIcon(Stub2.getUser().getIcon()));
        navegationDrawerLeft.addItem(new DividerDrawerItem());
        navegationDrawerLeft.addItem(new PrimaryDrawerItem().withName("Promoções").withIcon(getResources().getDrawable(R.mipmap.ic_cart_outline)));
        navegationDrawerLeft.addItem(new PrimaryDrawerItem().withName("Buscar Viagem").withIcon(getResources().getDrawable(R.mipmap.ic_ferry)));
        navegationDrawerLeft.addItem(new PrimaryDrawerItem().withName("Minhas Viagens").withIcon(getResources().getDrawable(R.mipmap.ic_emoticon_cool)));

    }
}
