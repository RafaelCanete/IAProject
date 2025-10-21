package com.iaproject;

import com.iaproject.code.ManagePersonalImpl;
import com.iaproject.model.SalesMan;
import com.iaproject.model.SocialPerformanceRecord;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ManagePersonalImpl impl = new ManagePersonalImpl();

        SalesMan john = new SalesMan("John", "Doe", 1, new ArrayList<>(), "", "Sales", "");
        impl.createSalesMan(john);

        SocialPerformanceRecord johnRecord1 = new SocialPerformanceRecord("April", 10);
        impl.addSocialPerformanceRecord(johnRecord1, john);

        SocialPerformanceRecord johnRecord2 = new SocialPerformanceRecord("May", 15);
        impl.addSocialPerformanceRecord(johnRecord2, john);

        SalesMan chantal = new SalesMan("Chantal", "Banks", 2, new ArrayList<>(), "HR Senior Consultant", "HR", "Michael Moore");
        impl.createSalesMan(chantal);

        SocialPerformanceRecord chantalRecord1 = new SocialPerformanceRecord("April", 10);
        impl.addSocialPerformanceRecord(chantalRecord1, chantal);


        System.out.println("Salesman und Performance Records erfolgreich angelegt!\n");

        SalesMan found = impl.readSalesMan(1);
        if (found != null) {
            System.out.println("Gefundener Salesman:");
            System.out.println("  Name: " + found.getFirstname() + " " + found.getLastname());
            System.out.println("  ID: " + found.getId());
        }


        List<SalesMan> all = impl.readAllSalesMen();
        System.out.println("\n Alle Salesmen in der Datenbank:");
        for (SalesMan s : all) {
            System.out.println("  " + s.getFirstname() + " " + s.getLastname() + " (ID: " + s.getId() + ")");
        }

        List<SocialPerformanceRecord> records = impl.readSocialPerformanceRecord(john);
        System.out.println("\n Performance Records für " + john.getFirstname() + ":");
        for (SocialPerformanceRecord rec : records) {
            System.out.println("  Monat: " + rec.getMonth() + ", Score: " + rec.getSocialScore());
        }

        System.out.println("\n Programm erfolgreich ausgeführt!");

    }
}
