package com.example.android.medtimer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddMedic extends Fragment {

    //Classes used in the Firebase Database API
    // 1: Reference to drug database
    private FirebaseDatabase mFirebaseDatabase;

    //2: Reference to the specific part of the database
    private DatabaseReference mDrugDatabaseReference;

    private EditText mNameOfMedic;
    private EditText mDescription;
    private EditText mStartTime;
    private EditText mStartDate;
    private EditText mEndDate;
    private EditText mInterval;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Add Medication");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.add_medic, container, false);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        // Get reference to specific part of the database
        mDrugDatabaseReference = mFirebaseDatabase.getReference().child("userMedication");

        try {
            View.OnClickListener handler = new View.OnClickListener() {
                public void onClick(View v) {
                // we will use switch statement and just
                // get the button's id to make things easier
                switch (v.getId()) {
                    // toast will be shown with the
                    // EditText for plain text input
                    case R.id.save:
                        getEditTextValues(v);
                        clearEditTextValues();
                        Toast.makeText(getContext(), "Data added successfully.", Toast.LENGTH_SHORT).show();
                        break;

                    // the EditText for plain text input will be cleared
                    case R.id.clear:
                        clearEditTextValues();
                        break;
                }
                }

            };

            // we will set the listeners of our three buttons
            view.findViewById(R.id.save).setOnClickListener(handler);
            view.findViewById(R.id.clear).setOnClickListener(handler);

        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }

    private void getEditTextValues(View view) {
        mDescription = (EditText) view.findViewById(R.id.editTextDescription);
        mNameOfMedic = (EditText) view.findViewById(R.id.editTextNameOfMedic);
        mInterval = (EditText) view.findViewById(R.id.editTextInterval);
        mStartTime = (EditText) view.findViewById(R.id.editTextStartTime);
        mStartDate = (EditText) view.findViewById(R.id.editTextStartDate);
        mEndDate = (EditText) view.findViewById(R.id.editTextEndDate);

        MedicationModel medicationModel = new MedicationModel(mNameOfMedic.getText(), mDescription.getText(),
                mInterval.getText() , mStartDate.getText(), mEndDate.getText(),
                mStartTime.getText());

        mDrugDatabaseReference.push().setValue(medicationModel);
    }

    private void clearEditTextValues(){
        mNameOfMedic.setText("");
        mDescription.setText("");
        mInterval.setText("");
        mStartDate.setText("");
        mStartTime.setText("");
        mEndDate.setText("");
    }
}