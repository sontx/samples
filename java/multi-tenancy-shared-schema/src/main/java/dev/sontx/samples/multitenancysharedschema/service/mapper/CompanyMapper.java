package dev.sontx.samples.multitenancysharedschema.service.mapper;

import dev.sontx.samples.multitenancysharedschema.domain.Company;
import dev.sontx.samples.multitenancysharedschema.service.dto.CompanyDTO;
import dev.sontx.samples.multitenancysharedschema.service.dto.CreateCompanyDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    Company toEntity(CreateCompanyDTO createCompanyDTO);
    CompanyDTO toDto(Company company);
}
