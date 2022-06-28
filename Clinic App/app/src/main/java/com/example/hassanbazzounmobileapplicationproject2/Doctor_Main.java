package com.example.hassanbazzounmobileapplicationproject2;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import com.google.android.material.snackbar.Snackbar;
import androidx.core.content.ContextCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hassanbazzounmobileapplicationproject2.Adapters.AdapterDocViewAppn;
import com.example.hassanbazzounmobileapplicationproject2.Classes.DoctorAppn;
import com.example.hassanbazzounmobileapplicationproject2.Classes.sharedInfo;
import com.example.hassanbazzounmobileapplicationproject2.Dialogs.LogOutDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Doctor_Main extends AppCompatActivity {

    //Recycler
    private RecyclerView recycler_docAppn;
    private ArrayList<DoctorAppn> itemList;
    AdapterDocViewAppn adapter_docAppn;
    Context context;
    LinearLayoutManager mLayoutManager;
    sharedInfo sharedInfo;
    Toolbar toolbar;
    ImageView logoutBtn;
    LogOutDialog logOutDialog;
    private ViewFlipper vf;
    TextView noAppn;
    SwipeRefreshLayout swipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor__main);

        toolbar = (Toolbar) findViewById(R.id.mainToolbar);
        setSupportActionBar(toolbar);

        logoutBtn = (ImageView) findViewById(R.id.logoutBtn);
        noAppn = (TextView) findViewById(R.id.noAppn);

        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh_notification);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            getAppoinments();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, 100);
            }
        });


        //viewFlipper
        vf = (ViewFlipper) findViewById(R.id.viewflipper);
        vf.setDisplayedChild(0);
        vf.setVisibility(View.VISIBLE);
        sharedInfo = new sharedInfo(getApplicationContext());
        recycler_docAppn = (RecyclerView) findViewById(R.id.recycler_DoctorAppn);
        itemList = new ArrayList<>();
        adapter_docAppn = new AdapterDocViewAppn(getApplicationContext(), this);
        recycler_docAppn.setAdapter(adapter_docAppn);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recycler_docAppn.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        try {
            getAppoinments();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                logOutDialog = new LogOutDialog(Doctor_Main.this);
                (logOutDialog.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);
                logOutDialog.show();
            }
        });


    }

    @Override
    public void onBackPressed() {

    }


    public void getAppoinments() throws JSONException {


        final String url = getResources().getString(R.string.api) + "doctorappn.php";
        final RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        final StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    itemList.clear();
                    swipeRefresh.setRefreshing(false);
                    vf.setVisibility(View.GONE);
                    JSONObject jsonObject = new JSONObject(response);
                    System.out.println("Response " + response);


                    String error = jsonObject.getString("error");
                    String messages = jsonObject.getString("messages");

                    JSONArray jsonArray = new JSONArray(messages);

                    if (jsonArray.length() == 0) {

                        noAppn.setVisibility(View.VISIBLE);

                    } else {

                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);


                            String patient_name = jsonObject1.getString("patient_name");
                            String date = jsonObject1.getString("date");
                            String symptomsDescription = jsonObject1.getString("symptomsDescription");

                            DoctorAppn doctorAppn = new DoctorAppn(patient_name, date, symptomsDescription);
                            itemList.add(doctorAppn);
                        }
                        adapter_docAppn.addAll(itemList);
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
