package QAPSpringBoot.QapFinal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "tournament", path = "tournament")
public interface TournamentRepository extends JpaRepository<Tournament, Long> {

    Tournament findById(@Param("id") long id);

    List<Tournament> findAll();
}