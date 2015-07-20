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
import com.example.mhcabral.boatrip.ModelsClasses.Localidade;
import com.example.mhcabral.boatrip.ModelsClasses.Uf;
import com.example.mhcabral.boatrip.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
        pb.setMax(100);
        pb.setProgress(0);
        Stub2.initInstance(context);
        callByJsonObjectRequestLocalidade("http://boatrip.microben.com.br/index.php?r=localidade/mobile-index");
        callByJsonObjectRequestBarco("http://boatrip.microben.com.br/index.php?r=barco/mobile-index");
        int i;
        for(i=0;i<100;i++) {
            //Log.i("SProgress","Progress: "+i);
            pb.setProgress(i);
        }
        Intent it = new Intent(this, BuscaActivity.class);
        startActivity(it);




        /*
        Uf novaUf = new Uf(0,"Amazonas");
        Localidade novaLocalidade1 = new Localidade(0,"Manaus",novaUf);
        Stub2.addListlocalidade(novaLocalidade1);
        Localidade novaLocalidade2 = new Localidade(1,"Tefe",novaUf);
        Stub2.addListlocalidade(novaLocalidade2);
        Localidade novaLocalidade3 = new Localidade(2,"Coari",novaUf);
        Stub2.addListlocalidade(novaLocalidade3);
        */
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
                            for(i=0;i<Integer.parseInt(response.get("totalItems").toString());i++){
                                //Log.i("ScriptLocalidade","Data "+i+": "+dataArray.getJSONObject(i));
                                JSONObject subobject = dataArray.getJSONObject(i);
                                JSONObject subobjectUf = subobject.getJSONObject("uf");
                                //Log.i("ScriptUf","Data UF: "+subobjectUf);
                                Uf novaUf = new Uf(Integer.parseInt(subobjectUf.get("id").toString()),subobjectUf.get("uf_nome").toString());
                                //Log.i("ScriptUf","Uf-id: "+novaUf.getId()+" Uf-nome: "+novaUf.getNome());
                                Localidade novaLocalidade = new Localidade(Integer.parseInt(subobject.get("id").toString()),subobject.get("localidade_nome").toString(),novaUf);
                                //Log.i("ScriptLocalidade","Localidade-id: "+novaLocalidade.getId()+" Localidade-nome: "+novaLocalidade.getNome());
                                Stub2.addListlocalidade(novaLocalidade);
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

        request.getBody();
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
                            for(i = 0; i < Integer.parseInt(response.get("totalItems").toString());i++){
                                //Log.i("ScriptBarco","Data "+i+": "+dataArray.getJSONObject(i));
                                JSONObject subobject = dataArray.getJSONObject(i);
                                Barco novoBarco = new Barco(Integer.parseInt(subobject.getString("id")),subobject.getString("nome"),subobject.getString("descricao"),Integer.parseInt(subobject.getString("tripulantes")));
                                Stub2.addListbarcos(novoBarco);
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

        request.getBody();
        rq.add(request);
    }

    /*

    public void callByJsonObjectRequest(String url){

        params = new HashMap<String, String>();
        params.put("method", "web-data-jor");

        CustomJsonObjectRequest request = new CustomJsonObjectRequest(Request.Method.POST,
                url,
                params,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("Script", "Sucess: " + response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(InicialActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        textview.setText("Não é possivel carregar dados do servidor. Por favor tente mais tarde");
                        //Toast.makeText(InicialActivity.this, "O aplicativo será encerrado", Toast.LENGTH_SHORT).show();
                        //finish();
                    }
        });

        request.setTag("tag");
        rq.add(request);
    }

    public void callByJsonArrayRequest(String url){

        params = new HashMap<String, String>();
        params.put("method", "web-data-jar");

        CustomJsonArrayRequest request = new CustomJsonArrayRequest(Request.Method.POST,
                url,
                params,
                new Response.Listener<JSONArray>(){
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.i("Script", "Sucess: " + response);
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(InicialActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        textview.setText("Não é possivel carregar dados do servidor. Por favor tente mais tarde");
                        //Toast.makeText(InicialActivity.this,"O aplicativo será encerrado",Toast.LENGTH_SHORT).show();
                        //finish();
                    }
                });
        request.setTag("tag");
        rq.add(request);
    }

    public void callByStringRequest(String url) {

        StringRequest request = new StringRequest(Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("Script", "Sucess: " + response);
                    }
                },
                new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(InicialActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        textview.setText("Não é possivel carregar dados do servidor. Por favor tente mais tarde");
                        //Toast.makeText(InicialActivity.this,"O aplicativo será encerrado",Toast.LENGTH_SHORT).show();
                        //finish();
                    }
                }){
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                //params.put("username", username);
                //params.put("password", password);
                //params.put("grant_type", "password");
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                //HashMap<String,String> header = new HashMap<String,String>();
                //header.put("apiKey","Essa é a minha API KEY: fuashfsufhasu");
                //return(header);

                return params;
            }

            @Override
            public Priority getPriority(){
                return(Priority.NORMAL);
            }


        };

        request.setTag("tag");
        rq.add(request);
    }
*/


    @Override
    public void onStop(){
        super.onStop();

        //rq.cancelAll("tag");
    }
}
