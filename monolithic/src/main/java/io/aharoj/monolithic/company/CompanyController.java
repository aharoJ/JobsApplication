package io.aharoj.monolithic.company;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/companies")
public class CompanyController {
  private CompanyService companyService;

  // EVC
  public CompanyController(CompanyService companyService) {
    this.companyService = companyService;
  }

  @GetMapping
  public ResponseEntity<List<Company>> getAllCompanies() {
    return new ResponseEntity<>(companyService.getAllCompanies(), HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<String> updateCompany(@PathVariable Long id, @RequestBody Company company) {
    companyService.updateCompany(id, company);
    return new ResponseEntity<>("Company updated sucessfully", HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<String> addCompany(@RequestBody Company company) {
    companyService.createCompany(company);
    return new ResponseEntity<>("Company added sucessfully", HttpStatus.CREATED);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> removeCompany(@PathVariable Long id) {
    if (companyService.deleteCompany(id)) {
      return new ResponseEntity<>("Company deleted sucessfully", HttpStatus.OK);
    }
    return new ResponseEntity<>("Company not found", HttpStatus.NOT_FOUND);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
    Company company = companyService.getCompanyById(id);
    if (company != null) {
      return new ResponseEntity<>(company, HttpStatus.OK);
    }
    return new ResponseEntity<>(company, HttpStatus.NOT_FOUND);
  }

}
