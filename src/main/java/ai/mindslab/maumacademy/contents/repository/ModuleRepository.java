package ai.mindslab.maumacademy.contents.repository;

import ai.mindslab.maumacademy.contents.domain.Module;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModuleRepository extends JpaRepository<Module,Long> {

    List<Module> getAllModules();
}
