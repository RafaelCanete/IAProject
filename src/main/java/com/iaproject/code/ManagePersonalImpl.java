package com.iaproject.code;

import com.iaproject.model.SalesMan;
import com.iaproject.model.SocialPerformanceRecord;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.push;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

public class ManagePersonalImpl implements ManagePersonal {

    private final MongoCollection<Document> salesmanCollection;

    public ManagePersonalImpl() {
        MongoDatabase database = MongoConnection.getDatabase();
        salesmanCollection = database.getCollection("salesman");
    }


    @Override
    public void createSalesMan(SalesMan salesman) {
        Document doc = new Document("sid", salesman.getId())
                .append("firstname", salesman.getFirstname())
                .append("lastname", salesman.getLastname())
                .append("socialPerfomanceRecords", salesman.getSocialPerformanceRecords());
        salesmanCollection.insertOne(doc);
        System.out.println("Added salesman: " + salesman.getFirstname() + " " + salesman.getLastname());
    }

    @Override
    public void addSocialPerformanceRecord(SocialPerformanceRecord record, SalesMan salesMan) {
        Document recordDoc = new Document("month", record.getMonth())
                .append("socialScore", record.getSocialScore());

        salesmanCollection.updateOne(
                eq("sid", salesMan.getId()),
                push("socialPerfomanceRecords", recordDoc)
        );

        System.out.println("Added performance record for salesman id: " + salesMan.getId());
    }

    @Override
    public SalesMan readSalesMan(int sid) {
        Document doc = salesmanCollection.find(new Document("sid", sid)).first();
        if (doc == null) {
            System.out.println("No salesman found with id: " + sid);
            return null;
        }

        List<Document> recordDocs = doc.getList("socialPerfomanceRecords", Document.class, new ArrayList<>());

        List<SocialPerformanceRecord> records = new ArrayList<>();
        for (Document rec : recordDocs) {
            records.add(new SocialPerformanceRecord(
                    rec.getString("month"),
                    rec.getInteger("socialScore")
            ));
        }

        return new SalesMan(
                doc.getString("firstname"),
                doc.getString("lastname"),
                sid,
                records
        );
    }


    @Override
    public List<SalesMan> readAllSalesMen() {
            List<SalesMan> salesmen = new ArrayList<>();

            for (Document doc : salesmanCollection.find()) {

                List<SocialPerformanceRecord> records = new ArrayList<>();
                List<Document> recordDocs = doc.getList("socialPerfomanceRecords", Document.class, new ArrayList<>());

                for (Document rec : recordDocs) {
                    records.add(new SocialPerformanceRecord(
                            rec.getString("month"),
                            rec.getInteger("socialScore")
                    ));
                }

                SalesMan salesman = new SalesMan(
                        doc.getString("firstname"),
                        doc.getString("lastname"),
                        doc.getInteger("sid"),
                        records
                );

                salesmen.add(salesman);
            }

            return salesmen;
        }


    @Override
    public List<SocialPerformanceRecord> readSocialPerformanceRecord(SalesMan salesMan) {
        List<SocialPerformanceRecord> records = new ArrayList<>();

        Document doc = salesmanCollection.find(eq("sid", salesMan.getId())).first();

        if (doc == null) {
            System.out.println("No salesman found with id: " + salesMan.getId());
            return records;
        }

        List<Document> recordDocs = doc.getList("socialPerfomanceRecords", Document.class, new ArrayList<>());

        for (Document rec : recordDocs) {
            records.add(new SocialPerformanceRecord(
                    rec.getString("month"),
                    rec.getInteger("socialScore")
            ));
        }
        return records;
    }

}
