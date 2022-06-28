package com.example.hassanbazzounmobileapplicationproject2.Dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CalendarView;
import android.widget.DatePicker;
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
import com.example.hassanbazzounmobileapplicationproject2.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import org.json.JSONException;
import org.json.JSONObject;
import org.threeten.bp.LocalDate;
import org.threeten.bp.format.DateTimeFormatter;

public class ConvertBtnDialog extends Dialog implements View.OnClickListener {


    public Activity activity;
    public Dialog dialog;


    MaterialCalendarView calendarCons;
    EditText price_cons;
    TextView convert_submit;
    private String date_picked;
    sharedInfo sharedInfo;

    public ConvertBtnDialog(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.activity = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.convert_cons);


        sharedInfo = new sharedInfo(activity.getApplicationContext());
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        convert_submit = (TextView) findViewById(R.id.convert_submit);

        calendarCons = (MaterialCalendarView) findViewById(R.id.calendarCons);
        price_cons = (EditText) findViewById(R.id.price_cons);


        calendarCons.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {


                date_picked = LocalDate.of(date.getYear(), date.getMonth(), date.getDay())
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));


            }
        });

        convert_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    Convert();
                    dismiss();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });


    }



    @Override
    public void onClick(View view) {

    }

    public void Convert() throws JSONException {


        final String url = activity.getResources().getString(R.string.api)+"convert.php?name=" + sharedInfo.getPatientNameIntent() + "&date=" + sharedInfo.getDateIntent() + "&price=" + price_cons.getText().toString();
        final RequestQueue queue = Volley.newRequestQueue(activity.getApplicationContext());
        System.out.println("Response " + url);

        final StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {


                    JSONObject jsonObject = new JSONObject(response);
                    System.out.println("Response " + response);


                    String error = jsonObject.getString("error");


                    if (error.equals("true")) {

                    } else {

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

