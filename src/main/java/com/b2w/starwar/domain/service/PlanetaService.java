package com.b2w.starwar.domain.service;

import com.b2w.starwar.domain.entity.Planeta;

/**
 * Classe criada por mpinho na data 03/03/19
 * E-mail: marcosjava2008@gmail.com
 */
public interface PlanetaService {

    /**
     * Método para salvar um planeta
     * @param planeta planeta
     * @return Planeta
     */
    Planeta save(Planeta planeta);

}
