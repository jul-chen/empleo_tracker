package com.julch.empleo_track.repository;

import com.julch.empleo_track.dto.AdzunaResponse;
import com.julch.empleo_track.dto.JobOfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobOfferRepository extends JpaRepository<JobOfferEntity, String> {}
