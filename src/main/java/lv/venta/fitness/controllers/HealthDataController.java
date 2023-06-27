package lv.venta.fitness.controllers;

import lv.venta.fitness.services.IHealthDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.rmi.server.ExportException;

@RestController
@RequestMapping("/healthData")
public class HealthDataController {

    @Autowired
    private IHealthDataService healthDataService;

    @GetMapping("/error")
    public String getError(Model model){
        model.addAttribute("packetError", "Error");
        return "error-page";
    }

    @GetMapping("/showAll")
    public String getAllHealthData(Model model){
        model.addAttribute("healthData", healthDataService.selectAllHealthData());
        return "all-healthdata-page";
    }

    @GetMapping("/showHealthDataByUserId/{idus}")
    public String getHealthDataById(@PathVariable(name = "idus") long idus, Model model){
        try {
            model.addAttribute("healthData", healthDataService.selectAllHealthDataByUserId(idus));
            return "all-healthdata-page";
        } catch (Exception e) {
            model.addAttribute("packetError", e.getMessage());
            return "error-page";
        }
    }

    @GetMapping("/deleteById/{idhe}")
    public String getDeleteExercise(@PathVariable(name = "idhe") long idhe, Model model){
        try {
            healthDataService.deleteHealthDataById(idhe);
            model.addAttribute("healthData", healthDataService.selectAllHealthData());
            return "all-healthData-page";
        } catch (Exception e) {
            model.addAttribute("packetError", e.getMessage());
            return "error-page";
        }
    }

    @GetMapping("/deleteByUserId/{idus}")
    public String getDeleteExerciseByUserID(@PathVariable(name = "idus") long idus, Model model){
        try {
            healthDataService.deleteHealthDataByUserId(idus);
            model.addAttribute("healthData", healthDataService.selectAllHealthData());
            return "all-healthData-page";
        } catch (Exception e) {
            model.addAttribute("packetError", e.getMessage());
            return "error-page";
        }
    }


}
