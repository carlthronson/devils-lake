package personal.carlthronson.dl.be.svc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import personal.carlthronson.dl.be.entity.ContactEntity;
import personal.carlthronson.dl.be.repo.SimpleRepository;
import personal.carlthronson.dl.be.repo.ContactRepository;

@Service
@Transactional
public class ContactService extends SimpleService<ContactEntity> {

    @Autowired
    ContactRepository repository;

    @Override
    public SimpleRepository<ContactEntity> getSimpleRepository() {
        return this.repository;
    }

    @Override
    public JpaRepository<ContactEntity, Long> getJpaRepository() {
        return this.repository;
    }
}
