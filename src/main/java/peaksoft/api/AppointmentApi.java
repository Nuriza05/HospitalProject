package peaksoft.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import peaksoft.model.Appointment;
import peaksoft.model.Department;
import peaksoft.service.AppointmentService;

/**
 * @created : Lenovo Nuriza
 **/
@Controller
@RequestMapping("/appointments")
public class AppointmentApi {
    private final AppointmentService appointmentService;
    @Autowired
    public AppointmentApi(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }
    @GetMapping
    public String findAll(Model model){
     model.addAttribute("appointments", appointmentService.getAll());
     return "appointment/mainPage";
    }
    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("newAppointment", new Appointment());
        return "appointment/savePage";
    }

    @PostMapping("/savePage")
    public String save(@ModelAttribute("newAppointment") Appointment appointment) {
        appointmentService.save(appointment);
        return "redirect:/appointments";
    }
}
