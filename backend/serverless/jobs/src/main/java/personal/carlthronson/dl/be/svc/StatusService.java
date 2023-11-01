package personal.carlthronson.dl.be.svc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import personal.carlthronson.dl.be.entity.StatusEntity;
import personal.carlthronson.dl.be.repo.SimpleRepository;
import personal.carlthronson.dl.be.repo.StatusRepository;

@Service
@Transactional
public class StatusService extends SimpleService<StatusEntity> {

    @Autowired
    StatusRepository repository;

    @Override
    public SimpleRepository<StatusEntity> getSimpleRepository() {
        return this.repository;
    }

    @Override
    public JpaRepository<StatusEntity, Long> getJpaRepository() {
        return this.repository;
    }
}
