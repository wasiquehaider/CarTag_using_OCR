package com.example.wasique.imagetotext;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    static final int request = 1;
    ImageView imageView;
    TextView showTxt;
    ImageButton fetchBtn,btnCamera,btnStepTwo;
    private Bitmap bp;
    String getCarNumber;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Fetching Data From Firebase");
        progressDialog.setMessage("please wait...");

        imageView = (ImageView)findViewById(R.id.imageView);
        showTxt= (TextView) findViewById(R.id.showCarNumber);
        fetchBtn = (ImageButton) findViewById(R.id.fetchBtn);
        btnCamera= (ImageButton)findViewById(R.id.btnCamera);
        btnStepTwo = (ImageButton) findViewById(R.id.btnStepTwo);


        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCamera();
            }
        });
        fetchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTextFromImage();
            }
        });

        btnStepTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (showTxt.getText().equals(null) || showTxt.getText().equals("") || showTxt.getText().equals(" ")){
                    Toast.makeText(MainActivity.this, "Please Re-Scan", Toast.LENGTH_SHORT).show();
                } else {
                    progressDialog.show();
                    firebaseInstances(getCarNumber);
                }

            }
        });


    }

    private void openCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 0);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("codes", String.valueOf(requestCode + "  " + resultCode + " " + data));
        if (resultCode == RESULT_OK) {
            bp = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bp);
        }
    }


    public void getTextFromImage() {

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            //bitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(), id);}
            TextRecognizer textRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();

            if (!textRecognizer.isOperational()) {
                Toast.makeText(getApplicationContext(), "Could Not Read Text", Toast.LENGTH_SHORT).show();
            } else {
                Frame frame = new Frame.Builder().setBitmap(bp).build();
                SparseArray<TextBlock> items = textRecognizer.detect(frame);
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < items.size(); ++i) {
                    TextBlock myItem = items.valueAt(i);
                    stringBuilder.append(myItem.getValue());
                }

                showTxt.setText(stringBuilder);
                if (stringBuilder.length() < 6){
                    Toast.makeText(this, "please re-scan", Toast.LENGTH_SHORT).show();
                } else {
                    getCarNumber = String.valueOf(stringBuilder);
                    if (getCarNumber.contains("-")) {
                    } else {
                        getCarNumber.replaceAll(" ", "");
                        StringBuilder stringBuilder1 = new StringBuilder(getCarNumber);
                        stringBuilder1.insert(3, '-');
                        getCarNumber = String.valueOf(stringBuilder1);
                        Log.i("carNumber", getCarNumber);
                    }
                }
            }
        }
    }

    private void firebaseInstances(final String getCarNumber) {

        ///*****Update Code*******/////
        ////****Author: Arsalan Siddiq*****/////

//        String getCar ="AKN-341";
////                getCarNumber.replace("-", "");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("NumPlates").child(getCarNumber);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                String cplc = String.valueOf(dataSnapshot.child("CPLCclearance").getValue());
                String name = dataSnapshot.child("CarName").getValue().toString();
                String company = dataSnapshot.child("Company").getValue().toString();
                String make = dataSnapshot.child("Make").getValue().toString();
                String model = dataSnapshot.child("Model").getValue().toString();
                String numplate = dataSnapshot.child("NumPlate").getValue().toString();
                String price = dataSnapshot.child("NetPrice").getValue().toString();
                String owner = dataSnapshot.child("Owner").getValue().toString();
                String regDate = dataSnapshot.child("RegDate").getValue().toString();
//              String regNum = dataSnapshot.child("RegNum").getValue().toString();
                String security = dataSnapshot.child("SecClearance").getValue().toString();
                String tax = dataSnapshot.child("TaxClearance").getValue().toString();

                Log.i("dataGet", cplc + " " + name + " " +company + " " + make + " "+ model+ " "+numplate+ " "+
                        price+ " "+owner+ " "+regDate+ " "+security+ " "+tax);

                Intent intent = new Intent(MainActivity.this, StepTwo.class);
                intent.putExtra("name", name);
                intent.putExtra("company", company);
                intent.putExtra("numplate", numplate);
                intent.putExtra("price", price);
                intent.putExtra("cplc", cplc);
                intent.putExtra("make", make);
                intent.putExtra("model", model);
                intent.putExtra("owner", owner);
                intent.putExtra("regDate", regDate);
//              intent.putExtra("regNum", regNum);
                intent.putExtra("security", security);
                intent.putExtra("tax", tax);

                progressDialog.dismiss();
                startActivity(intent);
                finish();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "something went wrong, press again", Toast.LENGTH_SHORT).show();
                Log.w("DataGet", "Failed to read value.", error.toException());
            }
        });






