package com.example.mhcabral.boatrip.Activitys;

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
import com.example.mhcabral.boatrip.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class CadastroUserActivity extends BaseInternalActivity {

    private Toolbar mToolbar;
    private RequestQueue rq;
    private Map<String, String> params;
    private EditText email;
    private EditText senha;
    private EditText senha2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_user);

        //CUSTOM TOOLBAR
        mToolbar = (Toolbar) findViewById(R.id.tb_cadastro_user);
        mToolbar.setLogo(R.mipmap.ic_boatrip_logo);
        mToolbar.setTitle("BoaTrip");
        mToolbar.setSubtitle("Novo Usuario");
        mToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        mToolbar.setSubtitleTextColor(getResources().getColor(android.R.color.white));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rq = Volley.newRequestQueue(CadastroUserActivity.this);
        email = (EditText) findViewById(R.id.editTextEmailCad);
        senha = (EditText) findViewById(R.id.editTextSenhaCad);
        senha2 = (EditText) findViewById(R.id.editTextSenha2Cad);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cadastro_user, menu);
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

    public void cadastrarUsuario(View view){
        if(senha.getText().toString().equals(senha2.getText().toString())) {
            callByJsonObjectRequestCadastroUser(Stub2.getPrefix_url() + "/index.php?r=user/mobile-create");
        }
        else{
            Toast.makeText(this, "Senha e sua confirmação diferentes", Toast.LENGTH_SHORT).show();
        }
    }

    public void callByJsonObjectRequestCadastroUser(String url){

        params = new HashMap<String, String>();
        params.put("username",email.getText().toString());
        params.put("password",senha.getText().toString());

        CustomJsonObjectRequest request = new CustomJsonObjectRequest(Request.Method.POST,
                url,
                params,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {
                        int status = -1;
                        Log.i("ScriptCadastro", "Sucess: " + response);
                        try {
                            status = response.getInt("status");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if(status == 0){
                            Toast.makeText(CadastroUserActivity.this, "Usuario já cadastrado", Toast.LENGTH_SHORT).show();
                        }
                        else if(status == 1){
                            Toast.makeText(CadastroUserActivity.this, "Usuario cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        else if(status == 2){
                            Toast.makeText(CadastroUserActivity.this, "Email ou senha fora do padrão", Toast.LENGTH_SHORT).show();
                            Toast.makeText(CadastroUserActivity.this, "Email deve existir", Toast.LENGTH_SHORT).show();
                            Toast.makeText(CadastroUserActivity.this, "Senha deve ter pelo menos 6 digitos", Toast.LENGTH_SHORT).show();
                        }
                        else if(status == 3){
                            Toast.makeText(CadastroUserActivity.this, "Todos os campos devem ser preenchidos", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CadastroUserActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
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
