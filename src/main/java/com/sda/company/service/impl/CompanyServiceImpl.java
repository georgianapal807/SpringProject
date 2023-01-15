package com.sda.company.service.impl;

import com.sda.company.convertor.CompanyConvertor;
import com.sda.company.dto.CompanyCreateDto;
import com.sda.company.dto.CompanyInfoDto;
import com.sda.company.dto.CompanyShortInfoDto;
import com.sda.company.model.Company;
import com.sda.company.repository.CompanyRepository;
import com.sda.company.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    //    @Autowired - field injection -> not recommended because we do not have control on how java instantiate the classes
    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public CompanyInfoDto createCompany(CompanyCreateDto companyCreateDto) {
//        Company company = companyRepository.save(CompanyConvertor.createDtoToEntity(companyCreateDto));
//        CompanyInfoDto companyInfoDto = CompanyConvertor.entityToInfoDto(company);
//        
//        return companyInfoDto;
        return CompanyConvertor.entityToInfoDto(companyRepository.save(CompanyConvertor.createDtoToEntity(companyCreateDto)));
    }

    @Override
    public List<CompanyShortInfoDto> getAllCompanies() {
        List<CompanyShortInfoDto> companyShortInfoDtos = new ArrayList<>();
        companyRepository.findAll().forEach(company -> companyShortInfoDtos.add(CompanyConvertor.entityToShortInfoDto(company)));
        return companyShortInfoDtos;
    }

    @Override
    public Optional<CompanyInfoDto> findCompanyByName(String name) {
        Optional<Company> company = companyRepository.findByName(name);
        CompanyInfoDto companyInfoDto = null;
        if (company.isPresent()) {
            companyInfoDto = CompanyConvertor.entityToInfoDto(company.get());
        }
        return Optional.ofNullable(companyInfoDto);
    }

    @Override
    public void generateCompanies(List<Company> companies) {
        companyRepository.saveAll(companies);
        System.out.println(companies.size() + " dummy companies were generated.");
    }


}