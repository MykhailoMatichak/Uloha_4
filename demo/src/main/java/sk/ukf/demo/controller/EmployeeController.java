package sk.ukf.demo.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.ukf.demo.dto.EmployeeDTO;
import sk.ukf.demo.model.Employee;
import sk.ukf.demo.response.ResponseWrapper;
import sk.ukf.demo.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper<List<Employee>>> getAll() {
        List<Employee> employees = service.findAll();
        return ResponseEntity.ok(ResponseWrapper.<List<Employee>>builder()
                .data(employees)
                .message("Successfully retrieved all employees")
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Employee>> getById(@PathVariable Long id) {
        Employee employee = service.findById(id);
        return ResponseEntity.ok(ResponseWrapper.<Employee>builder()
                .data(employee)
                .message("Successfully retrieved employee")
                .build());
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper<Employee>> create(@Valid @RequestBody EmployeeDTO dto) {
        Employee created = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseWrapper.<Employee>builder()
                .data(created)
                .message("Employee successfully created")
                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Employee>> update(@PathVariable Long id, @Valid @RequestBody EmployeeDTO dto) {
        Employee updated = service.update(id, dto);
        return ResponseEntity.ok(ResponseWrapper.<Employee>builder()
                .data(updated)
                .message("Employee successfully updated")
                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Void>> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(ResponseWrapper.<Void>builder()
                .message("Employee successfully deleted")
                .build());
    }
}