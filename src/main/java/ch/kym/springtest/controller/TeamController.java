package ch.kym.springtest.controller;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.kym.springtest.repository.TeamRepository;
import ch.kym.springtest.response.TeamResponse;
import ch.kym.springtest.model.Team;

@RestController
public class TeamController {
    
    @Autowired
    private TeamRepository tRepo;

    @GetMapping("/teams")
    public List<TeamResponse> getTeams(){

        List<Team> teams = tRepo.findAll();
        List<TeamResponse> list = new ArrayList<>();
        
        teams.forEach(t -> {
             TeamResponse tResponse = new TeamResponse();
             tResponse.setTeamName(t.getName());
             tResponse.setId(t.getId());
             tResponse.setEmployeeName(t.getEmployee().getName());
             list.add(tResponse);
         });

        return list;
    }


}
