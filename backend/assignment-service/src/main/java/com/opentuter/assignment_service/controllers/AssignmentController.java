package com.opentuter.assignment_service.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AssignmentController {
    @GetMapping("/health")
    public String health() {
        return "Assignment Service is UP";
    }
}
