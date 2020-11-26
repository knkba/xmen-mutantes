package co.com.cancaba.xmen.message;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Permite extraer la consulta de estadísticas.
 * @author ccano
 *
 */
public interface Stats {
    /**
     * Obtiene la cantidad de mutantes.
     * @return Un entero con la cantidad.
     */
    @JsonProperty("count_mutant_dna")
    Integer getCountMutantDna();

    /**
     * Obtiene la cantidad de humanos.
     * @return Un entero con la cantidad.
     */
    @JsonProperty("count_human_dna")
    Integer getCountHumanDna();

    /**
     * Obtiene la relación entre mutantes y humanos.
     * @return Un float con la relacion.
     */
    Float getRatio();
}
