package com.pracApp.controller;

import com.pracApp.entity.Employee;
import com.pracApp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService=employeeService;
    }



    @GetMapping("/list")
    public String listAll(Model model){
        List<Employee> ans=employeeService.findAll();
        model.addAttribute("employee",ans);
        return "list-employees";
    }

    @GetMapping("/showFormForSave")
    public String saveForm(Model model){
        model.addAttribute("employee",new Employee());
        return "save-employee";
    }
    @GetMapping("/showFormForUpdate")
    public String updateForm(@RequestParam("employeeId") int id, Model model){
        Employee employee=employeeService.findById(id);
        model.addAttribute("employee",employee);
        return "save-employee";
    }
    @GetMapping("/showFormForDelete")
    public String deleteForm(@RequestParam("employeeId") int id, Model model){
        employeeService.deleteById(id);
        return "redirect:/employees/list";
    }

    @PostMapping("/addemployee")
    public String addEmployee(@ModelAttribute Employee employee){
        employeeService.save(employee);
        return "redirect:/employees/list";
    }



}
