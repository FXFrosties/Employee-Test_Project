
package ch.kym.springtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.kym.springtest.model.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long>{

}