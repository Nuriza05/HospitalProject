package peaksoft.api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Doctor;
import peaksoft.service.DepartmentService;
import peaksoft.service.DoctorService;


/**
 * @created : Lenovo Nuriza
 **/
@Controller
@RequestMapping("/doctors")
public class DoctorApi {
    private final DoctorService doctorService;
    private final DepartmentService departmentService;

    @Autowired
    public DoctorApi(DoctorService doctorService, DepartmentService departmentService) {
        this.doctorService = doctorService;
        this.departmentService = departmentService;
    }


    @GetMapping("/{hospitalId}")
    public String findAll(@PathVariable Long hospitalId, Model model) {
        model.addAttribute("doctors", doctorService.getAll(hospitalId));
        return "doctor/mainPage";
    }

    @GetMapping("/{hospitalId}/new")
    public String create(@PathVariable Long hospitalId, Model model) {
        model.addAttribute("newDoctor", new Doctor());
        model.addAttribute(hospitalId);
        model.addAttribute("departments", departmentService.getAll(hospitalId));
        return "doctor/savePage";
    }

    @PostMapping("/{hospitalId}/savePage")
    public String save(@PathVariable Long hospitalId, @ModelAttribute("newDoctor") Doctor doctor) {
        doctorService.save(hospitalId, doctor);
        return "redirect:/doctors/" + hospitalId;
    }

    @DeleteMapping("/{hospitalId}/{doctorId}/delete")
    public String deleteById(@PathVariable Long hospitalId, @PathVariable Long doctorId) {
        doctorService.deleteById(doctorId);
        return "redirect:/doctors/" + hospitalId;
    }

    @GetMapping("/{hospitalId}/{doctorId}/edit")
    public String edit(Model model, @PathVariable Long doctorId, @PathVariable Long hospitalId) {
        model.addAttribute("doctor",doctorService.getById(doctorId));
        model.addAttribute("hospitalId", hospitalId);
        return "doctor/update";
    }

    @PutMapping("/{hospitalId}/{doctorId}/update")
    public String update(@PathVariable Long hospitalId, @PathVariable Long doctorId, @ModelAttribute("doctor") Doctor doctor) {
        doctorService.update(doctorId, doctor);
        return "redirect:/doctors/" + hospitalId;
    }



}