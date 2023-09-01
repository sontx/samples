package dev.sontx.samples.multitenancysharedschema.service.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

@Data
public class CreateCompanyDTO implements Serializable {
    @NotEmpty
    @Size(min = 5, max = 255)
    private String name;

    @Size(max = 255)
    private String address;
}
