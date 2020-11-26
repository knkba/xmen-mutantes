package co.com.cancaba.xmen.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import co.com.cancaba.xmen.message.DNARequest;
import co.com.cancaba.xmen.service.impl.MutantServiceImpl;

@ExtendWith(MockitoExtension.class)
public class XMenControllerTest {
    @Mock
    private MutantServiceImpl service;
    @InjectMocks
    private XMenController controller;

    @Test
    @DisplayName("isMutant.")
    public void isMutant() {
        DNARequest dna = new DNARequest();
        when(this.service.isMutant(dna.getDna())).thenReturn(true);
        assertEquals(this.controller.isMutant(dna).getStatusCode(), HttpStatus.OK);
    }

    @Test
    @DisplayName("isMutant.")
    public void isMutant1() {
        DNARequest dna = new DNARequest();
        when(this.service.isMutant(dna.getDna())).thenReturn(false);
        assertEquals(this.controller.isMutant(dna).getStatusCode(), HttpStatus.FORBIDDEN);
    }

    @Test
    @DisplayName("isMutant.")
    public void isMutant2() {
        DNARequest dna = new DNARequest();
        when(this.service.isMutant(dna.getDna())).thenThrow(RuntimeException.class);
        assertEquals(this.controller.isMutant(dna).getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    @DisplayName("stats.")
    public void stats() {
        when(this.service.getStats()).thenThrow(RuntimeException.class);
        assertEquals(this.controller.stats().getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    @DisplayName("stats1.")
    public void stats1() {
        when(this.service.getStats()).thenReturn(null);
        assertEquals(this.controller.stats().getStatusCode(), HttpStatus.OK);
    }

}
