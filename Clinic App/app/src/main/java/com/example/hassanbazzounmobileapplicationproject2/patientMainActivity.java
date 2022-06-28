package com.example.hassanbazzounmobileapplicationproject2;

import android.app.Activity;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;

import com.example.hassanbazzounmobileapplicationproject2.Classes.sharedInfo;
import com.example.hassanbazzounmobileapplicationproject2.Dialogs.LogOutDialog;
import com.example.hassanbazzounmobileapplicationproject2.fragments.Patient_MakeAppoinments;
import com.example.hassanbazzounmobileapplicationproject2.fragments.Patient_ViewAppointments;
import com.example.hassanbazzounmobileapplicationproject2.fragments.Patient_ViewConsultations;

public class patientMainActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    Activity activity;
    Toolbar toolbar;

    LogOutDialog logOutDialog;
    ImageView logoutBtn;
    sharedInfo sharedInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_main);





        try {

            toolbar = (Toolbar) findViewById(R.id.mainToolbar);
            setSupportActionBar(toolbar);
            sharedInfo = new sharedInfo(getApplicationContext());
            logoutBtn = (ImageView) findViewById(R.id.logoutBtn);
            mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
            mViewPager = (ViewPager) findViewById(R.id.view_pager);
            mViewPager.setAdapter(mSectionsPagerAdapter);
            TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
            mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {


                    mViewPager.setCurrentItem(tab.getPosition());

                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });

            logoutBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    logOutDialog = new LogOutDialog(patientMainActivity.this);
                    (logOutDialog.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);
                    logOutDialog.show();
                }
            });


        } catch (Exception e) {

            System.out.println("ErrorPatientMain " + e.getMessage());
        }


    }

    @Override
    public void onBackPressed() {

    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);

        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {


                case 0:
                    return new Patient_MakeAppoinments();

                case 1:

                    return new Patient_ViewAppointments();


                case 2:

                    return new Patient_ViewConsultations();



            }

            return null;
        }

        @Override
        public int getCount() {

            return 3;
        }
    }
}
