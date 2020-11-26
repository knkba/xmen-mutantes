package co.com.cancaba.xmen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.com.cancaba.xmen.entity.Dna;
import co.com.cancaba.xmen.message.Stats;

/**
 * Repositorio para la tabla dna.
 * @author ccano
 *
 */
public interface DNARepository extends JpaRepository<Dna, String> {
    /**
     * Retorna las estadísticas de la relación mutante humanos.
     * @return Un objeto de tipo {@link Stats}
     */
    @Query(nativeQuery = true, value = "select r.count_mutant_dna as countMutantDna, "
            + "r.count_human_dna as countHumanDna, "
            + "trunc((r.count_mutant_dna + 0.0) / r.count_human_dna,1)  as ratio "
            + "from (select sum(case when dna_mutant = true then 1 else 0 end) as count_mutant_dna, "
            + "count(dna) as count_human_dna from dna) as r")
    Stats findStats();
}
