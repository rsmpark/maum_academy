package ai.mindslab.maumacademy.contents.repository;

import ai.mindslab.maumacademy.contents.domain.ContentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentTypeRepository extends JpaRepository<ContentType,Long> {
}
