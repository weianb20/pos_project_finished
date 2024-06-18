package at.htlkaindorf.pos_projekt.user;

import java.util.List;

public interface IUserService {
    void saveUser(UserDto userDto);

    MyUser findByEmail(String email);

    List<UserDto> findAllUsers();
}
