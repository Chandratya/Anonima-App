package com.krein.chandratya.anonimaapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

public class userChat extends AppCompatActivity {

    private ImageView btnSend;
    private EditText txtChat;
    private RecyclerView ChatViewList;
    private ProgressDialog pd;

    private String chatCode;
    private ArrayList<ChatModel> chatList;
    private PostingModel post;
    private ChatModel chat;
    private AdapterChatItem adapterChatItem;

    private FirebaseAuth auth;
    private FirebaseDatabase fdb;
    private DatabaseReference refChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_chat);

        chatCode="X";

        chatList=new ArrayList<>();

        txtChat=(EditText) findViewById(R.id.txtChatTxt);
        btnSend=(ImageView) findViewById(R.id.btnChatSend);
        ChatViewList=(RecyclerView)findViewById(R.id.ChatViewList);

        Intent ini = getIntent();
        post = (PostingModel) ini.getSerializableExtra("ITEM_POSTING");
        if(post==null){
            chat= (ChatModel) ini.getSerializableExtra("ITEM_CHAT");
            setTitle("Chat with "+chat.getUserToEmail());
            post=null;
        }else {
            setTitle("" + post.getUserName().split("@")[0]);
        }
        auth=FirebaseAuth.getInstance();
        fdb=FirebaseDatabase.getInstance();
        refChat=fdb.getReference("Chats");

        ChatViewList=(RecyclerView)findViewById(R.id.ChatViewList);
        ChatViewList.setLayoutManager(new LinearLayoutManager(this));
        adapterChatItem=new AdapterChatItem(chatList,auth);
        ChatViewList.setAdapter(adapterChatItem);

        //Check Chat Code
//        for(ChatModel ch: chatList){
//            if(ch.getUserFrom().equals(auth.getCurrentUser().getUid()) && ch.getUserTo().equals(post.getUserId())){
//                chatCode=ch.getKey();
//                break;
//            }
//        }

        populateAllChat();

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text, tgl, userId, userPostId, userEmail, userPostEmail;
                text=txtChat.getText().toString();
                tgl=new Date().toString();
                userId=auth.getCurrentUser().getUid();
                userEmail=auth.getCurrentUser().getEmail();
                if(post!=null){
                    userPostId=post.getUserId();
                    userPostEmail=post.getUserName();
                }else{
                    userPostId=chat.getUserTo();
                    userPostEmail=chat.getUserToEmail();
                }
                String chatKey;
                ChatModel chat = new ChatModel(text, tgl, userId, userPostId, userEmail, userPostEmail);
                chatKey = refChat.push().getKey();
//                chat.setKey(chatKey);
//                String keyItem = refChat.child(chatKey).push().getKey();
//                chat.setKeyItem(keyItem);
                refChat.child(chatKey).setValue(chat);

                txtChat.setText("");
            }
        });
    }

    public void populateAllChat(){
        pd = new ProgressDialog(this);
        pd.setMessage("Checking Chat ...");
        pd.show();
        refChat.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ps: dataSnapshot.getChildren()){
                    ChatModel mChat = ps.getValue(ChatModel.class);
//                    for(DataSnapshot ps1: ps.getChildren()){
//                        ChatModel mChatItem = ps1.getValue(ChatModel.class);
//                    if(mChat.getUserFrom().equals(auth.getCurrentUser().getUid()) || mChat.getUserTo().equals(post.getUserId())){
                        chatList.add(mChat);
                        adapterChatItem.notifyDataSetChanged();
//                        Log.d("CHATLIST:CHILD:VAL",""+ps1.toString());
//                    }
//                    Log.d("CHATLIST::VAL",""+ps.toString());

                }

                Log.d("CHATLIST::COUNT",""+chatList.size());
//                adapterChatItem=new AdapterChatItem(chatList);
//                ChatViewList.setAdapter(adapterChatItem);


//                for(ChatModel ch: chatList){
//                    if((ch.getUserTo()).equals(post.getUserId())){
//                        chatCode=ch.getKey();
//                        //break;
//                    }
//                }

                //chatCode=getChatCode(chatList)==null?"X":getChatCode(chatList);
                Log.d("CHATLIST::CODE",""+chatCode);

                pd.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private String getChatCode(ArrayList<ChatModel> chats) {
        String code = "X";
        for (ChatModel ch : chats) {
            if ((ch.getUserFrom()).equals(auth.getCurrentUser().getUid()) && (ch.getUserTo()).equals(post.getUserId())) {
                code=ch.getKey();
                break;
            }
        }
        return code;
    }
}
