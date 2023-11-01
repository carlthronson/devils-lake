package personal.carlthronson.dl.be.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import personal.carlthronson.dl.be.entity.StatusEntity;

@Repository
@Transactional
public interface StatusRepository extends JpaRepository<StatusEntity, Long>,
        SimpleRepository<StatusEntity> {

}
