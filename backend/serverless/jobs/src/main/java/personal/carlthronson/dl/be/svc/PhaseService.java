package personal.carlthronson.dl.be.svc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import personal.carlthronson.dl.be.entity.PhaseEntity;
import personal.carlthronson.dl.be.repo.PhaseRepository;
import personal.carlthronson.dl.be.repo.SimpleRepository;

@Service
@Transactional
public class PhaseService extends SimpleService<PhaseEntity> {

    @Autowired
    PhaseRepository repository;

    @Override
    public SimpleRepository<PhaseEntity> getSimpleRepository() {
        return this.repository;
    }

    @Override
    public JpaRepository<PhaseEntity, Long> getJpaRepository() {
        return this.repository;
    }
}
