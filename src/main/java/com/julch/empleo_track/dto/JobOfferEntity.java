package com.julch.empleo_track.dto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ofertas")
public class JobOfferEntity {
    @Id
    private String id;

    private String title;

    @Column(length = 1500)
    private String redirect_url;

    private String company;

    public JobOfferEntity(String id, String title, String redirect_url, String company) {
        this.id = id;
        this.title = title;
        this.redirect_url = redirect_url;
        this.company = company;
    }

    public JobOfferEntity() {}

    public String getId() { return id;}

    public String getTitle() {
        return title;
    }


    public String getRedirect_url() {
        return redirect_url;
    }

    public String getCompany() {
        return company;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public void setRedirect_url(String redirect_url) {
        this.redirect_url = redirect_url;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
