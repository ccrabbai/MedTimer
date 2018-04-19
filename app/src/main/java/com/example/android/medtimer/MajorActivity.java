package com.example.android.medtimer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MajorActivity extends Fragment {

    private ListView mdrugListView;
    private MedAdapter mMedAdapter;
    private ProgressBar mProgressBar;

    //Classes used in the Firebase Database API
    // 1: Reference to med database
    private FirebaseDatabase mFirebaseDatabase;

    //2: Reference to the specific part of the database
    private DatabaseReference mDrugDatabaseReference;

    // 3: A listener that reads the devices state from the db
    private ChildEventListener mChildEventListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Med-Manager");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_major, container, false);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        // Get reference to specific part of the database
        mDrugDatabaseReference = mFirebaseDatabase.getReference().child("userMedication");


        // Initialize references to views
        mProgressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        mdrugListView = (ListView) view.findViewById(R.id.drugListView);

        // Initialize message ListView and its adapter
        List<MedicationModel> medicationModels = new ArrayList<>();
        mMedAdapter = new MedAdapter(getContext(), R.layout.item_drug, medicationModels);
        mdrugListView.setAdapter(mMedAdapter);

        // Initialize progress bar
        mProgressBar.setVisibility(ProgressBar.INVISIBLE);

        return view;
    }

    private void attachDataBaseReadListener(){
        if (mChildEventListener == null) {
            //The anonymous method for childEventListener
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    //Get the database object and convert it to a MedicationModel object
                    MedicationModel medicationModel = dataSnapshot.getValue(MedicationModel.class);
                    mMedAdapter.add(medicationModel);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    //Get the database object and convert it to a MedicationModel object
                    MedicationModel medicationModel = dataSnapshot.getValue(MedicationModel.class);
                    mMedAdapter.add(medicationModel);
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {}

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {}

                @Override
                public void onCancelled(DatabaseError databaseError) {}
            };

            //Add the childEventListener to the database reference
            mDrugDatabaseReference.addChildEventListener(mChildEventListener);
        }
    }

    private void detachDataBaseReadListener(){
        if (mChildEventListener != null) {
            mDrugDatabaseReference.removeEventListener(mChildEventListener);
            mChildEventListener = null;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        attachDataBaseReadListener();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        detachDataBaseReadListener();
    }

}