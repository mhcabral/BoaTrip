package com.example.mhcabral.boatrip.Activitys;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.mhcabral.boatrip.R;

public class BuscaOrigemActivity extends BaseInternalActivity {
    private Toolbar mToolbar;

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
    }
}
