package ma.youcode.aftas.repository;

import ma.youcode.aftas.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MembreRepository extends JpaRepository<Member,Integer> {

    Optional<Member> findByNum(Integer num);

}
