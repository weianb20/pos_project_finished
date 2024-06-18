package at.htlkaindorf.pos_projekt.Service;

import at.htlkaindorf.pos_projekt.repo.UserRepo;
import at.htlkaindorf.pos_projekt.user.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailService implements UserDetailsService {

    private final UserRepo userRepo;

    @Autowired
    public MyUserDetailService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public boolean userExists(String email) {
        return userRepo.findByEmail(email) != null;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        MyUser user = userRepo.findByEmail(email);
        if(user == null) {
            throw new UsernameNotFoundException(email);
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().name()));

        return new User(user.getEmail(), user.getPassword(), authorities);
    }
}
