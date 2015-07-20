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

import com.example.mhcabral.boatrip.Activitys.BuscaActivity;
import com.example.mhcabral.boatrip.Activitys.BuscaDestinoActivity;
import com.example.mhcabral.boatrip.Activitys.BuscaOrigemActivity;
import com.example.mhcabral.boatrip.R;

import java.util.List;


public class Listfragment extends Fragment implements RecyclerViewOnClickListenerHack {

    private RecyclerView mRecyclerView;
    private List<String> mList;
    private PopupWindow popupWindow;
    private static int lastposition;


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
                Log.i("Script","IdOrigem: "+Stub2.getIdbusca_origem()+" IdDestino: "+Stub2.getIdbusca_destino()+" Mes e ano: "+Stub2.getMesAno());
                BuscaActivity.callByJsonObjectRequestViagem("http://boatrip.microben.com.br/index.php?r=viagem/mobile-find&l1="+Stub2.getIdbusca_origem()+"&l2="+Stub2.getIdbusca_destino()+"&ma="+Stub2.getMesAno());
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
}