//        final String object1 = new String(getCarNumber);
//        final String object = new String("AKN-341");
//        Log.d("numb", getCarNumber);
////        final FirebaseDatabase database = FirebaseDatabase.getInstance();
//
////        String numbplate = "NumPlates";
//        final DatabaseReference zonesRef = FirebaseDatabase.getInstance().getReference("NumPlates");
//
//
//        zonesRef.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        if (object1.compareTo(object) == 1) {
//                            if (dataSnapshot.hasChild("AKN-341")) {
//                                DatabaseReference zone1Ref = zonesRef.child(String.valueOf("AKN-341"));
//                                zone1Ref.addValueEventListener(new ValueEventListener() {
//                                    @Override
//                                    public void onDataChange(DataSnapshot dataSnapshot) {
//                                        String cplc = String.valueOf(dataSnapshot.child(" CPLCclearance").getValue());
//                                        String name = dataSnapshot.child("CarName").getValue().toString();
//                                        Log.i("name", name);
//                                        String company = dataSnapshot.child("Company").getValue().toString();
//                                        String make = dataSnapshot.child("Make").getValue().toString();
//                                        String model = dataSnapshot.child("Model").getValue().toString();
//                                        String numplate = dataSnapshot.child("NumPlate").getValue().toString();
//                                        String price = dataSnapshot.child("NetPrice").getValue().toString();
//                                        String owner = dataSnapshot.child("Owner").getValue().toString();
//                                        String regDate = dataSnapshot.child("RegDate").getValue().toString();
////                                    String regNum = dataSnapshot.child("RegNum").getValue().toString();
//                                        String security = dataSnapshot.child("SecClearance").getValue().toString();
//                                        String tax = dataSnapshot.child("TaxClearance").getValue().toString();
//
//                                        Intent intent = new Intent(MainActivity.this, StepTwo.class);
//                                        intent.putExtra("name", name);
//                                        intent.putExtra("company", company);
//                                        intent.putExtra("numplate", numplate);
//                                        intent.putExtra("price", price);
//                                        intent.putExtra("cplc", cplc);
//                                        intent.putExtra("make", make);
//                                        intent.putExtra("model", model);
//                                        intent.putExtra("owner", owner);
//                                        intent.putExtra("regDate", regDate);
////                                    intent.putExtra("regNum", regNum);
//                                        intent.putExtra("security", security);
//                                        intent.putExtra("tax", tax);
//
//                                        startActivity(intent);
//
//                                    }
//
//                                    @Override
//                                    public void onCancelled(DatabaseError databaseError) {
//
//                                    }
//                                });
//                                Toast.makeText(MainActivity.this, "Data Hai", Toast.LENGTH_SHORT).show();
//                            } else {
//                                Toast.makeText(MainActivity.this, "Nahi hai", Toast.LENGTH_SHORT).show();
//                            }
//                        } else {
//                            if (dataSnapshot.hasChild("ATY-231")) {
//                                DatabaseReference zone1Ref = zonesRef.child(String.valueOf("ATY-231"));
//                                zone1Ref.addValueEventListener(new ValueEventListener() {
//                                    @Override
//                                    public void onDataChange(DataSnapshot dataSnapshot) {
//                                        String cplc = String.valueOf(dataSnapshot.child(" CPLCclearance").getValue());
//                                        Log.i("cplc", cplc);
//                                        String name = dataSnapshot.child("CarName").getValue().toString();
//                                        String company = dataSnapshot.child("Company").getValue().toString();
//                                        String make = dataSnapshot.child("Make").getValue().toString();
//                                        String model = dataSnapshot.child("Model").getValue().toString();
//                                        String numplate = dataSnapshot.child("NumPlate").getValue().toString();
//                                        String price = dataSnapshot.child("NetPrice").getValue().toString();
//                                        String owner = dataSnapshot.child("Owner").getValue().toString();
//                                        String regDate = dataSnapshot.child("RegDate").getValue().toString();
////                                    String regNum = dataSnapshot.child("RegNum").getValue().toString();
//                                        String security = dataSnapshot.child("SecClearance").getValue().toString();
//                                        String tax = dataSnapshot.child("TaxClearance").getValue().toString();
//
//                                        Intent intent = new Intent(MainActivity.this, StepTwo.class);
//                                        intent.putExtra("name", name);
//                                        intent.putExtra("company", company);
//                                        intent.putExtra("numplate", numplate);
//                                        intent.putExtra("price", price);
//                                        intent.putExtra("cplc", cplc);
//                                        intent.putExtra("make", make);
//                                        intent.putExtra("model", model);
//                                        intent.putExtra("owner", owner);
//                                        intent.putExtra("regDate", regDate);
////                                    intent.putExtra("regNum", regNum);
//                                        intent.putExtra("security", security);
//                                        intent.putExtra("tax", tax);
//
//                                        startActivity(intent);
//
//                                    }
//
//                                    @Override
//                                    public void onCancelled(DatabaseError databaseError) {
//
//                                    }
//                                });
//                                Toast.makeText(MainActivity.this, "Data Hai", Toast.LENGTH_SHORT).show();
//                            } else {
//                                Toast.makeText(MainActivity.this, "Nahi hai", Toast.LENGTH_SHORT).show();
//                            }
//                        }
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

//                    }
//                    }

//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//                        //handle databaseError
//                    }
//                });

    }

}
