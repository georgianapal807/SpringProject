package com.sda.company.service.impl;

import com.sda.company.convertor.CompanyConvertor;
import com.sda.company.dto.CompanyCreateDto;
import com.sda.company.dto.CompanyInfoDto;
import com.sda.company.dto.CompanyShortInfoDto;
import com.sda.company.exception.CompanyException;
import com.sda.company.model.Company;
import com.sda.company.repository.CompanyRepository;
import com.sda.company.repository.EmployeeRepository;
import com.sda.company.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyServiceImpl2 implements CompanyService {
    //    @Autowired //field injection -> not recommended because we do not have control on how java instantiate the classes
    private final CompanyRepository companyRepository;
    private final EmployeeRepository employeeRepository;

    //    constructor injection
    @Autowired
    public CompanyServiceImpl2(CompanyRepository companyRepository, EmployeeRepository employeeRepository) {
        this.companyRepository = companyRepository;
        this.employeeRepository = employeeRepository;
    }

    public CompanyInfoDto createCompany(CompanyCreateDto companyCreateDto) {
//        Company company = companyRepository.save(CompanyConvertor.createDtoToEntity(companyCreateDto));
//        CompanyInfoDto companyInfoDto = CompanyConvertor.entityToInfoDto(company);
//
//        return companyInfoDto;

        //alternative
        return CompanyConvertor.entityToInfoDto(companyRepository.save(CompanyConvertor.createDtoToEntity(companyCreateDto)));
    }

    @Override
    public List<CompanyShortInfoDto> getAllCompanies() {
        List<CompanyShortInfoDto> companyShortInfoDto = new ArrayList<>();
        companyRepository.findAll().forEach(company -> companyShortInfoDto.add(CompanyConvertor.entityToShortInfoDto(company)));

        return companyShortInfoDto;
    }

    @Override
    public CompanyInfoDto findCompanyByName(String name) {
        Company company = companyRepository.findByName(name)
                .orElseThrow(() -> new CompanyException("Company with name " + name + " not found"));

        return CompanyConvertor.entityToInfoDto(company);
    }

    @Override
    public void generateCompanies(List<Company> companies) {
        companyRepository.saveAll(companies);
        System.out.println(companies.size() + " companies were generated");
    }

}