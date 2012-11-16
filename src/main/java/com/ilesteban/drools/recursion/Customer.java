/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilesteban.drools.recursion;

import java.util.ArrayList;
import java.util.List;
import org.drools.definition.type.PropertyReactive;

/**
 *
 * @author esteban
 */
@PropertyReactive
public class Customer {
    private String name;
    private int seniority;
    private double discount;
    private int childrenCount;
    private List<String> discountReasons = new ArrayList<String>();

    public Customer() {
    }

    public Customer(String name) {
        this.name = name;
    }

    public Customer(String name, int seniority) {
        this.name = name;
        this.seniority = seniority;
    }
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeniority() {
        return seniority;
    }

    public void setSeniority(int seniority) {
        this.seniority = seniority;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public List<String> getDiscountReasons() {
        return discountReasons;
    }

    public void setDiscountReasons(List<String> discountReasons) {
        this.discountReasons = discountReasons;
    }
    
    public void addDiscountReason(String reason){
        this.discountReasons.add(reason);
    }

    public int getChildrenCount() {
        return childrenCount;
    }

    public void setChildrenCount(int childrenCount) {
        this.childrenCount = childrenCount;
    }
    
}
