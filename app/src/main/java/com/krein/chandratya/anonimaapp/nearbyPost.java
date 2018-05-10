package com.krein.chandratya.anonimaapp;


import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.zip.Inflater;


/**
 * A simple {@link Fragment} subclass.
 */
public class nearbyPost extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter NearbyAdapter;
    private ArrayList<PostingModel> listNearby;
    private NearbyAdapter mAdapter;
    private ProgressBar pbLoad;


    public nearbyPost() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nearby_post, container, false);

        recyclerView = view.findViewById(R.id.rvNearby);
        recyclerView.setHasFixedSize(true);

        pbLoad=(ProgressBar)view.findViewById(R.id.pbNearby);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        listNearby = new ArrayList<>();

        setHasOptionsMenu(true);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
       // menu.clear();
        inflater.inflate(R.menu.chat_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (item.getItemId()){
            case R.id.action_chat:

                break;
        }

        return super.onOptionsItemSelected(item);

    }

    public void onStart() {
        super.onStart();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference tbPosting = FirebaseDatabase.getInstance().getReference("Postings");
        pbLoad.setVisibility(0);
        tbPosting.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listNearby.clear();
                pbLoad.setVisibility(8);
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    PostingModel post = ds.getValue(PostingModel.class);

                    listNearby.add(post);
                }
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
                NearbyAdapter NearbyList = new NearbyAdapter(getContext(), listNearby);
                recyclerView.setAdapter(NearbyList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}

