package com.example.contactapp.demo.ServiceApi;


import com.example.contactapp.demo.jpa.Department;
import com.example.contactapp.demo.jpa.Site;
import com.example.contactapp.demo.repository.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class SiteService {
    @Autowired
    private SiteRepository repository;

    public List<Site> getAllSites() {
        return repository.findAll();
    }

    public Site getSiteById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Site saveSite(Site site) {
        return repository.save(site);
    }

    public String deleteSite(Long id) {
        repository.deleteById(id);
        return "Site removed || " + id;
    }

    public Site updateSite(Site site) {
        Site existingSite = repository.findById(site.getIdSite()).orElse(null);
        if (existingSite != null) {
            existingSite.setCity(site.getCity());
            return repository.save(existingSite);
        } else {
            return null;
        }
    }




}
