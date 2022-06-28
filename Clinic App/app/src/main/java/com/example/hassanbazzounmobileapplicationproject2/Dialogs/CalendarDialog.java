package com.example.hassanbazzounmobileapplicationproject2.Dialogs;


import android.app.Activity;
import android.app.Dialog;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.annotation.*;


import android.view.View;
import androidx.annotation.NonNull;
import android.view.Window;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.hassanbazzounmobileapplicationproject2.Classes.sharedInfo;
import com.example.hassanbazzounmobileapplicationproject2.R;
import com.example.hassanbazzounmobileapplicationproject2.fragments.Patient_MakeAppoinments;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import org.threeten.bp.LocalDate;
import org.threeten.bp.format.DateTimeFormatter;


public class CalendarDialog extends Dialog implements View.OnClickListener {


    public Activity activity;
    String date_picked;
    sharedInfo sharedInfo;
    TextView choose_dateAppn;

    public CalendarDialog(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.activity = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.calendarview);


        MaterialCalendarView calendarView = (MaterialCalendarView) findViewById(R.id.makeappn_date);
        choose_dateAppn = (TextView) findViewById(R.id.choose_dateAppn);
        sharedInfo = new sharedInfo(activity.getApplicationContext());


            calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
                @Override
                public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {


                    date_picked = LocalDate.of(date.getYear(), date.getMonth(), date.getDay())
                            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));


                }
            });

        choose_dateAppn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sharedInfo.setDateIntent(date_picked);
                dismiss();
            }
        });


    }



    @Override
    public void onClick(View view) {

    }
}

