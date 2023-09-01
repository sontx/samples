package dev.sontx.samples.multitenancysharedschema.service.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CompanyDTO implements Serializable {
    private Long id;
    private String name;
    private String address;
}
