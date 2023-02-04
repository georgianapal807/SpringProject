package com.sda.company.service;

import com.sda.company.dto.CompanyCreateDto;
import com.sda.company.dto.CompanyInfoDto;
import com.sda.company.dto.CompanyShortInfoDto;
import com.sda.company.model.Company;

import java.util.List;

public interface CompanyService {
    CompanyInfoDto createCompany(CompanyCreateDto companyCreateDto);

    List<CompanyShortInfoDto> getAllCompanies();

    CompanyInfoDto findCompanyByName(String name);

    void generateCompanies(List<Company> companies);
}