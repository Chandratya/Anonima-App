package com.krein.chandratya.anonimaapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

/**
 * Created by waski on 10/05/2018.
 */

public class chatListAdapter extends RecyclerView.Adapter<chatListAdapter.VH> {

    private ArrayList<ChatModel> chats;
//    private FirebaseAuth auth;
    public chatListAdapter(ArrayList<ChatModel> chats) {
        this.chats = chats;
//        this.auth = auth;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.cardview_chatlist,parent, false);
        return new chatListAdapter.VH(itemView);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        final ChatModel chat = chats.get(position);
        holder.lblUser.setText(""+chat.getUserToEmail());
        holder.lblText.setText(""+chat.getTxt());
        holder.btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chatroom = new Intent(view.getContext(), userChat.class);
                chatroom.putExtra("ITEM_CHAT",chat);
                view.getContext().startActivity(chatroom);
            }
        });
    }

    @Override
    public int getItemCount() {
        return chats.size();
    }

    public class VH extends RecyclerView.ViewHolder{
        private CardView cdChat;
        private TextView lblUser, lblText;
        private Button btnGo;
        public VH(View itemView) {
            super(itemView);
            cdChat=(CardView)itemView.findViewById(R.id.cdChatlist);
            lblUser=(TextView)itemView.findViewById(R.id.lblChatlistUser);
            lblText=(TextView)itemView.findViewById(R.id.lblChatlistTXT);
            btnGo=(Button)itemView.findViewById(R.id.btnChatlistGO);
        }
    }
}
