package at.htlkaindorf.pos_projekt.Skins;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SkinServiceImpl implements SkinService{

    private final SkinRepo skinRepo;

    @Autowired
    public SkinServiceImpl(SkinRepo skinRepo) {
        this.skinRepo = skinRepo;
    }

    @Override
    public void saveSkin(SkinDto skinDto) {
        Skin skin = new Skin();
        skin.setChampName(skinDto.getChampName());
        skin.setSkinID(skinDto.getSkinID());
        skin.setSkinName(skinDto.getSkinName());
        skin.setChromas(skinDto.isChromas());
        skin.setPrice(skinDto.getPrice());
        skinRepo.save(skin);
    }

    @Override
    public List<SkinDto> getAllSkins() {
        return skinRepo.findAll().stream().map(skin -> {
            SkinDto skinDto = new SkinDto();
            skinDto.setChampName(skin.getChampName());
            skinDto.setSkinID(skin.getSkinID());
            skinDto.setSkinName(skin.getSkinName());
            skinDto.setChromas(skin.isChromas());
            skinDto.setPrice(skin.getPrice());
            return skinDto;
        }).collect(Collectors.toList());
    }
}
