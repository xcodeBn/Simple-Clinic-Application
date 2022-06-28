package com.example.hassanbazzounmobileapplicationproject2.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;



import androidx.fragment.*;
import com.google.android.material.snackbar.Snackbar;
import androidx.core.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;
import androidx.fragment.app.Fragment;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hassanbazzounmobileapplicationproject2.Classes.sharedInfo;
import com.example.hassanbazzounmobileapplicationproject2.Dialogs.CalendarDialog;
import com.example.hassanbazzounmobileapplicationproject2.R;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;


public class Patient_MakeAppoinments extends Fragment {


    CalendarDialog calendarDialog;
    ImageView calendarIcon;
    RelativeLayout relative_makeappn;
    EditText symptoms_Description_patient;
    CheckBox firstVisit;
    TextView submit_appn;
    sharedInfo sharedInfo;
    String isChecked = "";
    ViewFlipper vf;

    public Patient_MakeAppoinments() {

    }


    @Nullable
    @Override


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.patientmakeappoinments, container, false);

        //viewFlipper
        vf = (ViewFlipper) view.findViewById(R.id.vf);

        symptoms_Description_patient = (EditText) view.findViewById(R.id.symptoms_Description_patient);
        firstVisit = (CheckBox) view.findViewById(R.id.checkboxFVisit);
        submit_appn = (TextView) view.findViewById(R.id.submit_appn);

        sharedInfo = new sharedInfo(getActivity().getApplicationContext());

        //Hide keyboard on screen click
        relative_makeappn = (RelativeLayout) view.findViewById(R.id.relative_makeappn);
        relative_makeappn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeKeyboard();
            }
        });

        calendarIcon = (ImageView) view.findViewById(R.id.calendarIcon);
        calendarIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Open Calendar
                calendarDialog = new CalendarDialog(getActivity());
                calendarDialog.show();

            }
        });


        submit_appn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                submit_appn.setText("");
                vf.setDisplayedChild(0);
                vf.setVisibility(View.VISIBLE);

                if (firstVisit.isChecked()){

                    isChecked="Yes";
                }else{
                    isChecked="No";
                }

                try {
                    makeAPPN(isChecked);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });


        return view;
    }

    public void makeAPPN(String isChecked) throws JSONException {


        final String url = getResources().getString(R.string.api)+"makeappn.php?name=" + sharedInfo.getUser() + "&date=" + sharedInfo.getDateIntent() + "&symptoms=" + symptoms_Description_patient.getText().toString()+"&firstVis="+isChecked;
        final RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());

        final StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    vf.setVisibility(View.GONE);
                    submit_appn.setText("Submit");
                    JSONObject jsonObject = new JSONObject(response);

                    String error = jsonObject.getString("error");


                    if (error.equals("true")) {

                    }
                    else {

                        View parentLayout = getActivity().findViewById(android.R.id.content);
                        Snackbar snackbar = Snackbar.make(parentLayout, error, Snackbar.LENGTH_LONG)
                                .setAction("", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                    }
                                })
                                .setActionTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorPrimary));
                        View sbView = snackbar.getView();
                        sbView.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.success));
                        snackbar.show();

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

    public void closeKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {

            InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            assert inputMethodManager != null;
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);


        }

    }

}