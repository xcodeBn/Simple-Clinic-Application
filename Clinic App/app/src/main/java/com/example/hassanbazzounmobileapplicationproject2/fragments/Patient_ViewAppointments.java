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

import com.example.hassanbazzounmobileapplicationproject2.Adapters.AdapterPatientAppn;
import com.example.hassanbazzounmobileapplicationproject2.Classes.Appoinments;
import com.example.hassanbazzounmobileapplicationproject2.Classes.sharedInfo;
import com.example.hassanbazzounmobileapplicationproject2.R;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Patient_ViewAppointments extends Fragment {


    //Recycler
    private RecyclerView recycler_appn;
    private ArrayList<Appoinments> itemList;
    AdapterPatientAppn adapter_appn;
    Context context;
    LinearLayoutManager mLayoutManager;
   sharedInfo sharedInfo;
    TextView noAppn;
    private ViewFlipper vf;
    public Patient_ViewAppointments() {

    }



    @Override


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.patientviewappn, container, false);


        vf = (ViewFlipper) view.findViewById(R.id.viewflipper);
        vf.setDisplayedChild(0);
        vf.setVisibility(View.VISIBLE);
        sharedInfo = new sharedInfo(getActivity().getApplicationContext());
        recycler_appn = (RecyclerView) view.findViewById(R.id.recycler_patientAppn);
        itemList = new ArrayList<>();
        adapter_appn = new AdapterPatientAppn(getActivity().getApplicationContext(), getActivity());
        recycler_appn.setAdapter(adapter_appn);
        mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recycler_appn.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        noAppn = (TextView) view.findViewById(R.id.noAppnPatient);

        try {

            getPatientAppn();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return view;
    }

    public void getPatientAppn() throws JSONException {


        final String url = getResources().getString(R.string.api)+"appoinments.php?name="+sharedInfo.getUser();
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

                    System.out.println("kkkk "+error);
                    JSONArray jsonArray = new JSONArray(messages);

                    if(jsonArray.length() ==0 ){

                        noAppn.setVisibility(View.VISIBLE);

                    }else {

                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);


                            String date = jsonObject1.getString("date");
                            String symptomsDescription = jsonObject1.getString("symptomsDescription");


                            Appoinments appoinments = new Appoinments(date, symptomsDescription);
                            itemList.add(appoinments);
                        }
                        adapter_appn.addAll(itemList);
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