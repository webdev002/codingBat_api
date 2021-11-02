package uz.pdp.codingbatapi.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LanguageDto {

    @NotNull(message = "name bosh bolishi mukin emas!")
    private String name;

    @NotNull(message = "userId bosh bolishi mumkin emas!")
    private Integer userId;
}
