package com.julch.empleo_track.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.List;

public record AdzunaResponse(List<JobOfferData> results ) {
    public record JobOfferData(
            String id,
            String title,
            String description,
            String contract_time,
            AdzunaResponse.Category category,
            String redirect_url,
            AdzunaResponse.Company company
    ){}
    public record Company(String company_name){}
    public record Category(String category_name){}
}
