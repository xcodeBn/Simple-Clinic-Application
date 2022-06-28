package com.example.hassanbazzounmobileapplicationproject2;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hassanbazzounmobileapplicationproject2.Classes.sharedInfo;
import com.example.hassanbazzounmobileapplicationproject2.Dialogs.ConvertBtnDialog;
import com.example.hassanbazzounmobileapplicationproject2.Dialogs.DeleteBtnDialog;
import com.example.hassanbazzounmobileapplicationproject2.Dialogs.ModifyBtnDialog;

public class Edit_activity extends AppCompatActivity {

    TextView patient_name_intent;
    TextView symptoms_Description_intent;
    TextView date_Appn_intent;

    TextView deleteBtn;
    TextView modifyBtn;
    TextView convertBtn;
    ImageView arrow_back;

    DeleteBtnDialog deleteBtnDialog;
    ModifyBtnDialog modifyBtnDialog;
    ConvertBtnDialog convertBtnDialog;
    Toolbar toolbar;
    sharedInfo sharedInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_activity);


        toolbar = (Toolbar) findViewById(R.id.editToolbar);
        setSupportActionBar(toolbar);
        arrow_back = (ImageView) findViewById(R.id.arrow_back);

        arrow_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Edit_activity.this,Doctor_Main.class);
                startActivity(intent);
            }
        });


        Intent intent = getIntent();
        String patient_name = intent.getStringExtra("patient_name");
        String date = intent.getStringExtra("date");
        String symptomsDescription = intent.getStringExtra("symptomsDescription");

        sharedInfo = new sharedInfo(getApplicationContext());
        sharedInfo.setPatientNameIntent(patient_name);
        sharedInfo.setDateIntent(date);
        sharedInfo.setSymptomIntent(symptomsDescription);


        //Info
        patient_name_intent = (TextView) findViewById(R.id.patient_name_intent);
        symptoms_Description_intent = (TextView) findViewById(R.id.symptoms_Description_intent);
        date_Appn_intent = (TextView) findViewById(R.id.date_Appn_intent);
        patient_name_intent.setText(patient_name);
        date_Appn_intent.setText(date);
        symptoms_Description_intent.setText(symptomsDescription);

        //Btns
        deleteBtn = (TextView) findViewById(R.id.deleteBtn);
        modifyBtn = (TextView) findViewById(R.id.modifyBtn);
        convertBtn = (TextView) findViewById(R.id.convertBtn);

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                deleteBtnDialog = new DeleteBtnDialog(Edit_activity.this);
                (deleteBtnDialog.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);
                deleteBtnDialog.show();


            }
        });

        modifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                modifyBtnDialog = new ModifyBtnDialog(Edit_activity.this);
                (modifyBtnDialog.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);
                modifyBtnDialog.show();

            }
        });

        convertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                convertBtnDialog = new ConvertBtnDialog(Edit_activity.this);
                (convertBtnDialog.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);
                convertBtnDialog.show();

            }
        });

    }
}
