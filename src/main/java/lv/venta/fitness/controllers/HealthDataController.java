package lv.venta.fitness.controllers;

import lv.venta.fitness.services.IHealthDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HealthDataController {

    @Autowired
    private IHealthDataService healthDataService;

    @GetMapping("/error")
    public String getError(Model model){
        model.addAttribute("packetError", "Error");
        return "error-page";
    }

    @GetMapping("/healthData/showAll")
    public String getAllHealthData(Model model){
        model.addAttribute("healthData", healthDataService.selectAllHealthData());
        return "all-healthdata-page";
    }

    @GetMapping("/healthData/showHealthDataByUserId/{idus}")
    public String getHealthDataById(@PathVariable(name = "idus") long idus, Model model){
        try {
            model.addAttribute("healthData", healthDataService.selectAllHealthDataByUserId(idus));
            return "all-healthdata-page";
        } catch (Exception e) {
            model.addAttribute("packetError", e.getMessage());
            return "error-page";
        }
    }

}
