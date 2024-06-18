package at.htlkaindorf.pos_projekt.Skins;

import java.util.List;

public interface SkinService {
    void saveSkin(SkinDto skinDto);
    List<SkinDto> getAllSkins();
}
