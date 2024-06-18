package at.htlkaindorf.pos_projekt.repo;

import at.htlkaindorf.pos_projekt.user.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<MyUser, Integer> {

    public MyUser findByEmail(String email);

}
