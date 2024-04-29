# JOBS

## All HTTP Codes

> check source for for the bottom class

```java
HttpStatus
```

## src

- [JSON LINTER](https://jsonlint.com/)

## Entity

old way of doing

```java
return new Job(0L, "Job not found", "Job not found", "0", "0", "0");
```

new way of doung it

```java
return new ResponseEntity<>(job, HttpStatus.NOT_FOUND);
```

---

> Method `HttpEntity` is a generic class that represents an HTTP response or request entity, consisting of headers and body.

return `new`

```java
public HttpEntity<String> createJob(@RequestBody Job job) {
  return new ResponseEntity<>("Job added succesfully", HttpStatus.OK);
}
```

> Method `ResponseEntity` is a generic class that represents an HTTP response, consisting of headers, body, and status.

no `new` return

```java
public ResponseEntity<List<Job>> findAll() {
  return ResponseEntity.ok(jobService.findAll());
}
```

## Delete Mapping

Two different ways of doing a delete

```java
@DeleteMapping("/jobs/{id}")
public HttpEntity<String> deleteJob(@PathVariable Long id) {
  boolean deleted = jobService.deleteJobById(id);
  if (deleted) {
    return new ResponseEntity<>("Job Deleted", HttpStatus.OK);
  }
  return new ResponseEntity<>("Job Not Found", HttpStatus.NOT_FOUND);
}
```

```java
@DeleteMapping("/jobs/{id}")
public HttpEntity<String> deleteJobById(@PathVariable Long id) {
  if (jobService.deleteJobById(id)) {
    return new ResponseEntity<>("Job deleted succesfully", HttpStatus.OK);
  }
  return new ResponseEntity<>("Job not found", HttpStatus.NOT_FOUND);
}
```

## @Requestmapping

### Class

```java
@RequestMapping("/jobs")
```

like this, all methods derived its url instantly from the class

### Method

```java
@RequestMapping(value = "/jobs/{id}", method = RequestMethod.GET)
```

#### Difference

```java
1. @GetMapping("/jobs/{id}")
2. @RequestMapping(value = "/jobs/{id}", method = RequestMethod.GET)
```

## JPA

### H2

```xml
spring.h2.console.enabled=true
spring.datasource.url=jdbc:h2:mem:test
```

### @Entity

it tells JPA that this class is an entity and it should be mapped to a database table.

### @Table(name="job_table")

it tells JPA that this entity is mapped to a table named job_table.

### jpa requiered a DVC

- default constructor is required by JPA

### Required a Repository

- JPA requires a repository to interact with the database.
- The repository is an interface that extends JpaRepository.
- JpaRepository is a generic interface that takes two parameters: the entity and the type of the entity's primary key.

---

> Updating all methods into JPA instead of hardcode ArrayList<>()

```java
  @Override
  public List<Job> findAll() {
    return jobRepository.findAll();
  }

  @Override
  public void createJob(Job job) {
    job.setId(nextId++);
    jobRepository.save(job);
  }

  @Override
  public Job getJobById(Long id) {
    return jobRepository.findById(id).orElse(null);
  }
```

### URL

`http://localhost:8080/h2-console`

#### conf

```xml
2024-04-27T23:03:50.767-07:00  INFO 55118 --- [jobapplication] [main] o.s.b.a.h2.H2ConsoleAutoConfiguration    :
H2 console available at '/h2-console'. Database available at 'jdbc:h2:mem:test'
```

past in at `jdbc:` into JDBC URL

for example

```sh
JDBC URL:jdbc:h2:mem:test
```

## Application Properties

this is the way to show the SQL queries in the console

```java
spring.jpa.show-sql=true
```

this is the way to set the database to create and drop the tables every time the application starts.

```java
spring.jpa.hibernate.ddl-auto=create-drop
```

> Controller calls --> Service --> Service calls --> Repository --> Repository calls --> Database --> Database returns --> Repository --> Repository returns --> Service --> Service returns --> Controller

## Void vs Bool for Deleting

```java
@Override
public void deleteCompany(Long id) {
    if (!companyRepository.existsById(id)) {
        throw new EntityNotFoundException("Company with ID " + id + " not found.");
    }
    companyRepository.deleteById(id);
}

// Controller
@DeleteMapping("/{id}")
public ResponseEntity<String> removeCompany(@PathVariable Long id) {
    try {
        companyService.deleteCompany(id);
        return ResponseEntity.ok("Company deleted successfully");
    } catch (EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting company");
    }
}
```

---

---

```java
@Override
public boolean deleteCompany(Long id) {
    if (!companyRepository.existsById(id)) {
        return false;
    }
    companyRepository.deleteById(id);
    return true;
}

// Controller
@DeleteMapping("/{id}")
public ResponseEntity<String> removeCompany(@PathVariable Long id) {
    if (companyService.deleteCompany(id)) {
        return ResponseEntity.ok("Company deleted successfully");
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company not found");
    }
}

```
