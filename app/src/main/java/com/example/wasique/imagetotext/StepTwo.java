package com.example.wasique.imagetotext;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.hendrix.pdfmyxml.PdfDocument;
import com.hendrix.pdfmyxml.viewRenderer.AbstractViewRenderer;

import java.io.File;

public class StepTwo extends AppCompatActivity {

    private TextView carName,companyName;
    private EditText edtTxtmileage,edtTextTrim,edtTxtvariant;
    private CheckBox checkbox_roof,checkbox_bonnet,checkbox_bumperfront,checkbox_bumperrear,checkbox_fenderright,checkbox_fenderleft,
            checkbox_doorright,checkbox_doorleft;
    private ImageButton btnStepThree;

    private int rating;
    private long getPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_two);

//        EditText
        edtTxtmileage = (EditText) findViewById(R.id.edtTxtmileage);
        edtTextTrim= (EditText) findViewById(R.id.edtTextTrim);
        edtTxtvariant = (EditText) findViewById(R.id.edtTxtvariant);

//        CheckBox
        checkbox_roof = (CheckBox) findViewById(R.id.checkbox_roof);
        checkbox_bonnet = (CheckBox) findViewById(R.id.checkbox_bonnet);
        checkbox_bumperfront = (CheckBox) findViewById(R.id.checkbox_bumperfront);
        checkbox_bumperrear = (CheckBox) findViewById(R.id.checkbox_bumperrear);
        checkbox_fenderleft= (CheckBox) findViewById(R.id.checkbox_fenderleft);
        checkbox_fenderright= (CheckBox) findViewById(R.id.checkbox_fenderright);
        checkbox_doorleft = (CheckBox) findViewById(R.id.checkbox_doorleft);
        checkbox_doorright = (CheckBox) findViewById(R.id.checkbox_doorright);

//      TextView
        carName = (TextView) findViewById(R.id.carName);
        companyName= (TextView) findViewById(R.id.companyName);

        btnStepThree= (ImageButton) findViewById(R.id.btnStepThree);

        final String name = getIntent().getStringExtra("name");
        final String company = getIntent().getStringExtra("company");
        final String price = getIntent().getStringExtra("price");
        final String cplc = getIntent().getStringExtra("cplc");
        final String make = getIntent().getStringExtra("make");
        final String model= getIntent().getStringExtra("model");
        final String owner = getIntent().getStringExtra("owner");
        final String regDate = getIntent().getStringExtra("regDate");
        final String numplate = getIntent().getStringExtra("numplate");
        final String security = getIntent().getStringExtra("security");
        final String tax = getIntent().getStringExtra("tax");
//        final String mileage = edtTxtmileage.getText().toString();
//        final String trim = edtTextTrim.getText().toString();
//        final String variant = edtTxtvariant.getText().toString();

        btnStepThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calculation calculation = new Calculation(checkbox_roof.isChecked(), checkbox_bonnet.isChecked(),
                        checkbox_bumperfront.isChecked(),checkbox_bumperrear.isChecked(),checkbox_fenderright.isChecked(),
                        checkbox_fenderleft.isChecked(),checkbox_doorright.isChecked(),checkbox_doorleft.isChecked());
                rating = calculation.calculateRating();
                getPrice = calculation.getCarNetValue(Long.parseLong(price));

                Intent intent = new Intent(StepTwo.this,ThirdStep.class);
                intent.putExtra("name", name);
                intent.putExtra("company", company);
                intent.putExtra("numplate", numplate);
                intent.putExtra("cplc", cplc);
                intent.putExtra("make", make);
                intent.putExtra("model", model);
                intent.putExtra("owner", owner);
                intent.putExtra("regDate", regDate);
//                                    intent.putExtra("regNum", regNum);
                intent.putExtra("security", security);
                intent.putExtra("tax", tax);
                intent.putExtra("getUpdatedPrice",String.valueOf(getPrice ));
                intent.putExtra("getRating" ,String.valueOf(rating));
                intent.putExtra("mileage" ,edtTxtmileage.getText().toString());
                intent.putExtra("trim" ,edtTextTrim.getText().toString());
                intent.putExtra("variant" ,edtTxtvariant.getText().toString());

                if(edtTxtmileage.getText().toString().trim().equals("") || edtTxtmileage.getText().toString().trim().equals("") ||
                        edtTxtmileage.getText().toString().trim().equals("")){
                    Toast.makeText(StepTwo.this, "Please Fill All Fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    startActivity(intent);
                }
//                createPDF();
            }
        });

//        String name = getIntent().getStringExtra("name");
//        String company = getIntent().getStringExtra("company");
//        String price = getIntent().getStringExtra("price");
//        String cplc = getIntent().getStringExtra("cplc");
//        String make = getIntent().getStringExtra("make");
//        String model= getIntent().getStringExtra("model");
//        String owner = getIntent().getStringExtra("owner");
//        String regDate = getIntent().getStringExtra("regDate");
//        String numplate = getIntent().getStringExtra("numplate");
//        String security = getIntent().getStringExtra("security");
//        String tax = getIntent().getStringExtra("tax");

        carName.setText(name);
        companyName.setText(company);
    }

//    private void createPDF(){
//
//        AbstractViewRenderer page = new AbstractViewRenderer(this, R.layout.layout_pdf) {
////            private String _text;
////
////            public void setText(String text) {
////                _text = text;
////            }
//
//            @Override
//            protected void initView(View view) {
//                TextView textView_carNumber = (TextView)view.findViewById(R.id.textView_carNumber);
//                TextView textView_price = (TextView)view.findViewById(R.id.textView_price);
//                textView_carNumber.setText("AKN-341");
//                textView_price.setText("1000000");
//            }
//        };
//
//// you can reuse the bitmap if you want
//        page.setReuseBitmap(true);
//
//
//
//
//        PdfDocument doc            = new PdfDocument(this);
//
//// add as many pages as you have
//        doc.addPage(page);
//
//        doc.setRenderWidth(2115);
//        doc.setRenderHeight(1500);
//        doc.setOrientation(PdfDocument.A4_MODE.LANDSCAPE);
//        doc.setProgressTitle(R.string.please_Wait);
//        doc.setProgressMessage(R.string.creating);
//        doc.setFileName("carpdf");
//        doc.setSaveDirectory(this.getExternalFilesDir(null));
//        doc.setInflateOnMainThread(false);
//        doc.setListener(new PdfDocument.Callback() {
//            @Override
//            public void onComplete(File file) {
//                Log.i(PdfDocument.TAG_PDF_MY_XML, "Complete");
//            }
//
//            @Override
//            public void onError(Exception e) {
//                Log.i(PdfDocument.TAG_PDF_MY_XML, "Error");
//            }
//        });
//
//        doc.createPdf(this);
//    }
}
