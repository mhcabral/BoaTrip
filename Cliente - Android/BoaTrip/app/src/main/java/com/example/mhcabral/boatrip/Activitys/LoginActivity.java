package com.example.mhcabral.boatrip.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mhcabral.boatrip.Controllers.CustomJsonObjectRequest;
import com.example.mhcabral.boatrip.Controllers.Stub2;
import com.example.mhcabral.boatrip.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends BaseInternalActivity {

    private Toolbar mToolbar;
    private RequestQueue rq;
    private Map<String, String> params;
    private EditText email;
    private EditText senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //CUSTOM TOOLBAR
        mToolbar = (Toolbar) findViewById(R.id.tb_login);
        mToolbar.setLogo(R.mipmap.ic_boatrip_logo);
        mToolbar.setTitle("BoaTrip");
        mToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        mToolbar.setSubtitleTextColor(getResources().getColor(android.R.color.white));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rq = Volley.newRequestQueue(LoginActivity.this);
        email = (EditText) findViewById(R.id.editTextEmail);
        senha = (EditText) findViewById(R.id.editTextSenha);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.cadastro) {
            Intent it = new Intent(this, CadastroUserActivity.class);
            startActivity(it);
        }

        return super.onOptionsItemSelected(item);
    }

    public void verificarLogin(View view){
        callByJsonObjectRequestLogin(Stub2.getPrefix_url()+"/index.php?r=user/mobile-login");
        //callByJsonObjectRequestLogin2(Stub2.getPrefix_url()+"/index.php?r=user/mobile-login");
    }

    public void callByJsonObjectRequestLogin(String url){

        params = new HashMap<String, String>();
        params.put("username", email.getText().toString());
        params.put("password",senha.getText().toString());

        CustomJsonObjectRequest request = new CustomJsonObjectRequest(Request.Method.POST,
                url,
                params,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {
                        int status = -1;
                        Log.i("ScriptLogin", "Sucess: " + response);
                        try {
                            status = response.getInt("status");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if(status == 0){
                            Toast.makeText(LoginActivity.this, "Usuario não encontrado", Toast.LENGTH_SHORT).show();
                        }
                        else if(status == 1){
                            Stub2.getUser().setEmail(email.getText().toString());
                            Stub2.getUser().setPassword_hash(senha.getText().toString());
                            //Profile novoProfile = new Profile();
                            Stub2.setProfile_ok(true);
                            finish();
                        }
                        else if(status == 2){
                            Stub2.getUser().setEmail(email.getText().toString());
                            Stub2.getUser().setPassword_hash(senha.getText().toString());
                            finish();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        try {
            request.getParams();
        } catch (AuthFailureError authFailureError) {
            authFailureError.printStackTrace();
        }
        rq.add(request);
    }

    public void callByJsonObjectRequestLogin2(String url) {
        params = new HashMap<String, String>();
        params.put("username", email.getText().toString());
        params.put("password", senha.getText().toString());
        Log.i("ScriptLogin", "Username: " + email.getText().toString());
        Log.i("ScriptLogin", "Password: " + senha.getText().toString());

        JsonObjectRequest request = new JsonObjectRequest
                (Request.Method.POST, url,new JSONObject(params),  new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {
                        int status = -1;
                        Log.i("ScriptLogin", "Sucess: " + response);
                        try {
                            status = response.getInt("status");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if(status == 0){
                            Toast.makeText(LoginActivity.this, "Usuario não encontrado", Toast.LENGTH_SHORT).show();
                        }
                        else if(status == 1){
                            Stub2.getUser().setEmail(email.getText().toString());
                            Stub2.getUser().setPassword_hash(senha.getText().toString());
                            //Profile novoProfile = new Profile();
                            Stub2.setProfile_ok(true);
                            finish();
                        }
                        else if(status == 2){
                            Stub2.getUser().setEmail(email.getText().toString());
                            Stub2.getUser().setPassword_hash(senha.getText().toString());
                            finish();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                headers.put("username", email.getText().toString());
                headers.put("password", senha.getText().toString());
                return headers;
            }

            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("username", email.getText().toString());
                params.put("password", senha.getText().toString());
                return params;
            }
        };

        request.setTag("tag");
        rq.add(request);
    }
}
