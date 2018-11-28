package trabalho.controller;

import trabalho.dto.ObjetivoDTO;
import trabalho.entity.Objetivo;
import trabalho.entity.Usuario;
import trabalho.repository.ObjetivoRepository;
import trabalho.repository.UsuarioRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class ObjetivoController {

    @Autowired
    private ObjetivoRepository objetivoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @CrossOrigin(origins = "*")
    @GetMapping(path = "objetivos")
    public ResponseEntity<List<ObjetivoDTO>> list(@RequestHeader("token") String userId) {

        Optional<Usuario> usuario = usuarioRepository.findById(userId);
        if (!usuario.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(objetivoRepository.findByUsuarioId(userId).stream()
                                                        .map(objetivo -> {
                                                            ObjetivoDTO dto = new ObjetivoDTO();
                                                            dto.setId(objetivo.getId());
                                                            dto.setDescricao(objetivo.getDescricao());
                                                            dto.setValorEstipulado(objetivo.getValorEstipulado());
                                                            dto.setValorInvestido(objetivo.getValorInvestido());
                                                            return dto;
                                                        }).collect(Collectors.toList()));
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "objetivos/{id}")
    public ResponseEntity<Objetivo> find(@RequestHeader("token") String user, @PathVariable("id") Integer id) {

        Optional<Usuario> usuario = usuarioRepository.findById(user);
        if (!usuario.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Optional<Objetivo> opt =  objetivoRepository.findByIdAndUsuarioId(id, user);

        return opt.isPresent() ? ResponseEntity.ok(opt.get()) : ResponseEntity.notFound().build();
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "objetivos")
    public ResponseEntity<Objetivo> insert(@RequestHeader("token") String userId, @RequestBody @Valid ObjetivoDTO objetivoDTO) {
        Optional<Usuario> usuario = usuarioRepository.findById(userId);
        if (!usuario.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Objetivo objetivo = new Objetivo();
        objetivo.setDescricao(objetivoDTO.getDescricao());
        objetivo.setValorEstipulado(objetivoDTO.getValorEstipulado());
        objetivo.setValorInvestido(objetivoDTO.getValorInvestido() != null ? objetivoDTO.getValorInvestido() : BigDecimal.ZERO);
        objetivo.setUsuario(usuario.get());

        return ResponseEntity.status(HttpStatus.CREATED).body(objetivoRepository.save(objetivo));
    }

    @CrossOrigin(origins = "*")
    @PutMapping(path = "objetivos/{id}")
    public ResponseEntity<Objetivo> update(@RequestHeader("token") String userId, @PathVariable("id") Integer id, @RequestBody @Valid ObjetivoDTO objetivoDTO) {

        Optional<Usuario> usuario = usuarioRepository.findById(userId);
        if (!usuario.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Optional<Objetivo> objetivoOptional = objetivoRepository.findByIdAndUsuarioId(id, userId);

        if (objetivoOptional.isPresent()) {
            Objetivo objetivo = objetivoOptional.get();

            if (StringUtils.isNotBlank(objetivoDTO.getDescricao()))
                objetivo.setDescricao(objetivoDTO.getDescricao());

            if (objetivoDTO.getValorEstipulado() != null)
                objetivo.setValorEstipulado(objetivoDTO.getValorEstipulado());

            if (objetivoDTO.getValorInvestido() != null)
                objetivo.setValorInvestido(objetivoDTO.getValorInvestido());

            return ResponseEntity.ok(objetivoRepository.save(objetivo));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin(origins = "*")
    @PutMapping(path = "objetivos/{id}/actions/updateValue")
    public ResponseEntity<Objetivo> updateValue(@RequestHeader("token") String userId, @PathVariable("id") Integer id, @RequestBody ObjetivoDTO objetivoDTO) {

        Optional<Usuario> usuario = usuarioRepository.findById(userId);
        if (!usuario.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Optional<Objetivo> objetivoOptional = objetivoRepository.findByIdAndUsuarioId(id, userId);

        if (objetivoOptional.isPresent()) {
            Objetivo objetivo = objetivoOptional.get();

            if (objetivoDTO.getValorInvestido() != null){
                BigDecimal newValue = objetivo.getValorInvestido();
                newValue = newValue.add(objetivoDTO.getValorInvestido());

                objetivo.setValorInvestido(newValue);
            }

            return ResponseEntity.ok(objetivoRepository.save(objetivo));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(path = "objetivos/{id}")
    public ResponseEntity<Objetivo> delete(@RequestHeader("token") String user, @PathVariable("id") Integer id) {

        objetivoRepository.deleteByIdAndUsuarioId(id, user);

        return ResponseEntity.ok().build();
    }
}
