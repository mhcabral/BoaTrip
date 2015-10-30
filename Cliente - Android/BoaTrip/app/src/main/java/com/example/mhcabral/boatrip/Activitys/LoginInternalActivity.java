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
import com.android.volley.toolbox.Volley;
import com.example.mhcabral.boatrip.Controllers.CustomJsonObjectRequest;
import com.example.mhcabral.boatrip.Controllers.Stub2;
import com.example.mhcabral.boatrip.ModelsClasses.Profile;
import com.example.mhcabral.boatrip.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginInternalActivity extends BaseInternalActivity {

    private Toolbar mToolbar;
    private RequestQueue rq;
    private Map<String, String> params;
    private EditText email;
    private EditText senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_internal);

        //CUSTOM TOOLBAR
        mToolbar = (Toolbar) findViewById(R.id.tb_login_internal);
        mToolbar.setLogo(R.mipmap.ic_boatrip_logo);
        mToolbar.setTitle("BoaTrip");
        mToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        mToolbar.setSubtitleTextColor(getResources().getColor(android.R.color.white));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rq = Volley.newRequestQueue(LoginInternalActivity.this);
        email = (EditText) findViewById(R.id.editTextEmail_li);
        senha = (EditText) findViewById(R.id.editTextSenha_li);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login_internal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.cadastro_li) {
            Intent it = new Intent(this, CadastroUserActivity.class);
            startActivity(it);
        }

        return super.onOptionsItemSelected(item);
    }

    public void verificarLogin(View view){
        callByJsonObjectRequestLogin(Stub2.getPrefix_url() + "/index.php?r=user/mobile-login");
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
                        Intent it;
                        Log.i("ScriptLogin", "Sucess: " + response);
                        JSONObject subobject = null;
                        JSONObject subobjectProfile = null;
                        try {
                            status = response.getInt("status");
                            subobject = response.getJSONObject("data");
                            subobjectProfile = subobject.getJSONObject("profile");
                            Log.i("ScriptLogin", "Data: " + subobject);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if(status == 0){
                            Toast.makeText(LoginInternalActivity.this, "Usuario não encontrado", Toast.LENGTH_SHORT).show();
                        }
                        else if(status == 1){
                            Stub2.getUser().setEmail(email.getText().toString());
                            Stub2.getUser().setPassword_hash(senha.getText().toString());
                            try {
                                String genero;
                                if(subobjectProfile.getInt("genero_id") == 1){
                                    genero = "Masculino";
                                }
                                else{
                                    genero = "Feminino";
                                }
                                Profile novoProfile = new Profile(subobjectProfile.getInt("id"),subobjectProfile.getString("first_name"),subobjectProfile.getString("last_name"),
                                        subobjectProfile.getString("cpf"),subobjectProfile.getString("rg"),subobjectProfile.getString("endereco"),subobjectProfile.getString("cep"),
                                        subobjectProfile.getString("telefone"),subobjectProfile.getString("birthdate"),genero);
                                Stub2.getUser().setUserProfile(novoProfile);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            Log.i("ScriptLogin", Stub2.getUser().getUserProfile().toString());
                            //it = new Intent(LoginActivity.this,BuscaActivity.class);
                            //startActivity(it);
                            //finish();
                        }
                        else if(status == 2){
                            Stub2.getUser().setEmail(email.getText().toString());
                            Stub2.getUser().setPassword_hash(senha.getText().toString());
                            Profile novoProfile = new Profile(-1);
                            Stub2.getUser().setUserProfile(novoProfile);
                            it = new Intent(LoginInternalActivity.this,CadastroPerfilActivity.class);
                            startActivity(it);
                            finish();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginInternalActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        try {
            request.getParams();
        } catch (AuthFailureError authFailureError) {
            authFailureError.printStackTrace();
        }
        rq.add(request);
    }
}
