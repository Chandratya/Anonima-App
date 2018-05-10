package com.krein.chandratya.anonimaapp;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chandratya on 01/05/2018.
 */

public class NearbyAdapter extends RecyclerView.Adapter<NearbyAdapter.MyViewHolder> {
    private List<PostingModel> listNearby1;
    private Context context;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView namaUser, Konten, idPosting;
        public CardView cvNearby;
        public ImageButton btnComment;

        public MyViewHolder(View view) {
            super(view);
            namaUser = (TextView)view.findViewById(R.id.user);
            Konten = (TextView)view.findViewById(R.id.isikonten);
            idPosting = (TextView)view.findViewById(R.id.idPosting);
            cvNearby=(CardView)view.findViewById(R.id.cdPost);
            btnComment = (ImageButton)view.findViewById(R.id.btnComment);
        }
    }
    public NearbyAdapter(Context context, ArrayList<PostingModel> nearbyList){
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
    public void onBindViewHolder(final MyViewHolder holder, int position) {
    final PostingModel post = listNearby1.get(position);
    holder.namaUser.setText(""+post.getUserName().split("@")[0]);
    holder.Konten.setText(post.getPosting());
    holder.idPosting.setText(post.getKey());
    holder.btnComment.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("value", (Serializable) listNearby1);
            Intent abc = new Intent(context, DetailPost.class);

            Intent intent = abc.putExtra(bundle);
        }
    });
    }

    @Override
    public int getItemCount() {
        return listNearby1.size();
    }


}
