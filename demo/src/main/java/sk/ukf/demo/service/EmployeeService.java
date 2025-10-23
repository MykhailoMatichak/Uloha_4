package sk.ukf.demo.service;

import org.springframework.stereotype.Service;
import sk.ukf.demo.dto.EmployeeDTO;
import sk.ukf.demo.model.Employee;
import sk.ukf.demo.repository.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<Employee> findAll() {
        return repository.findAll();
    }

    public Employee findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public Employee create(EmployeeDTO dto) {
        Employee employee = mapToEntity(dto);
        return repository.save(employee);
    }

    public Employee update(Long id, EmployeeDTO dto) {
        Employee employee = findById(id);
        updateEntityFromDto(employee, dto);
        return repository.save(employee);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private Employee mapToEntity(EmployeeDTO dto) {
        Employee employee = new Employee();
        updateEntityFromDto(employee, dto);
        return employee;
    }

    private void updateEntityFromDto(Employee employee, EmployeeDTO dto) {
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setBirthDate(dto.getBirthDate());
        employee.setEmail(dto.getEmail());
        employee.setPhone(dto.getPhone());
        employee.setJobTitle(dto.getJobTitle());
        employee.setSalary(dto.getSalary());
        employee.setFullTime(dto.getFullTime());
    }
}