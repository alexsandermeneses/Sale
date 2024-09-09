package org.example.Service;

import org.example.domain.entity.Demmand;
import org.example.rest.dto.DemmandDTO;

public interface DemmandService {
    Demmand save (DemmandDTO demmandDTO);
}