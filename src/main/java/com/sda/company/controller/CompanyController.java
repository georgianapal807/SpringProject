package com.sda.company.controller;

import com.sda.company.components.CustomFakerCompany;
import com.sda.company.dto.CompanyCreateDto;
import com.sda.company.dto.CompanyInfoDto;
import com.sda.company.dto.CompanyShortInfoDto;
import com.sda.company.service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/company")
public class CompanyController {

    private final CompanyService companyService;

    private final CustomFakerCompany customFakerCompany;

    @Autowired
    public CompanyController(CompanyService companyService, CustomFakerCompany customFakerCompany) {
        this.companyService = companyService;
        this.customFakerCompany = customFakerCompany;
    }

    @PostMapping("/create")
    public ResponseEntity<CompanyInfoDto> createCompany(@RequestBody @Valid CompanyCreateDto companyCreateDto) {
        CompanyInfoDto companyInfoDto = companyService.createCompany(companyCreateDto);
        return ResponseEntity.ok(companyInfoDto);
    }

    @GetMapping("/getAllCompanies")
    public ResponseEntity<List<CompanyShortInfoDto>> getAllCompanies() {
        List<CompanyShortInfoDto> allCompanies = companyService.getAllCompanies();
        return ResponseEntity.ok(allCompanies);
    }

    @GetMapping("/findCompanyByName")
    public ResponseEntity<CompanyInfoDto> getCompanyByName(@RequestParam String name) {
        Optional<CompanyInfoDto> companyInfoDto = companyService.findCompanyByName(name);
        return ResponseEntity.of(companyInfoDto);
    }

    @GetMapping("/generateCompanies")
    public ResponseEntity<String> generateCompanies() {
        companyService.generateCompanies(customFakerCompany.generateDummyCompanies());
        return ResponseEntity.ok("The dummy companies were generated!");
    }
}
