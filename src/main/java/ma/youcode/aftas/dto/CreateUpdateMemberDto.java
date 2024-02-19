package ma.youcode.aftas.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import ma.youcode.aftas.enums.IdentityDocumentType;

import java.util.Date;

@Data
public class CreateUpdateMemberDto {

    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotBlank(message = "FamilyName cannot be blank")
    private String familyName;
    @Past(message = "AccessionDate should be in the past")
    private Date accessionDate;
    @NotBlank(message = "Nationality cannot be blank")
    private String nationality;
    @NotNull(message = "IdentityDocumentType cannot be null")
    private IdentityDocumentType identityDocumentType;
    @NotBlank(message = "IdentityNumber cannot be blank")
    private String identityNumber;
}
