package org.example.rest.controller;

import org.example.Service.DemmandService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/demmands")
public class DemmandController {

    private DemmandService demmandService;

    public DemmandController(DemmandService demmandService) {
        this.demmandService = demmandService;
    }
}