package com.krein.chandratya.anonimaapp;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChatListActivity extends AppCompatActivity {

    private RecyclerView rChatList;
    private chatListAdapter chatListAdapter;
    private ArrayList<ChatModel> chats;

    private FirebaseAuth auth;
    private FirebaseDatabase fdb;
    private DatabaseReference refChats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_chatlist_2);

        chats=new ArrayList<>();
        rChatList=(RecyclerView)findViewById(R.id.listChatList);
        rChatList.setLayoutManager(new LinearLayoutManager(this));
        chatListAdapter=new chatListAdapter(chats);
        rChatList.setAdapter(chatListAdapter);

        auth= FirebaseAuth.getInstance();
        fdb= FirebaseDatabase.getInstance();
        refChats=fdb.getReference("Chats");

        populateChats();
    }

    private void populateChats(){
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Getting Chat ...");
        pd.show();
        refChats.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ArrayList<ChatModel> userChats = new ArrayList<>();
                String userTemp = "X";

                for(DataSnapshot ps: dataSnapshot.getChildren()){
                    ChatModel chat = ps.getValue(ChatModel.class);

                    if(auth.getCurrentUser().getUid().equals(chat.getUserFrom())) {
                        //userChats.add(chat);
                        if(chat.getUserTo().equals(userTemp)==false){
                            userTemp=chat.getUserTo();
                            userChats.add(chat);
                        }
                    }
//                    chats.add(chat);
//                    chatListAdapter.notifyDataSetChanged();
                }
                chats=userChats;
                //chatListAdapter.notifyDataSetChanged();
                chatListAdapter=new chatListAdapter(chats);
                rChatList.setAdapter(chatListAdapter);

                Log.d("CHATLIST:ACT:COUNT",""+chats.size());
                pd.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
