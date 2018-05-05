package com.krein.chandratya.anonimaapp;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chandratya on 01/05/2018.
 */

public class NearbyAdapter extends RecyclerView.Adapter<NearbyAdapter.MyViewHolder> {
    private List<NearbyCons> listNearby1;
    private Context context;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView namaUser, Konten,idPosting;
        public CardView cvNearby;

        public MyViewHolder(View view) {
            super(view);
            namaUser = (TextView)view.findViewById(R.id.user);
            Konten = (TextView)view.findViewById(R.id.isikonten);
            idPosting = (TextView)view.findViewById(R.id.idPosting);
            cvNearby=(CardView)view.findViewById(R.id.cdPost);
        }
    }
    public NearbyAdapter(Context context, ArrayList<NearbyCons> nearbyList){
        this.context = context;
        this.listNearby1 = nearbyList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.cardview_nearby,parent, false);
        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
    final NearbyCons post = listNearby1.get(position);
    holder.namaUser.setText(post.getUser());
    holder.Konten.setText(post.getPosting());
    holder.idPosting.setText(post.getKeyComment());
    }

    @Override
    public int getItemCount() {
        return listNearby1.size();
    }


}
