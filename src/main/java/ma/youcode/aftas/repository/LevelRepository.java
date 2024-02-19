package ma.youcode.aftas.repository;

import ma.youcode.aftas.model.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface LevelRepository extends JpaRepository<Level, Long> {
    public Optional<Level> findByCode(Integer code);


}
