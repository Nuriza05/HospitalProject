package peaksoft.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import peaksoft.model.Department;
import peaksoft.service.DepartmentService;
import peaksoft.service.HospitalService;

/**
 * @created : Lenovo Nuriza
 **/
@Controller
@RequestMapping("/departments")
public class DepartmentApi {
    private final DepartmentService departmentService;
    private final HospitalService hospitalService;

    @Autowired
    public DepartmentApi(DepartmentService departmentService, HospitalService hospitalService) {
        this.departmentService = departmentService;
        this.hospitalService = hospitalService;
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("departments", departmentService.getAll());
        return "department/mainPage";
    }

    @GetMapping("/savePage")
    public String create(Model model){
        model.addAttribute("departments", new Department());
        model.addAttribute("hospitals",hospitalService.getAll());
        return "department/savePage";
    }

    @PostMapping("/")
    public String save (Department department){
        departmentService.save(department);
        return "redirect:/departments";
    }

}
