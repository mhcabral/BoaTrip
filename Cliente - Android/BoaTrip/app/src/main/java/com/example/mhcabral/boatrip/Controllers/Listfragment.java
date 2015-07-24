package com.example.mhcabral.boatrip.Controllers;

/**
 * Created by mhcabral on 17/07/15.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.mhcabral.boatrip.Activitys.BuscaActivity;
import com.example.mhcabral.boatrip.Activitys.BuscaDestinoActivity;
import com.example.mhcabral.boatrip.Activitys.BuscaOrigemActivity;
import com.example.mhcabral.boatrip.Activitys.BuscaResultActivity;
import com.example.mhcabral.boatrip.ModelsClasses.Barco;
import com.example.mhcabral.boatrip.ModelsClasses.Viagem;
import com.example.mhcabral.boatrip.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


public class Listfragment extends Fragment implements RecyclerViewOnClickListenerHack {

    private RecyclerView mRecyclerView;
    private List<String> mList;
    private PopupWindow popupWindow;
    private static int lastposition;
    private static boolean f1 = false,f2 = false,f3 = false;
    private static RequestQueue rq;


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_list);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager llm = (LinearLayoutManager) mRecyclerView.getLayoutManager();
                ListAdapter adapter = (ListAdapter) mRecyclerView.getAdapter();

                if (mList.size() == llm.findLastCompletelyVisibleItemPosition() + 1) {
                    List<String> listAux = ((BuscaActivity) getActivity()).getSetProfileList();

                    for (int i = 0; i < listAux.size(); i++) {
                        adapter.addListItem(listAux.get(i), mList.size());
                    }
                }
            }
        });


        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);


        mList = ((BuscaActivity) getActivity()).getSetProfileList();
        ListAdapter adapter = new ListAdapter(getActivity(), mList);
        adapter.setRecyclerViewOnClickListenerHack(this);
        mRecyclerView.setAdapter(adapter);


        return view;
    }

    @Override
    public void onClickListener(View view, int position) {
        //Toast.makeText(getActivity(), "Position: "+position, Toast.LENGTH_SHORT).show();
        Log.i("Script", "Position: " + position);
        ListAdapter adapter = (ListAdapter) mRecyclerView.getAdapter();
        //Toast.makeText(BuscaActivity.this, "Caso "+position, Toast.LENGTH_SHORT).show();
        Intent it;
        switch (position) {
            case 0:
                if (lastposition != 0){
                    lastposition = position;
                    it = new Intent(((BuscaActivity) getActivity()), BuscaOrigemActivity.class);
                    startActivity(it);
                    f1 = true;
                }
                else{
                    Toast.makeText(getActivity(), "Refreshing", Toast.LENGTH_SHORT).show();
                    lastposition = -1;
                }
                if(Stub2.getDbusca_origem()!=null) {
                    ((TextView) view).setText(Stub2.getDbusca_origem());
                }
                break;
            case 1:
                if(lastposition != 1) {
                    lastposition = position;
                    it = new Intent(((BuscaActivity) getActivity()), BuscaDestinoActivity.class);
                    startActivity(it);
                    f2 = true;
                }
                else{
                    Toast.makeText(getActivity(), "Refreshing", Toast.LENGTH_SHORT).show();
                    lastposition = -1;
                }
                if(Stub2.getDbusca_destino()!=null) {
                    ((TextView) view).setText(Stub2.getDbusca_destino());
                }
                break;
            case 2:
                if(lastposition != 2) {
                    lastposition = position;
                    popupWindow = BuscaActivity.getPopupWindow();
                    popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
                    f3 = true;
                }
                else{
                    Toast.makeText(getActivity(), "Refreshing", Toast.LENGTH_SHORT).show();
                    lastposition = -1;
                }
                ((TextView) view).setText(Stub2.getMesAno());
                //listview.setAdapter(null);
                //listview.setAdapter(adapter);
                break;
            case 3:
                if((f1 == true)&&(f2 == true)&&(f3 == true)) {
                    Log.i("Script", "IdOrigem: " + Stub2.getIdbusca_origem() + " IdDestino: " + Stub2.getIdbusca_destino() + " Mes e ano: " + Stub2.getMesAno());
                    callByJsonObjectRequestViagem(Stub2.getPrefix_url()+"/index.php?r=viagem/mobile-find&l1=" + Stub2.getIdbusca_origem() + "&l2=" + Stub2.getIdbusca_destino() + "&ma=" + Stub2.getMesAno());
                }
                break;
        }
        //adapter.notifyDataSetChanged();
        //adapter.removeListItem(position);
    }

    public static int getLastposition() {
        return lastposition;
    }

    public static void setLastposition(int lastposition) {
        Listfragment.lastposition = lastposition;
    }

    public void callByJsonObjectRequestViagem(String url) {

        JsonObjectRequest request = new JsonObjectRequest
                (Request.Method.GET, url, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray dataArray = null;
                        Log.i("ScriptViagem", "Response: " + response);
                        int totalItens = -1;
                        try {
                            totalItens = Integer.parseInt(response.getString("totalItems"));
                            Log.i("ScriptViagem","Total Items: "+ response.get("totalItems").toString());
                            //Log.i("Script","Data :" + response.getJSONArray("data"));
                            dataArray = response.getJSONArray("data");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        int i,j, idBarco,idViagem;
                        //long DataSaida, DataChegada;
                        float valor,desconto;
                        Barco barcoBuscado = null;
                        if(totalItens>0) {
                            try {
                                for (i = 0; i < Integer.parseInt(response.get("totalItems").toString()); i++) {
                                    Log.i("ScriptViagem", "Data " + i + ": " + dataArray.getJSONObject(i));
                                    JSONObject subobject = dataArray.getJSONObject(i);
                                    for (j = 0; j < Stub2.getListbarcos().size(); j++) {
                                        idBarco = Integer.parseInt(subobject.getString("barco_id"));
                                        if (idBarco == Stub2.getListbarcos().get(i).getId()) {
                                            barcoBuscado = Stub2.getListbarcos().get(i);
                                        }
                                    }
                                    Log.i("ScriptViagem", "Barco " + i + ": " + barcoBuscado.getNome());
                                    idViagem = Integer.parseInt(subobject.getString("id"));
                                    valor = Float.valueOf(subobject.getString("valor"));
                                    desconto = Float.valueOf(subobject.getString("valor_desconto"));
                                    Viagem novaViagem = new Viagem(idViagem, subobject.getString("data_saida"), subobject.getString("data_chegada"), valor, desconto, subobject.getString("percurso"), Stub2.getOrigemBuscado(), Stub2.getDestinoBuscado(), barcoBuscado);
                                    Stub2.addListviagens(novaViagem);
                                }
                                Intent it = new Intent(getActivity(), BuscaResultActivity.class);
                                startActivity(it);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        else{
                            Toast.makeText(getActivity(),"Nenhuma viagem encontrada", Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("ScriptViagem", "Error: " + error.getMessage());
                        Toast.makeText(getActivity(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        //Toast.makeText(BuscaActivity.this, "Servidor indisponivel", Toast.LENGTH_SHORT).show();
                    }
                });

        request.getBody();
        rq.add(request);
    }

    public static RequestQueue getRq() {
        return rq;
    }

    public static void setRq(RequestQueue rq) {
        Listfragment.rq = rq;
    }
}