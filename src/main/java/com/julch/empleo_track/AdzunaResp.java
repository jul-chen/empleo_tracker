package com.julch.empleo_track;

import java.util.List;

public record AdzunaResp(List<JobOfferData> results ) {
    public record JobOfferData(
            String id,
            String title,
            String description,
            String contract_time,
            Category category,
            String redirect_url,
            Company company
    ){}

    public record Company(String company_name){}
    public record Category(String category_name){}


}
