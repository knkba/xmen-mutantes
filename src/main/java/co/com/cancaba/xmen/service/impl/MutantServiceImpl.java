package co.com.cancaba.xmen.service.impl;

import java.util.Arrays;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import co.com.cancaba.xmen.entity.Dna;
import co.com.cancaba.xmen.message.Stats;
import co.com.cancaba.xmen.repository.DNARepository;
import co.com.cancaba.xmen.service.MutantService;
import lombok.extern.slf4j.Slf4j;

/**
 * Implementación de MutantService.
 * @author ccano
 *
 */
@Service
@Slf4j
public class MutantServiceImpl implements MutantService {

    /**
     * Establece la cadena minima de adn. Secuencia de letras iguales.
     */
    private static final int DNAMIN = 4;
    /**
     * Umbral de búsqueda. Si es mayor a uno, sería un mutante.
     */
    private static final int UMBRAL = 1;
    /**
     * Repositorio para el manejo de la persistencia.
     */
    private DNARepository repository;

    /**
     * Constructor.
     * @param repository Inyeccción de dependencia del repositorio.
     */
    public MutantServiceImpl(DNARepository repository) {
        this.repository = repository;
    }

    /**
     * {@inheritDoc}
     * @throws JsonProcessingException 
     */
    @Override
    public boolean isMutant(String[] dna) {
        if (this.isValidDna(dna)) {
            boolean retorno = false;
            //Se realiza la busqueda de manera horizontal.
            int count = searchHorizontal(dna);
            //Si lo encontrado es mayor al umbral, no se hace
            //Mas busquedas y se determina que es mutante.
            if (count > UMBRAL) {
                retorno = true;
            }
            //Busqueda vertical
            count = searchVertical(dna, count);
            if (count > UMBRAL) {
                retorno = true;
            }
            //Busqueda en diagonales.
            count = searchOblicuo(dna, count);
            if (count > UMBRAL) {
                retorno = true;
            }
            Dna entity = new Dna();
            entity.setDna(Arrays.toString(dna));
            entity.setDnaMutant(retorno);
            this.repository.save(entity);
            return retorno;
        } else {
            throw new RuntimeException("Ups. La cadena de adn no es válida!");
        }
    }

    /**
     * Valida que la cadena de dna sea válida.
     * @param dna Candena de dna.
     * @return true si es válida, false en caso contrario.
     */
    private boolean isValidDna(String[] dna) {
        return dna != null && dna.length >= DNAMIN
                && Arrays.stream(dna).allMatch(val -> Pattern.matches("^[ATCG]+$", val) && val.length() == dna.length);
    }

    /**
     * Busca secuencias de cuatro letras iguales.
     * @param val Cadena en la cual se hace la búsqueda.
     * @return true si encontro una secuencia, false en caso contrario.
     */
    private boolean search(String val) {
        boolean search = val.contains("AAAA") || val.contains("TTTT") || val.contains("CCCC") || val.contains("GGGG");
        if (search) {
            log.info(val);
        }
        return search;
    }

    /**
     * Realiza una busqueda de manera horizontal sobre el array de la cadena adn.
     * @param dna Cadena de adn.
     * @return El número de conincidencias que se encuentren.
     */
    private int searchHorizontal(String[] dna) {
        int count = 0;
        for (String val : dna) {
            count += search(val) ? 1 : 0;
            //Si lo encontrado es mayor al umbral, no se hace
            //más búsquedas y se determina que es mutante.
            if (count > UMBRAL) {
                return count;
            }
        }
        return count;
    }

    /**
     * Realiza una búsqueda de manera vertical sobre el array de la cadena adn.
     * @param dna Cadena de adn.
     * @param count Cuenta de la secuencias que han sido encontradas.
     * @return El número de secuencias encontradas como como mutante.
     */
    private int searchVertical(String[] dna, int count) {
        for (int i = 0; i < dna.length; i++) {
            StringBuilder buf = new StringBuilder();
            for (int j = 0; j < dna.length; j++) {
                buf.append(dna[j].charAt(i));
            }
            count += search(buf.toString()) ? 1 : 0;
            //Si lo encontrado es mayor al umbral, no se hace
            //más búsquedas y se determina que es mutante.
            if (count > UMBRAL) {
                return count;
            }
        }
        return count;
    }

    /**
     * Realiza una búsqueda por las diagonales de la cadena de adn.
     * @param dna Cadena de adn.
     * @param count Cuenta de las secuencias que han sido encontradas.
     * @return Cuenta con las secuencias de adn mutante que se encuentre.
     */
    private int searchOblicuo(String[] dna, int count) {
        //Se estable la diferencia entre el maximo de la cadena
        //y mínimo de la secuencia, para mejorar las pasados en los bucles.
        int difference = dna.length - DNAMIN;
        for (int i = difference; i >= 0; i--) {
            StringBuilder buf = new StringBuilder();
            for (int j = 0; j < dna.length - i; j++) {
                buf.append(dna[i + j].charAt(j));
            }
            count += search(buf.toString()) ? 1 : 0;
            //Si lo encontrado es mayor al umbral, no se hace
            //más búsquedas y se determina que es mutante.
            if (count > UMBRAL) {
                return count;
            }
        }
        for (int i = 1; i <= difference; i++) {
            StringBuilder buf = new StringBuilder();
            for (int j = 0; j < dna.length - i; j++) {
                buf.append(dna[j].charAt(i + j));
            }
            count += search(buf.toString()) ? 1 : 0;
            if (count > UMBRAL) {
                return count;
            }
        }
        for (int i = difference + 1; i < dna.length; i++) {
            StringBuilder buf = new StringBuilder();
            for (int j = 0; j <= i; j++) {
                buf.append(dna[i - j].charAt(j));
            }
            count += search(buf.toString()) ? 1 : 0;
            if (count > UMBRAL) {
                return count;
            }
        }
        for (int i = 0; i < difference; i++) {
            StringBuilder buf = new StringBuilder();
            for (int j = i + 1; j < dna.length; j++) {
                buf.append(dna[dna.length - j + i].charAt(j));
            }
            count += search(buf.toString()) ? 1 : 0;
            if (count > UMBRAL) {
                return count;
            }
        }
        return count;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Stats getStats() {
        return this.repository.findStats();
    }

}
