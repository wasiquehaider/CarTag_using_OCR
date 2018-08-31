package com.example.wasique.imagetotext;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.hendrix.pdfmyxml.PdfDocument;
import com.hendrix.pdfmyxml.viewRenderer.AbstractViewRenderer;

import java.io.File;

public class ThirdStep extends AppCompatActivity {

    String name, company, cplc, make, model, owner, regDate, numplate, security, tax, rating, price, mileage, trim, variant;

    TextView companyName,carName,Txt_TotalPrice,Txt_TotalMileage,Txt_Rating;
    ImageButton btnGeneratePDF, btnReloadApp;
    ImageView img_Tax,img_CPLC,img_Security;
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_step);

        carName = (TextView)findViewById(R.id.carName);
        companyName= (TextView)findViewById(R.id.companyName);
        Txt_TotalPrice = (TextView)findViewById(R.id.Txt_TotalPrice);
        Txt_TotalMileage = (TextView)findViewById(R.id.Txt_TotalMileage);
        Txt_Rating = (TextView)findViewById(R.id.Txt_Rating);

        btnGeneratePDF = (ImageButton) findViewById(R.id.btnGeneratePDF);
        btnReloadApp= (ImageButton) findViewById(R.id.btnReloadApp);


        btnGeneratePDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createPDF();
            }
        });

        btnReloadApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callIntent();
            }
        });





        name = getIntent().getStringExtra("name");
        company = getIntent().getStringExtra("company");
        cplc = getIntent().getStringExtra("cplc");
        make = getIntent().getStringExtra("make");
        model= getIntent().getStringExtra("model");
        owner = getIntent().getStringExtra("owner");
        regDate = getIntent().getStringExtra("regDate");
        numplate = getIntent().getStringExtra("numplate");
        security = getIntent().getStringExtra("security");
        tax = getIntent().getStringExtra("tax");
        rating = getIntent().getStringExtra("getRating");
        price = getIntent().getStringExtra("getUpdatedPrice");
        mileage = getIntent().getStringExtra("mileage");
        trim = getIntent().getStringExtra("trim");
        variant = getIntent().getStringExtra("variant");

        carName.setText(name);
        companyName.setText(company);
        Txt_TotalPrice.setText("PKR "+price);
        Txt_TotalMileage.setText("Mileage ->" + mileage);
        Txt_Rating.setText(rating + " / 10" );

        img_Tax = (ImageView)findViewById(R.id.img_Tax);
        img_CPLC = (ImageView)findViewById(R.id.img_CPLC);
        img_Security = (ImageView)findViewById(R.id.img_Security);

        if(tax.toString().equals("0")){
            img_Tax.setImageResource(R.drawable.icon_cancel);
        }
        if(tax.toString().equals("1")){
            img_Tax.setImageResource(R.drawable.icon_ok);
        }if(cplc.toString().equals("0")){
            img_CPLC.setImageResource(R.drawable.icon_cancel);
        }
        if(cplc.toString().equals("1")){
            img_CPLC.setImageResource(R.drawable.icon_ok);
        }if(security.toString().equals("0")){
            img_Security.setImageResource(R.drawable.icon_cancel);
        }
        if(security.toString().equals("1")){
            img_Security.setImageResource(R.drawable.icon_ok);
        }


    }

    private void createPDF(){

        AbstractViewRenderer page = new AbstractViewRenderer(this, R.layout.layout_pdf) {
//            private String _text;
//
//            public void setText(String text) {
//                _text = text;
//            }

            @Override
            protected void initView(View view) {
                TextView textView_car,textView_company,textView_updatedPrice,
                        textView_cplc,textView_make,textView_model,textView_owner,
                        textView_regDate,textView_numplate,textView_security,
                        textView_tax,textView_rating,textView_mileage,textView_trim,
                        textView_variant;

                textView_car = (TextView) view.findViewById(R.id.textView_car);
                textView_company = (TextView) view.findViewById(R.id.textView_company);
                textView_updatedPrice = (TextView) view.findViewById(R.id.textView_updatedPrice);
                textView_cplc = (TextView) view.findViewById(R.id.textView_cplc);
                textView_make= (TextView) view.findViewById(R.id.textView_make);
                textView_model = (TextView) view.findViewById(R.id.textView_model);
                textView_owner= (TextView) view.findViewById(R.id.textView_owner);
                textView_regDate= (TextView) view.findViewById(R.id.textView_regDate);
                textView_numplate = (TextView) view.findViewById(R.id.textView_numplate);
                textView_security = (TextView) view.findViewById(R.id.textView_security);
                textView_tax = (TextView) view.findViewById(R.id.textView_tax);
                textView_rating = (TextView) view.findViewById(R.id.textView_rating);
                textView_mileage = (TextView) view.findViewById(R.id.textView_mileage);
                textView_trim = (TextView) view.findViewById(R.id.textView_trim);
                textView_variant = (TextView) view.findViewById(R.id.textView_variant);



                textView_car.setText("Car Name : " + name);
                textView_company.setText("Company Name :" + company);
                textView_updatedPrice.setText("Car Price : PKR " + price);
                textView_make.setText("Car Make : " + make);
                textView_model.setText("Car Model : " + model);
                textView_owner.setText("Car Owner : " + owner);
                textView_regDate.setText("Car Registration Date : " + regDate);
                textView_numplate.setText("Car Number Plate : " + numplate);

//                textView_tax.setText("Tax Clearance : " + tax);
//                textView_cplc.setText("CPLC Clearance : " + cplc);
//                textView_cplc.setText("Security Clearance : " + security);

                if(security.toString().equals("1")) {
                    textView_security.setText("Security Clearance : " + "Yes");
                }else if(security.toString().equals("0")){
                    textView_security.setText("Security Clearance : " + "No");
                }
                if(tax.toString().equals("1")) {
                    textView_tax.setText("Tax Clearance : " + "Yes");
                }else if(tax.toString().equals("0")){
                    textView_tax.setText("Tax Clearance : " + "No");
                }
                if(cplc.toString().equals("1")) {
                    textView_cplc.setText("CPLC Clearance : " + "Yes");
                }else if(cplc.toString().equals("0")){
                    textView_cplc.setText("CPLC Clearance : " + "No");
                }
                textView_rating.setText("Car Condition : " + rating + " / 10");
                textView_mileage.setText("Car Mileage : " + mileage);
                textView_trim.setText("Trim : " + trim);
                textView_variant.setText("Car Variant : " + variant);



            }
        };

// you can reuse the bitmap if you want
        page.setReuseBitmap(true);

        PdfDocument doc = new PdfDocument(this);

// add as many pages as you have
        doc.addPage(page);

        doc.setRenderWidth(2115);
        doc.setRenderHeight(1500);
        doc.setOrientation(PdfDocument.A4_MODE.LANDSCAPE);
        doc.setProgressTitle(R.string.please_Wait);
        doc.setProgressMessage(R.string.creating);
        doc.setFileName("CarReport");
        doc.setSaveDirectory(this.getExternalFilesDir(null));
        doc.setInflateOnMainThread(false);
        doc.setListener(new PdfDocument.Callback() {
            @Override
            public void onComplete(File file) {

                Log.i(PdfDocument.TAG_PDF_MY_XML, "Report Generation Complete");
            }

            @Override
            public void onError(Exception e) {
                Log.i(PdfDocument.TAG_PDF_MY_XML, "Error");
            }
        });

        doc.createPdf(this);
    }

    public void callIntent(){
        Intent intent = new Intent(ThirdStep.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
