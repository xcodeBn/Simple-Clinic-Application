package com.example.hassanbazzounmobileapplicationproject2.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hassanbazzounmobileapplicationproject2.Classes.DoctorAppn;
import com.example.hassanbazzounmobileapplicationproject2.Edit_activity;
import com.example.hassanbazzounmobileapplicationproject2.R;

import java.util.ArrayList;


public class AdapterDocViewAppn extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_ITEM = 0;
    private final int VIEW_PROG = 1;
    private LinearLayoutManager mLinearLayoutManager;
    Activity activity;
    private ArrayList<DoctorAppn> itemList;
    private Context c;

    public AdapterDocViewAppn(Context c, Activity Act) {
        itemList = new ArrayList<DoctorAppn>();
        this.c = c;
        this.activity = Act;
    }


    @Override
    public int getItemViewType(int position) {
        return itemList.get(position) == null ? VIEW_PROG : VIEW_ITEM;
    }

    public void setLinearLayoutManager(LinearLayoutManager linearLayoutManager) {
        this.mLinearLayoutManager = linearLayoutManager;
    }


    @Override

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        try {


            if (viewType == VIEW_ITEM) {


                return new holder_text(LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_viewappn_items, parent, false));

            } else if (viewType == VIEW_ITEM) {


            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {


        try {
            if (holder instanceof holder_text) {



                ((holder_text) holder).patient_name.setText(itemList.get(position).getPatient_name());
                ((holder_text) holder).symptomsDescription.setText(itemList.get(position).getSymptomsDescription());
                ((holder_text) holder).date.setText(itemList.get(position).getDate());


                //Intent to edit page

                ((holder_text) holder).editBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(activity, Edit_activity.class);
                        intent.putExtra("patient_name", itemList.get(position).getPatient_name());
                        intent.putExtra("date", itemList.get(position).getDate());
                        intent.putExtra("symptomsDescription", itemList.get(position).getSymptomsDescription());
                        activity.startActivity(intent);

                    }
                });

            }

        } catch (Exception e) {
            System.out.println("adapterError" + e.getMessage().toString());
        }


    }


    public void addAll(ArrayList<DoctorAppn> itemList2) {
        itemList.clear();
        itemList.addAll(itemList2);
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return itemList.size();
    }


    public class holder_text extends RecyclerView.ViewHolder {

        TextView patient_name;
        TextView symptomsDescription;
        TextView date;
        ImageView editBtn;



        public holder_text(View itemView) {

            super(itemView);

            patient_name = (TextView) itemView.findViewById(R.id.patient_name);
            symptomsDescription = (TextView) itemView.findViewById(R.id.symptoms_Description);
            date = (TextView) itemView.findViewById(R.id.date_DocAppn);
            editBtn = (ImageView) itemView.findViewById(R.id.editBtn);

        }

    }


}