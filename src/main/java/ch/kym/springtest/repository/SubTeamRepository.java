package ch.kym.springtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ch.kym.springtest.model.SubTeam;

@Repository
public interface SubTeamRepository extends JpaRepository<SubTeam, Long>{

}