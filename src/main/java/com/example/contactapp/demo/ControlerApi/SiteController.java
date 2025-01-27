package com.example.contactapp.demo.ControlerApi;

import com.example.contactapp.demo.ServiceApi.EmployeeService;
import com.example.contactapp.demo.ServiceApi.SiteService;
import com.example.contactapp.demo.jpa.Department;
import com.example.contactapp.demo.jpa.Employee;
import com.example.contactapp.demo.jpa.Site;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://10.0.2.2")  // Autoriser CORS pour ce contrôleur;;j
@RequestMapping("/api/site")
public class SiteController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private SiteService service;

    @GetMapping
    public List<Site> getAllSites() {
        return service.getAllSites();
    }

    @GetMapping("/{id}")
    public Site getSiteById(@PathVariable Long id) {
        return service.getSiteById(id);
    }

    @PostMapping
    public Site addSite(@RequestBody Site site) {
        return service.saveSite(site);
    }

    @PutMapping("/{id}")
    public Site updateSite(@PathVariable Long id, @RequestBody Site site) {
        site.setIdSite(id);
        return service.updateSite(site);
    }


    @DeleteMapping("/{id}")
    public String deleteSite(@PathVariable Long id) {
        return service.deleteSite(id);
    }



    //ajout de la méthode pour récupérer des employés en fonction du site
    @GetMapping("/{id}/employees")
    public List<Employee> getEmployeesBySite(@PathVariable Long id) {
        return employeeService.getEmployeesBySite(id);
    }
}