package io.aharoj.jobapplication.company;

import java.util.List;

public interface CompanyService {
  List<Company> getAllCompanies();

  boolean updateCompany(Long id, Company company);

  void createCompany(Company company);

  void deleteCompany(Long id);


}
