package co.com.cancaba.xmen.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import co.com.cancaba.xmen.message.Stats;
import co.com.cancaba.xmen.repository.DNARepository;

@ExtendWith(MockitoExtension.class)
public class MutantServiceImplTest {
    @InjectMocks
    MutantServiceImpl service;

    @Mock
    private DNARepository repository;

    @Test
    @DisplayName("isMutant.")
    public void isMutant() {
        String[] dna = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        boolean result = service.isMutant(dna);
        assertTrue(result);
    }

    @Test
    @DisplayName("isMutant1.")
    public void isMutant1() {
        String[] dna = {"ACGC", "AACG", "TCAC", "CTAA"};
        boolean result = service.isMutant(dna);
        assertTrue(result);
    }

    @Test
    @DisplayName("isMutant2.")
    public void isMutant2() {
        String[] dna = {"ACGC", "AACG", "TCAC", "CTA"};
        assertThrows(RuntimeException.class, () -> {
            service.isMutant(dna);
        });
    }

    @Test
    @DisplayName("isMutant3.")
    public void isMutant3() {
        String[] dna = {"AAAA", "AACG", "CCCC", "CTAA"};
        boolean result = service.isMutant(dna);
        assertTrue(result);
    }

    @Test
    @DisplayName("isMutant4.")
    public void isMutant4() {
        String[] dna = {"ATGC", "CCGG", "AATT", "CCGA"};
        boolean result = service.isMutant(dna);
        assertFalse(result);
    }

    @Test
    @DisplayName("isMutant5.")
    public void isMutant5() {
        String[] dna = {"AAAA", "CAGC", "TTAC", "CCGA"};
        boolean result = service.isMutant(dna);
        assertTrue(result);
    }

    @Test
    @DisplayName("isMutant6.")
    public void isMutant6() {
        String[] dna = {"ACGTA", "TGCAC", "GTACC", "CAGAC", "CAGAC"};
        boolean result = service.isMutant(dna);
        assertTrue(result);
    }

    @Test
    @DisplayName("stats.")
    public void stats() {
        when(this.repository.findStats()).thenReturn(null);
        Stats result = service.getStats();
        assertTrue(result == null);
    }

}
