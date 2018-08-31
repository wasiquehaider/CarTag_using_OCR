package com.example.wasique.imagetotext;

/**
 * Created by Wasique on 5/6/2018.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CarInfo {

        @SerializedName(" CPLCclearance")
        @Expose
        private Integer cPLCclearance;
        @SerializedName("CarName")
        @Expose
        private String carName;
        @SerializedName("Company")
        @Expose
        private String company;
        @SerializedName("Make")
        @Expose
        private String make;
        @SerializedName("Model")
        @Expose
        private Integer model;
        @SerializedName("NetPrice")
        @Expose
        private Integer netPrice;
        @SerializedName("NumPlate")
        @Expose
        private String numPlate;
        @SerializedName("Owner")
        @Expose
        private String owner;
        @SerializedName("RegDate")
        @Expose
        private String regDate;
        @SerializedName("RegNo")
        @Expose
        private Integer regNo;
        @SerializedName("SecClearance")
        @Expose
        private Integer secClearance;
        @SerializedName("TaxClearance")
        @Expose
        private Integer taxClearance;

        public Integer getCPLCclearance() {
            return cPLCclearance;
        }

        public void setCPLCclearance(Integer cPLCclearance) {
            this.cPLCclearance = cPLCclearance;
        }

        public String getCarName() {
            return carName;
        }

        public void setCarName(String carName) {
            this.carName = carName;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getMake() {
            return make;
        }

        public void setMake(String make) {
            this.make = make;
        }

        public Integer getModel() {
            return model;
        }

        public void setModel(Integer model) {
            this.model = model;
        }

        public Integer getNetPrice() {
            return netPrice;
        }

        public void setNetPrice(Integer netPrice) {
            this.netPrice = netPrice;
        }

        public String getNumPlate() {
            return numPlate;
        }

        public void setNumPlate(String numPlate) {
            this.numPlate = numPlate;
        }

        public String getOwner() {
            return owner;
        }

        public void setOwner(String owner) {
            this.owner = owner;
        }

        public String getRegDate() {
            return regDate;
        }

        public void setRegDate(String regDate) {
            this.regDate = regDate;
        }

        public Integer getRegNo() {
            return regNo;
        }

        public void setRegNo(Integer regNo) {
            this.regNo = regNo;
        }

        public Integer getSecClearance() {
            return secClearance;
        }

        public void setSecClearance(Integer secClearance) {
            this.secClearance = secClearance;
        }

        public Integer getTaxClearance() {
            return taxClearance;
        }

        public void setTaxClearance(Integer taxClearance) {
            this.taxClearance = taxClearance;
        }
    }


