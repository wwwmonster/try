package com.alex.code.challenge.controller;

import com.alex.code.challenge.model.OperationRequest;
import com.alex.code.challenge.model.OperationType;
import com.alex.code.challenge.service.StringOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StringOperationController {
    @Autowired
    private StringOperationService service;

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("request", new OperationRequest());
        return "index";
    }

    @PostMapping("/process")
    public String processOperation(@ModelAttribute OperationRequest request, Model model) {
        String result = service.handleOperation(request.getOperation(), request.getInput1(), request.getInput2());
        model.addAttribute("request", request);
        model.addAttribute("result", result);
        return "index";
    }
}
