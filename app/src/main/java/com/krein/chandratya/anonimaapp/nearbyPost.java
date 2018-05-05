package com.krein.chandratya.anonimaapp;


import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class nearbyPost extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter NearbyAdapter;
    private ArrayList<NearbyCons> listNearby;
    private NearbyAdapter mAdapter;


    public nearbyPost() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nearby_post, container, false);

        recyclerView = view.findViewById(R.id.rvNearby);
        recyclerView.setHasFixedSize(true);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        listNearby = new ArrayList<>();
        return view;
    }

    public void onStart() {
        super.onStart();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        database.getReference("postnearby").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listNearby.clear();
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    NearbyCons post = ds.getValue(NearbyCons.class);

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

