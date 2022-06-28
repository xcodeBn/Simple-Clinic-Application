package com.example.hassanbazzounmobileapplicationproject2.fragments;


import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hassanbazzounmobileapplicationproject2.Adapters.AdapterConsultation;
import com.example.hassanbazzounmobileapplicationproject2.Adapters.AdapterPatientAppn;
import com.example.hassanbazzounmobileapplicationproject2.Classes.Appoinments;
import com.example.hassanbazzounmobileapplicationproject2.Classes.Consultation;
import com.example.hassanbazzounmobileapplicationproject2.Classes.sharedInfo;
import com.example.hassanbazzounmobileapplicationproject2.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Patient_ViewConsultations extends Fragment {





    private RecyclerView recycler_cons;
    private ArrayList<Consultation> itemList;
    AdapterConsultation adapter_cons;
    Context context;
    LinearLayoutManager mLayoutManager;
    sharedInfo sharefInfo;
    TextView noAppn;
    private ViewFlipper vf;
    public Patient_ViewConsultations() {

    }

    @Nullable
    @Override


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.patientviewcons, container, false);


        //viewFlipper
        vf = (ViewFlipper) view.findViewById(R.id.viewflipper);
        vf.setDisplayedChild(0);
        vf.setVisibility(View.VISIBLE);
        sharefInfo = new sharedInfo(getActivity().getApplicationContext());
        noAppn = (TextView) view.findViewById(R.id.noConsPatient);
        recycler_cons = (RecyclerView) view.findViewById(R.id.recycler_patientCons);
        itemList = new ArrayList<>();
        adapter_cons = new AdapterConsultation(getActivity().getApplicationContext(), getActivity());
        recycler_cons.setAdapter(adapter_cons);
        mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recycler_cons.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));


        try {
            getPatientCons();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return view;
    }


    public void getPatientCons() throws JSONException {


        final String url = getResources().getString(R.string.api)+"consultation.php?name="+sharefInfo.getUser();
        final RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());

        final StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    vf.setVisibility(View.GONE);
                    JSONObject jsonObject = new JSONObject(response);
                    System.out.println("Response " + response);


                    String error = jsonObject.getString("error");
                    String messages = jsonObject.getString("messages");

                    JSONArray jsonArray = new JSONArray(messages);

                    if(jsonArray.length() ==0 ){

                        noAppn.setVisibility(View.VISIBLE);

                    }else {

                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);


                            String date = jsonObject1.getString("date");
                            String payment_value = jsonObject1.getString("payment_value");


                            Consultation consultation = new Consultation(date, payment_value);
                            itemList.add(consultation);
                        }
                        adapter_cons.addAll(itemList);
                    }

                } catch (JSONException e) {
                    System.out.println("JSONException " + e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("VolleyError " + error.getMessage());
            }
        });

// add it to the RequestQueue

        queue.add(stringRequest.setRetryPolicy(new DefaultRetryPolicy(10000, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)));

    }

}