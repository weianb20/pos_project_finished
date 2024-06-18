package at.htlkaindorf.pos_projekt.controller;

import at.htlkaindorf.pos_projekt.Service.MyUserDetailService;
import at.htlkaindorf.pos_projekt.Skins.Skin;
import at.htlkaindorf.pos_projekt.Skins.SkinDto;
import at.htlkaindorf.pos_projekt.Skins.SkinService;
import at.htlkaindorf.pos_projekt.user.IUserService;
import at.htlkaindorf.pos_projekt.user.UserDto;
import jakarta.validation.Valid;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final IUserService userService;
    private final MyUserDetailService myUserDetailService;
    private final SkinService skinService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(IUserService userService, MyUserDetailService myUserDetailService, SkinService skinService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.myUserDetailService = myUserDetailService;
        this.skinService = skinService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("login")
    public String loginForm() {
        logger.info("--> loginForm()");
        return "login";
    }

    @GetMapping({"/", "index"})
    public String homePage() {
        logger.info("--> homePage()");
        return "index";
    }

    @GetMapping("register")
    public String showRegisterForm(Model model) {
        logger.info("--> showRegisterForm()");
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "register";
    }

    @PostMapping("register/save")
    public String register(@Valid @ModelAttribute("user") UserDto userDto, BindingResult bindingResult, Model model) {
        logger.info("Attempt to register user: {}", userDto);

        if(myUserDetailService.userExists(userDto.getEmail())) {
            logger.warn("alr existing user: {}", userDto.getEmail());
            bindingResult.rejectValue("email", null, "alr existing acc with this email");
        }
        if(bindingResult.hasErrors()) {
            logger.error("Register error for user: {}", userDto.getEmail());
            model.addAttribute("user", userDto);
            return "register";
        }
        userService.saveUser(userDto);
        logger.info("register was successful: {}", userDto.getEmail());
        return "redirect:/login";
    }
}
