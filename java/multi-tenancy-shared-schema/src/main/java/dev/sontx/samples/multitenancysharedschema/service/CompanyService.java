package dev.sontx.samples.multitenancysharedschema.service;

import dev.sontx.samples.multitenancysharedschema.domain.Company;
import dev.sontx.samples.multitenancysharedschema.repository.CompanyRepository;
import dev.sontx.samples.multitenancysharedschema.service.dto.CompanyDTO;
import dev.sontx.samples.multitenancysharedschema.service.dto.CreateCompanyDTO;
import dev.sontx.samples.multitenancysharedschema.service.mapper.CompanyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    public CompanyDTO create(CreateCompanyDTO createCompanyDTO) {
        var company = companyMapper.toEntity(createCompanyDTO);
        var savedCompany = companyRepository.save(company);
        return companyMapper.toDto(savedCompany);
    }

    public CompanyDTO update(Long id, CreateCompanyDTO createCompanyDTO) {
        var company = companyMapper.toEntity(createCompanyDTO);
        company.setId(id);
        var savedCompany = companyRepository.save(company);
        return companyMapper.toDto(savedCompany);
    }

    public Page<CompanyDTO> findAll(String search, Pageable pageable) {
        Specification<Company> spec = (root, query, builder) -> builder.conjunction();
        if (StringUtils.hasText(search)) {
            var text = search.trim().toLowerCase();
            spec = spec.and((root, query, builder) -> builder.like(builder.lower(root.get("name")), "%%%s%%".formatted(text)));
        }

        return companyRepository.findAll(spec, pageable).map(companyMapper::toDto);
    }

    public boolean existsById(Long id) {
        return companyRepository.existsById(id);
    }

    public void deleteById(Long id) {
        companyRepository.deleteById(id);
    }
}
