package com.example.mhcabral.boatrip.Controllers;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mhcabral on 15/07/15.
 */
public class CustomJsonArrayRequest extends Request<JSONArray> {

    private Listener<JSONArray> response;
    private Map<String, String> params;

    public CustomJsonArrayRequest(int method, String url, Map<String, String> params, Listener<JSONArray> response, ErrorListener listener){
        super(method,url,listener);
        this.params = params;
        this.response = response;

    }

    public CustomJsonArrayRequest(String url, Map<String, String> params, Listener<JSONArray> response, ErrorListener listener){
        super(Method.GET,url,listener);
        this.params = params;
        this.response = response;

    }

    public Map<String, String> getparams() throws AuthFailureError{
        return params;
    }

    public Map<String, String> getHeaders() throws AuthFailureError{
        HashMap<String,String> header = new HashMap<String,String>();
        header.put("apiKey","Essa é a minha API KEY: json array");

        return(header);
    }

    @Override
    protected Response<JSONArray> parseNetworkResponse(NetworkResponse response) {

        try {
            String js = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return (Response.success(new JSONArray(js), HttpHeaderParser.parseCacheHeaders(response)));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void deliverResponse(JSONArray response) {
        this.response.onResponse(response);
    }
}
