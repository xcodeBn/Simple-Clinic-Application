package com.example.hassanbazzounmobileapplicationproject2.Dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.example.hassanbazzounmobileapplicationproject2.Classes.sharedInfo;
import com.example.hassanbazzounmobileapplicationproject2.LoginMain;
import com.example.hassanbazzounmobileapplicationproject2.R;


public class LogOutDialog extends Dialog implements View.OnClickListener {


    public Activity activity;
    public Dialog dialog;
    sharedInfo sharedInfo;

    TextView logout_yesBtn;
    TextView logout_noBtn;


    public LogOutDialog(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.activity = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.logoutdialog_layout);


        sharedInfo = new sharedInfo(activity.getApplicationContext());
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        logout_yesBtn = (TextView) findViewById(R.id.logout_yesBtn);
        logout_noBtn = (TextView) findViewById(R.id.logout_noBtn);


        logout_yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sharedInfo.Clear();
                Intent intent = new Intent(activity.getApplicationContext(), LoginMain.class);
                activity.startActivity(intent);

            }
        });


        logout_noBtn.setOnClickListener(new View.OnClickListener() {
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
}

