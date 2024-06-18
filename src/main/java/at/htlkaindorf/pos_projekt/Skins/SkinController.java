package at.htlkaindorf.pos_projekt.Skins;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SkinController {
    private final SkinService skinService;

    @Autowired
    public SkinController(SkinService skinService) {
        this.skinService = skinService;
    }

    @GetMapping("/admin/addSkin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String showAddSkinForm(Model model) {
        model.addAttribute("skin", new SkinDto());
        return "addSkin";
    }

    @PostMapping("/admin/addSkin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String processAddSkin(@Valid @ModelAttribute("skin") SkinDto skinDto, BindingResult bindingResult, Model model) {
        System.out.println("::::::::::::::::::::::::::::::::::::::");
        System.out.println(skinDto.toString());
        System.out.println("::::::::::::::::::::::::::::::::::::::");
        if(bindingResult.hasErrors()) {
            return "addSkin";
        }
        skinService.saveSkin(skinDto);
        return "redirect:/skins";
    }

    @GetMapping("/skins")
    public String skinList(Model model) {
        model.addAttribute("skins", skinService.getAllSkins());
        //System.out.println(model);
        return "skins";
    }
}
