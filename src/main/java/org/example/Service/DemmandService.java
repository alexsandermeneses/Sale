package org.example.Service;

import org.example.domain.entity.Demmand;
import org.example.rest.dto.DemmandDTO;

import java.util.Optional;

public interface DemmandService {
    Demmand save (DemmandDTO demmandDTO);

    Optional<Demmand> getFullOrder(Integer id);
}