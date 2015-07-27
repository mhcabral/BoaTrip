package com.example.mhcabral.boatrip.Activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mhcabral.boatrip.Controllers.Stub2;
import com.example.mhcabral.boatrip.ModelsClasses.Barco;
import com.example.mhcabral.boatrip.ModelsClasses.Genero;
import com.example.mhcabral.boatrip.ModelsClasses.Localidade;
import com.example.mhcabral.boatrip.ModelsClasses.PassagemTipo;
import com.example.mhcabral.boatrip.ModelsClasses.Uf;
import com.example.mhcabral.boatrip.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InicialActivity extends ActionBarActivity {

    private Toolbar mToolbar;
    private TextView textview;
    private ProgressBar pb;
    private RequestQueue rq;
    private Map<String, String> params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);

        rq = Volley.newRequestQueue(InicialActivity.this);
        textview = (TextView) findViewById(R.id.textView_inicial);
        pb = (ProgressBar) findViewById(R.id.progressBar_inicial);

        init_stubs(this);

        mToolbar = (Toolbar) findViewById(R.id.tb_inicial);
        mToolbar.setLogo(R.mipmap.ic_boatrip_logo);
        mToolbar.setTitle("BoaTrip");
        mToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        mToolbar.setSubtitleTextColor(getResources().getColor(android.R.color.white));
        setSupportActionBar(mToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_inicial, menu);
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

    protected void init_stubs(Context context){
        //CRIANDO UM STUB
        pb.setMax(1000);
        pb.setProgress(0);
        Stub2.initInstance(context);
        int i;
        for(i=0;i<400;i++){
            pb.setProgress(i);
        }
        //Stub2.setPrefix_url();
        callByJsonObjectRequestLocalidade(Stub2.getPrefix_url() + "/index.php?r=localidade/mobile-index");
        callByJsonObjectRequestTipoPassagem(Stub2.getPrefix_url() + "/index.php?r=passagem-tipo/mobile-index");
        callByJsonObjectRequestGenero(Stub2.getPrefix_url() + "/index.php?r=genero/mobile-index");
        callByJsonObjectRequestBarco(Stub2.getPrefix_url() + "/index.php?r=barco/mobile-index");
    }

    public void callByJsonObjectRequestLocalidade(String url) {

        JsonObjectRequest request = new JsonObjectRequest
                (Request.Method.GET, url, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray dataArray = null;
                        Log.i("ScriptLocalidade", "Response: " + response);
                        try {
                            //Log.i("ScriptLocalidade","Total Items: "+ response.get("totalItems").toString());
                            //Log.i("ScriptLocalidade","Data :" + response.getJSONArray("data"));
                            dataArray = response.getJSONArray("data");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        int i;
                        try {
                            List<Localidade> novaListLocalidade = new ArrayList<Localidade>();
                            for(i=0;i<response.getInt("totalItems");i++){
                                //Log.i("ScriptLocalidade","Data "+i+": "+dataArray.getJSONObject(i));
                                JSONObject subobject = dataArray.getJSONObject(i);
                                JSONObject subobjectUf = subobject.getJSONObject("uf");
                                //Log.i("ScriptUf","Data UF: "+subobjectUf);
                                Uf novaUf = new Uf(Integer.parseInt(subobjectUf.get("id").toString()),subobjectUf.get("uf_nome").toString());
                                //Log.i("ScriptUf","Uf-id: "+novaUf.getId()+" Uf-nome: "+novaUf.getNome());
                                Localidade novaLocalidade = new Localidade(Integer.parseInt(subobject.get("id").toString()),subobject.get("localidade_nome").toString(),novaUf);
                                //Log.i("ScriptLocalidade","Localidade-id: "+novaLocalidade.getId()+" Localidade-nome: "+novaLocalidade.getNome());
                                novaListLocalidade.add(novaLocalidade);
                            }
                            Stub2.setListlocalidade(novaListLocalidade);
                            if(Stub2.getListlocalidade().size() == response.getInt("totalItems")) {
                                Log.i("ScriptLocalidade", "Peguei as localidades");
                                pb.setProgress(500);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(InicialActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        textview.setText("Não é possivel carregar dados do servidor. Por favor tente mais tarde");
                        Toast.makeText(InicialActivity.this, "O aplicativo será encerrado", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });

        request.setTag("tag");
        rq.add(request);
    }

    public void callByJsonObjectRequestBarco(String url) {

        JsonObjectRequest request = new JsonObjectRequest
                (Request.Method.GET, url, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray dataArray = null;
                        Log.i("ScriptBarco","Response: " + response);
                        try {
                            //Log.i("ScriptBarco","Total Items: "+ response.get("totalItems").toString());
                            //Log.i("ScriptBarco","Data :" + response.getJSONArray("data"));
                            dataArray = response.getJSONArray("data");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        int i;
                        try {
                            List<Barco> novaListBarco = new ArrayList<Barco>();
                            for(i = 0; i < response.getInt("totalItems");i++){
                                //Log.i("ScriptBarco","Data "+i+": "+dataArray.getJSONObject(i));
                                JSONObject subobject = dataArray.getJSONObject(i);
                                Barco novoBarco = new Barco(subobject.getInt("id"),subobject.getString("nome"),subobject.getString("descricao"),Integer.parseInt(subobject.getString("tripulantes")));
                                novaListBarco.add(novoBarco);
                            }
                            Stub2.setListbarcos(novaListBarco);
                            if(Stub2.getListbarcos().size() == response.getInt("totalItems")) {
                                Log.i("ScriptBarco", "Peguei os barcos");
                                pb.setProgress(1000);
                                Intent it = new Intent(InicialActivity.this, BuscaActivity.class);
                                startActivity(it);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(InicialActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        textview.setText("Não é possivel carregar dados do servidor. Por favor tente mais tarde");
                        Toast.makeText(InicialActivity.this, "O aplicativo será encerrado", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });

        request.setTag("tag");
        rq.add(request);
    }

    public void callByJsonObjectRequestTipoPassagem(String url) {

        JsonObjectRequest request = new JsonObjectRequest
                (Request.Method.GET, url, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray dataArray = null;
                        Log.i("ScriptTipoPassagem","Response: " + response);
                        try {
                            //Log.i("ScriptTipoPassagem","Total Items: "+ response.get("totalItems").toString());
                            //Log.i("ScriptTipoPassagem","Data :" + response.getJSONArray("data"));
                            dataArray = response.getJSONArray("data");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        int i;
                        try {
                            List<PassagemTipo> novaListPassagemTipo = new ArrayList<PassagemTipo>();
                            for(i = 0; i < response.getInt("totalItems");i++){
                                //Log.i("ScriptTipoPassagem","Data "+i+": "+dataArray.getJSONObject(i));
                                JSONObject subobject = dataArray.getJSONObject(i);
                                PassagemTipo novaPassagemTipo = new PassagemTipo(subobject.getInt("id"),subobject.getString("passagem_tipo_nome"));
                                novaListPassagemTipo.add(novaPassagemTipo);
                            }
                            Stub2.setListpassagemtipo(novaListPassagemTipo);
                            if(Stub2.getListpassagemtipo().size() == response.getInt("totalItems")) {
                                Log.i("ScriptTipoPassagem", "Peguei os tipos de passagens");
                                pb.setProgress(700);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(InicialActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        textview.setText("Não é possivel carregar dados do servidor. Por favor tente mais tarde");
                        Toast.makeText(InicialActivity.this, "O aplicativo será encerrado", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });

        request.setTag("tag");
        rq.add(request);
    }

    public void callByJsonObjectRequestGenero(String url) {

        JsonObjectRequest request = new JsonObjectRequest
                (Request.Method.GET, url, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray dataArray = null;
                        Log.i("ScriptGenero","Response: " + response);
                        try {
                            //Log.i("ScriptGenero","Total Items: "+ response.get("totalItems").toString());
                            //Log.i("ScriptGenero","Data :" + response.getJSONArray("data"));
                            dataArray = response.getJSONArray("data");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        int i;
                        try {
                            List<Genero> novaListGenero = new ArrayList<Genero>();
                            for(i = 0; i < response.getInt("totalItems");i++){
                                //Log.i("ScriptGenero","Data "+i+": "+dataArray.getJSONObject(i));
                                JSONObject subobject = dataArray.getJSONObject(i);
                                Genero novoGenero = new Genero(subobject.getInt("id"),subobject.getString("genero_nome"));
                                novaListGenero.add(novoGenero);
                            }
                            Stub2.setListgenero(novaListGenero);
                            if(Stub2.getListgenero().size() == response.getInt("totalItems")) {
                                Log.i("ScriptGenero", "Peguei todos os generos");
                                pb.setProgress(800);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(InicialActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        textview.setText("Não é possivel carregar dados do servidor. Por favor tente mais tarde");
                        Toast.makeText(InicialActivity.this, "O aplicativo será encerrado", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });

        request.setTag("tag");
        rq.add(request);
    }


    @Override
    public void onStop(){
        super.onStop();

        //rq.cancelAll("tag");
    }
}
