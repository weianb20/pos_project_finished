package at.htlkaindorf.pos_projekt.Skins;

import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SkinDto {
    @NotEmpty(message = "Champ Name can not be empty")
    String champName;

    Integer skinID;

    @NotEmpty(message = "Skin Name can not be empty")
    String skinName;

    boolean chromas;

    Double price;
}
