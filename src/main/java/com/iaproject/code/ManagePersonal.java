package com.iaproject.code;

import com.iaproject.model.SalesMan;
import com.iaproject.model.SocialPerformanceRecord;

import java.util.List;

public interface ManagePersonal {
    public void createSalesMan( SalesMan record );

    public void deleteSalesMan( int sid );

    public void addSocialPerformanceRecord(SocialPerformanceRecord record , SalesMan salesMan );
    // Remark: an SocialPerformanceRecord corresponds to part B of a bonus sheet

    public SalesMan readSalesMan(int sid );

    public List<SalesMan> readAllSalesMen();

    public List<SocialPerformanceRecord> readSocialPerformanceRecord(SalesMan salesMan );
    // Remark: How do you integrate the year?
}
