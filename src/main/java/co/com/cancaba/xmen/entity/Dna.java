package co.com.cancaba.xmen.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * Entidad para la tabla dna.
 * @author ccano
 *
 */
@Entity
@Table(name = "dna")
@Data
public class Dna {
    /**
     * String json de la cadena dna.
     */
    @Id
    private String dna;
    /**
     * Establece si la cadena es mutante o no.
     */
    private Boolean dnaMutant;
}
