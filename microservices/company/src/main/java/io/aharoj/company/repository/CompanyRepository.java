package io.aharoj.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.aharoj.company.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}
