package ch.kym.springtest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ch.kym.springtest.model.Employee;
import ch.kym.springtest.model.SubTeam;
import ch.kym.springtest.model.Team;
import ch.kym.springtest.service.EmployeeService;
import ch.kym.springtest.request.EmployeeRequest;
import ch.kym.springtest.repository.TeamRepository;
import ch.kym.springtest.repository.EmployeeRepository;
import ch.kym.springtest.repository.SubTeamRepository;
import jakarta.validation.Valid;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService eService;

    @Autowired
    private EmployeeRepository eRepo;

    @Autowired
    private TeamRepository tRepo;

    @Autowired
    private SubTeamRepository stRepo; 

    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    @GetMapping("/version")
    public String getAppDetails(){
        return appName + " - " + appVersion;
    }
    
    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getEmployees(@RequestParam Integer pageNumber, @RequestParam Integer pageSize){
        return new ResponseEntity<>(eService.getEmployees(pageNumber, pageSize), HttpStatus.OK);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long id){
        return new ResponseEntity<Employee>(eService.getEmployee(id), HttpStatus.OK);
    }

    @PostMapping("/employees")
    public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody EmployeeRequest eRequest){

        //first create a team becaus the employee needs the team
        Team team = new Team();
        team.setName(eRequest.getTeam());

        team = tRepo.save(team);

        //second create the employee with the team
        Employee employee = new Employee(eRequest);
        employee.setTeam(team);

        employee = eRepo.save(employee);

        //now create te Subteams with the employee since an employee can have multiple subteams
        for(String s: eRequest.getSubTeam()){
            SubTeam subTeam = new SubTeam();
            subTeam.setName(s);
            subTeam.setEmployee(employee);
            stRepo.save(subTeam);
        }

        return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee){
        employee.setId(id);
        return new ResponseEntity<>(eService.updateEmployee(employee), HttpStatus.OK);
    }

    @DeleteMapping("/employees")
    public ResponseEntity<HttpStatus> deleteEmployee(@RequestParam Long id){
        eService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/employees/filerByName")
    public ResponseEntity<List<Employee>> getEmployeesByName(@RequestParam String name){
        return new ResponseEntity<>(eService.getEmployeesByName(name), HttpStatus.OK);
    }

    @GetMapping("/employees/filerByNameAndLocation")
    public ResponseEntity<List<Employee>> getEmployeesByNameAndLocation(@RequestParam String name, @RequestParam String location){
        return new ResponseEntity<>(eService.getEmployeesByNameAndLocation(name, location), HttpStatus.OK);
    }

    @GetMapping("/employees/filterByKeyword")
    public ResponseEntity<List<Employee>> getEmployeesByKeyword(@RequestParam String name){
        return new ResponseEntity<>(eService.getEmployeesByKeyword(name), HttpStatus.OK);
    }

    @DeleteMapping("/employees/delete/{name}")
    public ResponseEntity<String> deleteEmployeesByName(@PathVariable String name){
        return new ResponseEntity<>("Number of Rows affected" + eService.deleteEmployeesByName(name), HttpStatus.ACCEPTED);
    }

    @GetMapping("/employees/getByTeamName")
    public ResponseEntity<List<Employee>> getEmployeesByTeam(@RequestParam String name){
        return new ResponseEntity<>(eRepo.getEmployeeByTeamName(name), HttpStatus.OK);
    }


}
