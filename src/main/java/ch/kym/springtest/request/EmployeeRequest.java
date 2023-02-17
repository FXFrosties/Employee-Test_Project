package ch.kym.springtest.request;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeRequest {

    private String name; 
    private String email;
    private String department;
    private String team;
    private List<String> subTeam;
    
}
