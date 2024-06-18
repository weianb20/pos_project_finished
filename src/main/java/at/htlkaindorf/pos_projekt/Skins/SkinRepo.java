package at.htlkaindorf.pos_projekt.Skins;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkinRepo extends JpaRepository<Skin, Integer> {}
