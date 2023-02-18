package peaksoft.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Hospital;
import peaksoft.service.HospitalService;


/**
 * @created : Lenovo Nuriza
 **/
@Controller
@RequestMapping("/hospitals")
public class HospitalApi {
    private final HospitalService hospitalService;

    @Autowired
    public HospitalApi(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("hospitals", hospitalService.getAll());
        return "hospital/mainPage";
    }

    @GetMapping("/new")
    public String create(Model model){
        model.addAttribute("newHospital",new Hospital());
        return "hospital/savePage";
    }

    @PostMapping("/savePage")
    public String save(@ModelAttribute("newHospital")Hospital hospital){
        hospitalService.save(hospital);
        return "redirect:/hospitals";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteById(@PathVariable("id") Long id) {
        hospitalService.deleteById(id);
        return "redirect:/hospitals";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("hospital", hospitalService.getById(id));
        return "hospital/update";
    }

    @PutMapping("/{id}/update")
    public String update(@PathVariable("id") Long id, @ModelAttribute("hospital") Hospital newHospital) {
        hospitalService.update(id, newHospital);
        return "redirect:/hospitals";
    }

}