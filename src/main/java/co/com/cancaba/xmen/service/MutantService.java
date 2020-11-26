package co.com.cancaba.xmen.service;

import co.com.cancaba.xmen.message.Stats;

/**
 * Inteface para la implementación de servicio que establece si 
 * un humano es mutante o no.
 * @author ccano
 *
 */
public interface MutantService {
    /**
     * Establece si un humano es mutante o no
     * de acuerdo a la cadena de adn enviada como 
     * parametro.
     * @param dna Dna del humano.
     * @return true si es mutante, false en caso contrario.
     */
    boolean isMutant(String[] dna);

    /**
     * Obtiene la estadísticas desde la base de datos.
     * @return Un objeto de tipo stats.
     */
    Stats getStats();
}
