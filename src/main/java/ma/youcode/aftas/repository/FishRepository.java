package ma.youcode.aftas.repository;

import ma.youcode.aftas.model.Fish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FishRepository extends JpaRepository<Fish,Long> {
    public Optional<Fish> findByName(String name);
}
