package com.sda.company.controller;

import com.sda.company.components.CustomFakerCompany;
import com.sda.company.dto.CompanyCreateDto;
import com.sda.company.dto.CompanyInfoDto;
import com.sda.company.dto.CompanyShortInfoDto;
import com.sda.company.service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/company")
@ControllerAdvice
public class CompanyController {
    private final CompanyService companyService;
    private final CustomFakerCompany customFakerCompany;

    @Autowired
    public CompanyController(@Qualifier("companyServiceImpl2") CompanyService companyService, CustomFakerCompany customFakerCompany) {
        this.companyService = companyService;
        this.customFakerCompany = customFakerCompany;
    }

    @PostMapping("/create")
    public ResponseEntity<CompanyInfoDto> createCompany(@RequestBody @Valid CompanyCreateDto companyCreateDto) {
        CompanyInfoDto companyInfoDto = companyService.createCompany(companyCreateDto);

        return ResponseEntity.ok(companyInfoDto);
//        return ResponseEntity.ok(companyService.createCompany(companyCreateDto)) -> alternative
    }

    @GetMapping("/getAllCompanies")
    public ResponseEntity<List<CompanyShortInfoDto>> getAllCompanies() {
        List<CompanyShortInfoDto> allCompanies = companyService.getAllCompanies();

        return ResponseEntity.ok(allCompanies);
        //return ResponseEntity.ok(companyService.getAllCompanies());   -> alternative
    }

    @GetMapping("/findCompanyByName")
    public ResponseEntity<CompanyInfoDto> getCompanyByName(@RequestParam String name) {
        CompanyInfoDto companyInfoDto = companyService.findCompanyByName(name);

        return ResponseEntity.ok(companyInfoDto);
    }

    @GetMapping("/generateCompanies")
    public ResponseEntity<String> generateCompanies() {
        companyService.generateCompanies(customFakerCompany.generateDummyCompanies());

        return ResponseEntity.ok("Companies were generated");
    }

}