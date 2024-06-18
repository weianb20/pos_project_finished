package at.htlkaindorf.pos_projekt.mock;

import at.htlkaindorf.pos_projekt.Skins.Skin;
import at.htlkaindorf.pos_projekt.Skins.SkinRepo;
import at.htlkaindorf.pos_projekt.repo.UserRepo;
import at.htlkaindorf.pos_projekt.role.Role;
import at.htlkaindorf.pos_projekt.user.MyUser;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class InitMock {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private SkinRepo skinRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    Logger logger = LoggerFactory.getLogger(getClass());

    @PostConstruct
    public void init() {
        if(userRepo.findByEmail("admin@gmail.com") == null) {
            MyUser adminUser = new MyUser();

            adminUser.setEmail("admin@gmail.com");
            adminUser.setPassword(passwordEncoder.encode("admin"));
            adminUser.setFirstName("admin");
            adminUser.setLastName("admin");
            adminUser.setRole(Role.ROLE_ADMIN);

            userRepo.save(adminUser);
            logger.info("Admin user created: {}", adminUser);
        } else {
            logger.info("Admin user already exists.");
        }

        if(userRepo.findByEmail("pb@gmail.com") == null) {
            MyUser user = new MyUser();
            user.setEmail("pb@gmail.com");
            user.setPassword(passwordEncoder.encode("pandy"));
            user.setFirstName("Pandy");
            user.setLastName("Bambusfresser");
            user.setRole(Role.ROLE_USER);

            userRepo.save(user);
            logger.info("Regular user created: {}", user);
        } else {
            logger.info("Regular user already exists.");
        }

        if(skinRepo.count() == 0) {
            Skin defaultYorick = new Skin();
            defaultYorick.setChampName("Yorick");
            defaultYorick.setSkinID(0);
            defaultYorick.setSkinName("default");
            defaultYorick.setChromas(false);
            defaultYorick.setPrice(11.99);
            skinRepo.save(defaultYorick);

            Skin undertakerYorick = new Skin();
            undertakerYorick.setChampName("Yorick");
            undertakerYorick.setSkinID(1);
            undertakerYorick.setSkinName("Undertaker Yorick");
            undertakerYorick.setChromas(false);
            undertakerYorick.setPrice(6.99);
            skinRepo.save(undertakerYorick);

            Skin gravelordAzir = new Skin();
            gravelordAzir.setChampName("Azir");
            gravelordAzir.setSkinID(2);
            gravelordAzir.setSkinName("Gravelord Azir");
            gravelordAzir.setChromas(false);
            gravelordAzir.setPrice(13.99);
            skinRepo.save(gravelordAzir);

            Skin worldsAzir = new Skin();
            worldsAzir.setChampName("Azir");
            worldsAzir.setSkinID(14);
            worldsAzir.setSkinName("Worlds 2022 Azir");
            worldsAzir.setChromas(true);
            worldsAzir.setPrice(17.99);
            skinRepo.save(worldsAzir);

            Skin obsidianMalph = new Skin();
            obsidianMalph.setChampName("Malphite");
            obsidianMalph.setSkinID(4);
            obsidianMalph.setSkinName("Obsidian Malphite");
            obsidianMalph.setChromas(false);
            obsidianMalph.setPrice(13.99);
            skinRepo.save(obsidianMalph);

            Skin fpxMalph = new Skin();
            fpxMalph.setChampName("Malphite");
            fpxMalph.setSkinID(25);
            fpxMalph.setSkinName("FPX Malphite");
            fpxMalph.setChromas(true);
            fpxMalph.setPrice(21.99);
            skinRepo.save(fpxMalph);
        }
    }
}
