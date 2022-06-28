package com.example.hassanbazzounmobileapplicationproject2.Dialogs;


import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import androidx.core.content.ContextCompat;

import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hassanbazzounmobileapplicationproject2.Classes.sharedInfo;
import com.example.hassanbazzounmobileapplicationproject2.Doctor_Main;
import com.example.hassanbazzounmobileapplicationproject2.Edit_activity;
import com.example.hassanbazzounmobileapplicationproject2.LoginMain;
import com.example.hassanbazzounmobileapplicationproject2.R;
import com.example.hassanbazzounmobileapplicationproject2.patientMainActivity;

import org.json.JSONException;
import org.json.JSONObject;


public class DeleteBtnDialog extends Dialog implements View.OnClickListener {


    public Activity activity;
    public Dialog dialog;
    sharedInfo sharedInfo;

    TextView delete_Yes;
    TextView delete_No;


    public DeleteBtnDialog(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.activity = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.delete_appn);


         sharedInfo = new sharedInfo(activity.getApplicationContext());
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        delete_Yes = (TextView) findViewById(R.id.delete_Yes);
        delete_No = (TextView) findViewById(R.id.delete_No);


        delete_Yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    Delete();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });


        delete_No.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dismiss();


            }
        });


    }


//    @Override
//    public void onBackPressed() {
//       activity.finish();
//    }

    @Override
    public void onClick(View view) {

    }


    public void Delete() throws JSONException {


        final String url = activity.getResources().getString(R.string.api)+"delete.php?name=" +sharedInfo.getPatientNameIntent()+"&date=" +sharedInfo.getDateIntent()+"&symptoms="+sharedInfo.getSymptomIntent();
        final RequestQueue queue = Volley.newRequestQueue(activity.getApplicationContext());

        final StringRequest stringRequest = new StringRequest(Request.Method.GET,url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {


                    JSONObject jsonObject = new JSONObject(response);
                    System.out.println("Response " + response);


                    String error = jsonObject.getString("error");

                    if(error.equals("true")){



                    }else{

                        Intent intent = new Intent(activity.getApplicationContext(), Doctor_Main.class);
                        activity.startActivity(intent);


                    }





                } catch (JSONException e) {
                    System.out.println("JSONException " + e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

// add it to the RequestQueue

        queue.add(stringRequest.setRetryPolicy(new DefaultRetryPolicy(10000, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)));

    }
}

