package com.krein.chandratya.anonimaapp;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

/**
 * Created by waski on 10/05/2018.
 */

public class AdapterChatItem extends RecyclerView.Adapter<AdapterChatItem.VH> {

    private ArrayList<ChatModel> chats;
    private FirebaseAuth auth;
    public AdapterChatItem(ArrayList<ChatModel> chats, FirebaseAuth auth) {
        this.chats = chats;
        this.auth = auth;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.cardview_userchat,parent, false);
        return new AdapterChatItem.VH(itemView);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        ChatModel chat = chats.get(position);
        ViewGroup.MarginLayoutParams  params = (ViewGroup.MarginLayoutParams) holder.cdChat.getLayoutParams();
        if(auth.getCurrentUser().getUid().equals(chat.getUserFrom())){
            holder.lblUser.setText(""+chat.getUserFromEmail());
            holder.cdChat.setCardBackgroundColor(Color.LTGRAY);
            params.setMargins(100,10,10,10);
            holder.cdChat.setLayoutParams(params);
//            holder.cdChat.MarginLayoutParams(new ViewGroup.MarginLayoutParams())
        }else{
            params.setMargins(10,10,100,10);
            holder.cdChat.setLayoutParams(params);
            holder.lblUser.setText(""+chat.getUserToEmail());
        }
        holder.lblText.setText(""+chat.getTxt());
    }

    @Override
    public int getItemCount() {
        return chats.size();
    }

    public class VH extends RecyclerView.ViewHolder{
        private CardView cdChat;
        private TextView lblUser, lblText;
        public VH(View itemView) {
            super(itemView);
            cdChat=(CardView)itemView.findViewById(R.id.cdChat);
            lblUser=(TextView)itemView.findViewById(R.id.lblChatItemUser);
            lblText=(TextView)itemView.findViewById(R.id.lblChatItemTXT);
        }
    }
}
