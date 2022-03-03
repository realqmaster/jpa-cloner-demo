package com.example.clonetester;

import com.example.clonetester.entity.Address;
import com.example.clonetester.entity.Child;
import com.example.clonetester.entity.Company;
import com.example.clonetester.entity.Employee;
import com.example.clonetester.repository.CompanyRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;
import sk.nociar.jpacloner.JpaCloner;
import sk.nociar.jpacloner.PropertyFilter;
import sk.nociar.jpacloner.PropertyFilters;

import javax.persistence.Id;
import javax.persistence.Transient;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class CloneTesterApplication implements ApplicationRunner {

  @Autowired private CompanyRepository repository;

  public static void main(String[] args) {
    SpringApplication.run(CloneTesterApplication.class, args);
  }

  @Override
  @Transactional
  public void run(ApplicationArguments args) throws Exception {

    Company c = new Company();
    c.setCode("CPY");
    c.setName("MyCompany");
    Address a1 = new Address();

    a1.setCity("Padova");
    a1.setCountry("IT");
    a1.setCreatedAt(LocalDateTime.now());

    c.setLegalAddress(a1);

    List<Employee> employees = new ArrayList<>();
    Employee e1 = new Employee();
    e1.setName("Bob");
    Address a2 = new Address();
    a2.setCity("Albignasego");
    e1.setHomeAddress(a2);

    Employee e2 = new Employee();
    e2.setName("Mary");
    Address a3 = new Address();
    a3.setCity("Monselice");
    e2.setHomeAddress(a3);

    Child child = new Child();
    child.setName("Tim");
    e1.setChildren(Collections.singleton(child));
    e2.setChildren(Collections.singleton(child));

    employees.add(e1);
    employees.add(e2);
    c.setEmployees(employees);

    c = repository.save(c);

    PropertyFilter filter = PropertyFilters.getAnnotationFilter(Id.class, Transient.class);
    Company clonedCompany = JpaCloner.clone(c, filter, "*+");
    repository.save(clonedCompany);

    ObjectMapper mapper = new ObjectMapper();
    mapper.findAndRegisterModules();

    repository
        .findAll()
        .forEach(
            company -> {
              try {
                System.out.println(
                    mapper.writerWithDefaultPrettyPrinter().writeValueAsString(company));
              } catch (JsonProcessingException e) {
                e.printStackTrace();
              }
            });
  }
}
