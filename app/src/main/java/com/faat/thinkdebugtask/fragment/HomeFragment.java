package com.faat.thinkdebugtask.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.faat.thinkdebugtask.MainActivity;
import com.faat.thinkdebugtask.R;
import com.faat.thinkdebugtask.adapter.ContactAdapter;
import com.faat.thinkdebugtask.model.ContactModel;
import com.faat.thinkdebugtask.network.HttpsTrustManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeFragment extends Fragment {
    RecyclerView recyclerView;
    ContactAdapter contactAdapter;
    List<ContactModel> list;
    String api = "https://api.androidhive.info/contacts/";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        setRetainInstance(true);
        initView(view);
        return view;
    }
    private void initView(View view) {
        MainActivity.bottomBar.selectTabAt(0, true);
        MainActivity.tittle.setText("Home");
        list = new ArrayList<ContactModel>();
        recyclerView = view.findViewById(R.id.recyclerView);
        contactAdapter = new ContactAdapter(list, getContext());
        recyclerView.setAdapter(contactAdapter);
        getcontect();
    }
    private void getcontect() {
        HttpsTrustManager.allowAllSSL();
        StringRequest request = new StringRequest(Request.Method.GET, api, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject rootObject = new JSONObject(response);
                    if (!rootObject.isNull("contacts")){
                        JSONArray contacts=rootObject.getJSONArray("contacts");
                        if (contacts.length()>0){
                            for (int i = 0; i < contacts.length(); i++) {
                                JSONObject contact=contacts.getJSONObject(i);
                                ContactModel model=new ContactModel();
                                model.setId(contact.getString("id"));
                                model.setName(contact.getString("name"));
                                model.setEmail(contact.getString("email"));
                                model.setAddress(contact.getString("address"));
                                model.setGender(contact.getString("gender"));
                                if (contact.has("phone")){
                                    JSONObject phone=contact.getJSONObject("phone");
                                    model.setMobile(phone.getString("mobile"));
                                    model.setHome(phone.getString("home"));
                                    model.setOffice(phone.getString("office"));
                                }
                                list.add(model);
                            }
                            contactAdapter.notifyDataSetChanged();
                        }
                    }
                } catch (Exception e) {
                    Toast.makeText(getContext(), ""+e.toString(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), ""+error.toString(), Toast.LENGTH_SHORT).show();
                Log.e("tag", "onErrorResponse: " + error);
            }
        }) {

            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<>();
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        request.setRetryPolicy(new DefaultRetryPolicy(
                300000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));
        requestQueue.add(request);
    }
}