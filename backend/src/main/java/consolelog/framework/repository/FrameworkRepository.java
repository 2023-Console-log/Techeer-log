package consolelog.framework.repository;

import consolelog.framework.domain.Framework;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FrameworkRepository extends JpaRepository<Framework, Long> {
    Optional<Framework> findByName(String name);
}
