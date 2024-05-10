package org.example.Service.impl;

import org.example.Service.DemmandService;
import org.example.domain.repository.DemmandRepository;
import org.springframework.stereotype.Service;

@Service
public class DemmandServiceImpl implements DemmandService {

    private DemmandRepository demmandRepository;

    public DemmandServiceImpl(DemmandRepository demmandRepository) {
        this.demmandRepository = demmandRepository;
    }
}