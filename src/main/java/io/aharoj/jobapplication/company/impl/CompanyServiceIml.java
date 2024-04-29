package io.aharoj.jobapplication.company.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import io.aharoj.jobapplication.company.Company;
import io.aharoj.jobapplication.company.CompanyRepository;
import io.aharoj.jobapplication.company.CompanyService;

@Service
public class CompanyServiceIml implements CompanyService {
  private CompanyRepository companyRepository;

  // EVC
  public CompanyServiceIml(CompanyRepository companyRepository) {
    this.companyRepository = companyRepository;
  }

  @Override
  public List<Company> getAllCompanies() {
    return companyRepository.findAll();
  }

  @Override
  public boolean updateCompany(Long id, Company company) {
    Optional<Company> companyOption = companyRepository.findById(id);
    if (companyOption.isPresent()) {
      Company companyToUpdate = companyOption.get();
      companyToUpdate.setDescription(company.getDescription());
      companyToUpdate.setName(company.getName());
      companyToUpdate.setJobs(company.getJobs());
      companyRepository.save(companyToUpdate);
      return true;
    } else {
      return false;
    }
  }

  @Override
  public void createCompany(Company company) {
    companyRepository.save(company);
  }

  @Override
  public boolean deleteCompany(Long id) {
    if (!companyRepository.existsById(id)) {
      return false;
    }
    companyRepository.deleteById(id);
    return true;
  }

  @Override
  public Company getCompanyById(Long id) {
    return companyRepository.findById(id).orElse(null);
  }

}
