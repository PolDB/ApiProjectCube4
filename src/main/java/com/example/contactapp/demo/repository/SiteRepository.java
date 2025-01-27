package com.example.contactapp.demo.repository;

import com.example.contactapp.demo.jpa.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteRepository extends JpaRepository<Site, Long> {
}