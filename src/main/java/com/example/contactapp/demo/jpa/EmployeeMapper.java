package com.example.contactapp.demo.jpa;

public class EmployeeMapper {
    public static EmployeeDTO toDTO(Employee employee) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setFirstname(employee.getFirstname());
        dto.setPhone(employee.getPhone());
        dto.setMail(employee.getMail());
        dto.setIdDepartment(employee.getDepartment() != null ? employee.getDepartment().getIdDepartment() : null);
        dto.setIdSite(employee.getSite() != null ? employee.getSite().getIdSite() : null);
        return dto;
    }
}
