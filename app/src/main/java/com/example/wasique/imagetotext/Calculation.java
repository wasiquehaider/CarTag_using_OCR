package com.example.wasique.imagetotext;

/**
 * Created by Wasique on 5/7/2018.
 */

public class Calculation {
    int rating,count = 0;

    boolean roof,bonnet,bumperfront,bumperrear,fenderright,fenderleft,doorright,doorleft;

    public Calculation(boolean roof, boolean bonnet, boolean bumperfront, boolean bumperrear, boolean fenderright, boolean fenderleft, boolean doorright, boolean doorleft) {
        this.roof = roof;
        this.bonnet = bonnet;
        this.bumperfront = bumperfront;
        this.bumperrear = bumperrear;
        this.fenderright = fenderright;
        this.fenderleft = fenderleft;
        this.doorright = doorright;
        this.doorleft = doorleft;
    }

    public int calculateRating(){

        count =  getCount();
//        count += 2;
        count = 10 - count;

        return count;

    }

    public long getCarNetValue(long getCarPrice) {

        long getTotalPrice = 0;

        if(roof) getTotalPrice += Constant.roof;
        if(bonnet) getTotalPrice += Constant.bonnet;
        if(bumperfront) getTotalPrice += Constant.bumperfront;
        if(bumperrear) getTotalPrice += Constant.bumperrear;
        if(fenderleft) getTotalPrice += Constant.fenderleft;
        if(fenderright) getTotalPrice += Constant.fenderright;
        if(doorleft) getTotalPrice += Constant.doorleft;
        if(doorright) getTotalPrice += Constant.doorright;

        getTotalPrice = getCarPrice - getTotalPrice  ;

        return getTotalPrice;
    }

    public int getCount(){
        if(roof) count++;
        if(bonnet) count++;
        if(bumperfront) count++;
        if(bumperrear) count++;
        if(fenderleft) count++;
        if(fenderright) count++;
        if(doorleft) count++;
        if(doorright) count++;

        return count;
    }
}
