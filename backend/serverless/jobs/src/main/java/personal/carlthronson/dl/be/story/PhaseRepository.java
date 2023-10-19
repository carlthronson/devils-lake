package personal.carlthronson.dl.be.story;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface PhaseRepository extends JpaRepository<Phase, Long> {

    Phase getByName(String name);

}
