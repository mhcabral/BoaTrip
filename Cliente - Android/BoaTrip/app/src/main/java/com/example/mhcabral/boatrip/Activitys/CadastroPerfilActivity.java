package com.example.mhcabral.boatrip.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CadastroPerfilActivity extends BaseInternalActivity {

    private Toolbar mToolbar;
    private RequestQueue rq;
    private Map<String, String> params;

    private List<String> list;
    private TextView textview;
    private EditText edittext1;
    private EditText edittext2;
    private EditText edittext3;
    private EditText edittext4;
    private EditText edittext5;
    private EditText edittext6;
    private EditText edittext7;
    private EditText edittext8;
    private Spinner spinner1;
    private ArrayAdapter adapter;

    private int itemSelected;
    private String itemSelected1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_perfil);

        //CUSTOM TOOLBAR
        mToolbar = (Toolbar) findViewById(R.id.tb_cadastro_perfil);
        mToolbar.setLogo(R.mipmap.ic_boatrip_logo);
        mToolbar.setTitle("BoaTrip");
        mToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        mToolbar.setSubtitleTextColor(getResources().getColor(android.R.color.white));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rq = Volley.newRequestQueue(CadastroPerfilActivity.this);

        textview = (TextView) findViewById(R.id.textViewEmailCadPerfil2);
        textview.setText(Stub2.getUser().getEmail());

        edittext1 = (EditText) findViewById(R.id.editTextNomeCadPerfil2);
        edittext2 = (EditText) findViewById(R.id.editTextSobrenomeCadPerfil2);
        edittext3 = (EditText) findViewById(R.id.editTextCPFCadPerfil);
        edittext4 = (EditText) findViewById(R.id.editTextRGCadPerfil);
        edittext5 = (EditText) findViewById(R.id.editTextEndereçoCadPerfil);
        edittext6 = (EditText) findViewById(R.id.editTextCEPCadPerfil);
        edittext7 = (EditText) findViewById(R.id.editTextTelefoneCadPerfil);
        edittext8 = (EditText) findViewById(R.id.editTextBirthdateCadPerfil);
        spinner1 = (Spinner) findViewById(R.id.spinner);

        list = getSetProfileList();
        adapter = new ArrayAdapter(this,R.layout.simple_list_item,list);
        spinner1.setAdapter(adapter);

        itemSelected = Integer.parseInt(list.get(0));
        itemSelected1 = list.get(0);

        spinner1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                itemSelected = position;
                itemSelected1 = list.get(position);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cadastro_perfil, menu);
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
        listAux.add("Masculino");
        listAux.add("Feminino");
        return(listAux);
    }

    public void cadastrarUsuario(View view){
        callByJsonObjectRequestCadastroPerfil(Stub2.getPrefix_url() + "/index.php?r=user/mobile-profile");
    }

    public void callByJsonObjectRequestCadastroPerfil(String url){

        params = new HashMap<String, String>();
        params.put("username", textview.getText().toString());
        params.put("first_name", edittext1.getText().toString());
        params.put("last_name",edittext2.getText().toString());
        params.put("cpf",edittext3.getText().toString());
        params.put("rg",edittext4.getText().toString());
        params.put("endereco",edittext5.getText().toString());
        params.put("cep",edittext6.getText().toString());
        params.put("telefone",edittext7.getText().toString());
        params.put("birthdate",edittext8.getText().toString());
        if(itemSelected == 0){
            itemSelected1 = list.get(0);
            params.put("genero", itemSelected1);
        }
        else{
            itemSelected1 = list.get(1);
            params.put("genero",itemSelected1);
        }

        CustomJsonObjectRequest request = new CustomJsonObjectRequest(Request.Method.POST,
                url,
                params,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {
                        int status = -1;
                        Intent it;
                        Log.i("ScriptCadastro", "Sucess: " + response);
                        try {
                            status = response.getInt("status");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if(status == 0){
                            Toast.makeText(CadastroPerfilActivity.this, "Perfil já cadastrado", Toast.LENGTH_SHORT).show();
                        }
                        else if(status == 1){
                            Toast.makeText(CadastroPerfilActivity.this, "Perfil cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                            Profile novoProfile = new Profile(100,edittext1.getText().toString(),edittext2.getText().toString(),edittext3.getText().toString(),edittext4.getText().toString(),
                                    edittext5.getText().toString(),edittext6.getText().toString(),edittext7.getText().toString(),edittext8.getText().toString(),itemSelected1);
                            //it = new Intent(CadastroPerfilActivity.this,);
                            //startActivity(it);
                            //finish();
                        }
                        else if(status == 2){
                            Toast.makeText(CadastroPerfilActivity.this, "Dados preenchidos incorretamente", Toast.LENGTH_SHORT).show();
                        }
                        else if(status == 3){
                            Toast.makeText(CadastroPerfilActivity.this, "Todos os campos devem ser preenchidos", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CadastroPerfilActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
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
