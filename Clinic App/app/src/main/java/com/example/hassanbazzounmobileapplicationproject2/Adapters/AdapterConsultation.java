package com.example.hassanbazzounmobileapplicationproject2.Adapters;

import android.app.Activity;
import android.content.Context;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hassanbazzounmobileapplicationproject2.Classes.Consultation;
import com.example.hassanbazzounmobileapplicationproject2.R;

import java.util.ArrayList;

public class AdapterConsultation extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_ITEM = 0;
    private final int VIEW_PROG = 1;
    private LinearLayoutManager mLinearLayoutManager;
    Activity activity;
    private ArrayList<Consultation> itemList;
    private Context c;

    public AdapterConsultation(Context c, Activity Act) {
        itemList = new ArrayList<Consultation>();
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


                return new holder_text(LayoutInflater.from(parent.getContext()).inflate(R.layout.consultations_items, parent, false));

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


                ((holder_text) holder).date.setText(itemList.get(position).getDate());
                ((holder_text) holder).payment_value.setText(itemList.get(position).getPayment_value());


            }

        } catch (Exception e) {
            System.out.println("adapterError" + e.getMessage().toString());
        }


    }


    public void addAll(ArrayList<Consultation> itemList2) {
        itemList.clear();
        itemList.addAll(itemList2);
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return itemList.size();
    }


    public class holder_text extends RecyclerView.ViewHolder {

        TextView payment_value;
        TextView date;


        public holder_text(View itemView) {

            super(itemView);

            payment_value = (TextView) itemView.findViewById(R.id.price);
            date = (TextView) itemView.findViewById(R.id.date_cons);

        }

    }


}