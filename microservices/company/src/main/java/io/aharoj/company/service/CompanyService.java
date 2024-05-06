package io.aharoj.company.service;

import java.util.List;

import io.aharoj.company.model.Company;

public interface CompanyService {
  List<Company> getAllCompanies();

  boolean updateCompany(Long id, Company company);

  void createCompany(Company company);

  boolean deleteCompany(Long id);

  Company getCompanyById(Long id);

}
