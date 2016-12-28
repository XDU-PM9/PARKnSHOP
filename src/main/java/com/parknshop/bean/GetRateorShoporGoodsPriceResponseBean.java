package com.parknshop.bean;

/**
 * Created by Cong on 2016/12/28.
 */
public class GetRateorShoporGoodsPriceResponseBean {

    /**
     * error : true
     * rateorPrice : 0.2
     */

    private boolean error;
    private double rateorPrice;

    public boolean getError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public double getRateorPrice() {
        return rateorPrice;
    }

    public void setRateorPrice(double rateorPrice) {
        this.rateorPrice = rateorPrice;
    }
}
