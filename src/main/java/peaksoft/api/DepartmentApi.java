package peaksoft.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Department;
import peaksoft.model.Hospital;
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

    @GetMapping(
            "/{hospitalId}")
    public String findAll(@PathVariable Long hospitalId,Model model) {
        model.addAttribute("departments", departmentService.getAll(hospitalId));
        model.addAttribute(hospitalId);
        return "department/mainPage";
    }

    @GetMapping("/{hospitalId}/new")
    public String create(@PathVariable Long hospitalId, Model model) {
        model.addAttribute("newDepartment", new Department());
        model.addAttribute(hospitalId);
        return "department/savePage";
    }

    @PostMapping("/{hospitalId}/savePage")
    public String save(@PathVariable Long hospitalId,@ModelAttribute("newDepartment") Department department) {
        departmentService.save(hospitalId,department);
        return "redirect:/departments/"+hospitalId;
    }

    @DeleteMapping("/{hospitalId}{departmentId}/delete")
    public String deleteById(@PathVariable("id")Long id) {
        departmentService.deleteById(id);
        return "redirect:/departments";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("department", departmentService.getById(id));
        return "department/update";
    }

    @PutMapping("/{id}/update")
    public String update(@PathVariable("id") Long id, @ModelAttribute("department") Department department) {
        departmentService.update(id, department);
        return "redirect:/departments";
    }
}
