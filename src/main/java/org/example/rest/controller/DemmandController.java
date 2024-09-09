package org.example.rest.controller;

import org.example.Service.DemmandService;
import org.example.domain.entity.Demmand;
import org.example.rest.dto.DemmandDTO;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/demmands")
public class DemmandController {

    private DemmandService demmandService;

    public DemmandController(DemmandService demmandService) {
        this.demmandService = demmandService;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Integer save(@RequestBody DemmandDTO dto){
        Demmand demmand = demmandService.save(dto);
        return demmand.getId();
    }


}