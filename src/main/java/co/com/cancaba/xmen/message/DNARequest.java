package co.com.cancaba.xmen.message;

import lombok.Data;

/**
 * Solicitud con la data para la detección de dna de mutante.
 * @author ccano
 *
 */
@Data
public class DNARequest {
    /**
     * Cadena de dna.
     */
    private String[] dna;
}
