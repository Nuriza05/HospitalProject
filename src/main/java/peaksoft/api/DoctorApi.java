package peaksoft.api;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import peaksoft.model.Doctor;
import peaksoft.service.DepartmentService;
import peaksoft.service.DoctorService;
import peaksoft.service.HospitalService;

/**
 * @created : Lenovo Nuriza
 **/
@Controller
@RequestMapping("/doctors")
public class DoctorApi {
    private final DoctorService doctorService;
    private final HospitalService hospitalService;
    private final DepartmentService departmentService;

    @Autowired
    public DoctorApi(DoctorService doctorService, HospitalService hospitalService, DepartmentService departmentService) {
        this.doctorService = doctorService;
        this.hospitalService = hospitalService;
        this.departmentService = departmentService;
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("doctors", doctorService.getAll());
        return "doctor/mainPage";
    }

    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("newDoctor", new Doctor());
        model.addAttribute("hospitals", hospitalService.getAll());
        model.addAttribute("departments", departmentService.getAll());
        return "doctor/savePage";
    }

    @PostMapping("/savePage")
    public String save(@ModelAttribute("newDoctor") Doctor doctor) {
        doctorService.save(doctor);
        return "redirect:/doctors";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteById(@PathVariable("id") Long id) {
        doctorService.deleteById(id);
        return "redirect:/doctors";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("doctor", doctorService.getById(id));
        return "doctor/update";
    }

    @PutMapping("/{id}/update")
    public String update(@PathVariable("id") Long id, @ModelAttribute("newDoctor") Doctor newDoctor) {
        doctorService.update(id, newDoctor);
        return "redirect:/doctors";
    }
}
