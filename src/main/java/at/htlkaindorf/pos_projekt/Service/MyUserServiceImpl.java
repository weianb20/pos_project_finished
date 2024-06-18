package at.htlkaindorf.pos_projekt.Service;

import at.htlkaindorf.pos_projekt.repo.UserRepo;
import at.htlkaindorf.pos_projekt.role.Role;
import at.htlkaindorf.pos_projekt.user.IUserService;
import at.htlkaindorf.pos_projekt.user.MyUser;
import at.htlkaindorf.pos_projekt.user.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MyUserServiceImpl implements IUserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MyUserServiceImpl(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDto userDto) {
        MyUser user = new MyUser();
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(Role.ROLE_USER);
        userRepo.save(user);
    }

    @Override
    public MyUser findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<MyUser> users = userRepo.findAll();

        return users.stream().map(user -> {
            UserDto userDto = new UserDto();
            userDto.setEmail(user.getEmail());
            userDto.setFirstName(userDto.getFirstName());
            userDto.setLastName(userDto.getLastName());
            return userDto;
        }).collect(Collectors.toList());
    }
}
