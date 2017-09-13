package com.mkdika.optimizedjava.loops;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Maikel Chandika <mkdika@gmail.com>
 */
public class TestLoop1 {

    public static void main(String[] args) {
        List<Member> goldMembers = createDummyData(1000);

        Benefit benefit = new Benefit(25d);
        
         /*
            approach-1, using for-each-loop
        */
//        for (Member m : goldMembers) {
//            m.setBenefit(benefit);
//        }      

        /*
            approach-2, using Java8 Stream API
        */
        goldMembers.forEach(m -> m.setBenefit(benefit));
        goldMembers.forEach(System.out::println);
        

         /*
            approach-3, using index-loop
         */
//        for (int i=0;i<goldMembers.size();i++) {
//            goldMembers.get(i).setBenefit(benefit);
//        }        
    }

    private static List<Member> createDummyData(int n) {
        List<Member> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new Member(generateMD5()));
        }
        return list;
    }

    private static String generateMD5() {
        UUID idOne = UUID.randomUUID();
        return idOne.toString();
    }
}

class Member {
    private String name;
    private Benefit benefit;

    public Member(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Benefit getBenefit() {
        return benefit;
    }

    public void setBenefit(Benefit benefit) {
        this.benefit = benefit;
    }

    @Override
    public String toString() {
        return name+":"+benefit.getDiscount();
    }        
}

class Benefit {
    private double discount;

    public Benefit(double discount) {
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}