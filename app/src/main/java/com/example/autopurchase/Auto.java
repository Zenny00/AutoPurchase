package com.example.autopurchase;

public class Auto {
    //Constants
    static final double STATE_TAX = 0.07;
    static final double INTEREST_RATE = 0.09;

    //Private data members
    private double mPrice;
    private double mDownPayment;
    private int mLoanTerm;

    //Setters
    public void setPrice(double price)
    {
        mPrice = price;
    }

    public void setDownPayment(double down)
    {
        mDownPayment = down;
    }

    public void setLoanTerm(String term)
    {
        if (term.contains("2"))
            mLoanTerm = 2;
        else if (term.contains("3"))
            mLoanTerm = 3;
        else
            mLoanTerm = 4;
    }

    //Getters
    public double getPrice()
    {
        return mPrice;
    }

    public double getDownPayment()
    {
        return mDownPayment;
    }

    public int getLoanTerm()
    {
        return mLoanTerm;
    }

    //Calculators
    public double taxAmount()
    {
        return mPrice * STATE_TAX;
    }

    public double totalCost()
    {
        return mPrice + taxAmount();
    }

    public double borrowedAmount()
    {
        return totalCost() - mDownPayment;
    }

    public double interestAmount()
    {
        return borrowedAmount() * INTEREST_RATE;
    }

    public double monthlyPayment()
    {
        return borrowedAmount() / (mLoanTerm * 12);
    }
}
