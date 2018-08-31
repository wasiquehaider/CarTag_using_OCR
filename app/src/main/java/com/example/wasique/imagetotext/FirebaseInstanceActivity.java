package com.example.wasique.imagetotext;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Wasique on 5/6/2018.
 */

public class FirebaseInstanceActivity {
    private Context context;

    public FirebaseInstanceActivity(Context context) {
        this.context = context;
    }

    private void firebaseInstances(final String getCarNumber) {
        Log.d("numb", getCarNumber);
//        FirebaseDatabase database = FirebaseDatabase.getInstance();

//        String numbplate = "NumPlates";
        final DatabaseReference zonesRef = FirebaseDatabase.getInstance().getReference("NumPlates");
//        DatabaseReference zone1Ref = zonesRef.child("AKN-341");

//        DatabaseReference zone1Ref = zonesRef.child("AKN-341");


        zonesRef.addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("AKN-341"))
                        {
                            DatabaseReference zone1Ref = zonesRef.child(String.valueOf("AKN-341"));
                            zone1Ref.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    String name = dataSnapshot.child("CarName").getValue().toString();
                                    String company = dataSnapshot.child("Company").getValue().toString();
                                    String number = dataSnapshot.child("NumPlate").getValue().toString();
                                    String price = dataSnapshot.child("NetPrice").getValue().toString();

                                    Intent intent = new Intent(context, StepTwo.class);
                                    intent.putExtra("name", name);
                                    intent.putExtra("company", company);
                                    intent.putExtra("number", number);
                                    intent.putExtra("price", price);
                                    context.startActivity(intent);
                                    Log.i("snap", name + " " + number + " " + price);}

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                            Toast.makeText(context, "Data Hai", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Nahi hai", Toast.LENGTH_SHORT).show();
                        }
//                        if (dataSnapshot.hasChild("AKN-341")) {
//                            String name = dataSnapshot.child("CarName").getValue().toString();
//                            String company = dataSnapshot.child("Company").getValue().toString();
//                            String number = dataSnapshot.child("NumPlate").getValue().toString();
//                            String price = dataSnapshot.child("NetPrice").getValue().toString();
//
//                            Intent intent = new Intent(MainActivity.this, StepTwo.class);
//                            intent.putExtra("name", name);
//                            intent.putExtra("company", company);
//                            intent.putExtra("number", number);
//                            intent.putExtra("price", price);
//                            startActivity(intent);
//                            Log.i("snap", name + " " + number + " " + price);
//                        } else {
//                            Toast.makeText(MainActivity.this, "Record Not Found", Toast.LENGTH_SHORT).show();
//                        }

                    }
//                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //handle databaseError
                    }
                });

    }

}
