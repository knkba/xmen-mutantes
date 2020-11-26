package co.com.cancaba.xmen.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.cancaba.xmen.message.DNARequest;
import co.com.cancaba.xmen.message.Stats;
import co.com.cancaba.xmen.service.MutantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * Api controller para el servicio de detección de mutantes.
 * @author ccano
 *
 */
@Api(value = "XMEN", description = "API la detección de mutantes.", tags = {"XMEN"})
@RequestMapping("${app.context-api}/mutant")
@RestController
public class XMenController {
    /**
     * Servicio.
     */
    private MutantService service;

    /**
     * Constructor.
     * @param service Servicio inyectado.
     */
    public XMenController(MutantService service) {
        this.service = service;
    }

    /**
     * Api para establecer si una cadena de adn ingresada es mutante o no.
     * @param dna {@link DNARequest}.
     * @return {@link ResponseEntity}.
     */
    @ApiOperation(value = "isMutant.", nickname = "isMutant", notes = "Detecta si un humano es mutante")
    @PostMapping(path = "/", consumes = "application/json")
    public ResponseEntity<Void> isMutant(
            @ApiParam(required = true, value = "dna a verificar") @RequestBody @Validated DNARequest dna) {
        try {
            if (this.service.isMutant(dna.getDna())) {
                return new ResponseEntity<Void>(HttpStatus.OK);
            } else {
                return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
            }
        } catch (Exception ex) {
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Obtiene la estadísticas de la relación mutantes humanos.
     * @return Un objeto de tipo {@link Stats}
     */
    @ApiOperation(value = "stats.", nickname = "stats", notes = "Obtiene las estadísticas")
    @GetMapping(path = "/stats", produces = "application/json")
    public ResponseEntity<?> stats() {
        try {
            return new ResponseEntity<Stats>(this.service.getStats(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
