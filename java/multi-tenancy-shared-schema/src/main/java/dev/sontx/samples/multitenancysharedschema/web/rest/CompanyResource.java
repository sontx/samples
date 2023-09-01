package dev.sontx.samples.multitenancysharedschema.web.rest;

import dev.sontx.samples.multitenancysharedschema.service.CompanyService;
import dev.sontx.samples.multitenancysharedschema.service.dto.CompanyDTO;
import dev.sontx.samples.multitenancysharedschema.service.dto.CreateCompanyDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/company")
@RequiredArgsConstructor
public class CompanyResource {
    private final CompanyService companyService;

    @PostMapping
    public CompanyDTO createCompany(@Valid @RequestBody CreateCompanyDTO createCompanyDTO) {
        return companyService.create(createCompanyDTO);
    }

    @PutMapping("{id}")
    public ResponseEntity<CompanyDTO> updateCompany(@PathVariable Long id, @Valid @RequestBody CreateCompanyDTO createCompanyDTO) {
        if (!companyService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        var updatedCompany = companyService.update(id, createCompanyDTO);
        return ResponseEntity.ok(updatedCompany);
    }

    @GetMapping
    public ResponseEntity<List<CompanyDTO>> getCompanies(@RequestParam(required = false) String search, Pageable pageable) {
        var page = companyService.findAll(search, pageable);
        var headers = new HttpHeaders();
        headers.add("X-Total-Count", Long.toString(page.getTotalElements()));
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        if (!companyService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        companyService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
