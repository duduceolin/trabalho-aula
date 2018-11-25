package trabalho.controller;


import com.sun.javaws.exceptions.InvalidArgumentException;
import trabalho.dto.UsuarioDTO;
import trabalho.entity.Usuario;
import trabalho.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(value = "usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/{userId}")
    public ResponseEntity<Usuario> find(@PathVariable("userId") Integer userId) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(userId);
        if (usuarioOptional.isPresent()) {
            return ResponseEntity.ok(usuarioOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/")
    public ResponseEntity<Usuario> insert(@RequestBody @Valid UsuarioDTO usuarioDTO) {

        if (usuarioRepository.findByUsuario(usuarioDTO.getUsuario()).isPresent())
            return ResponseEntity.unprocessableEntity().build();

        Usuario usuario = new Usuario();

        usuario.setNome(usuarioDTO.getNome());
        usuario.setUsuario(usuarioDTO.getUsuario());
        usuario.setSenha(usuarioDTO.getSenha());

        Usuario usuarioSalvo =  usuarioRepository.save(usuario);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo);
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/actions/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Usuario> login(@RequestBody @Valid UsuarioDTO usuarioDTO) {
        Optional<Usuario> usuario = Optional.ofNullable(usuarioRepository.findByUsuarioAndSenha(usuarioDTO.getUsuario(), usuarioDTO.getSenha()));

        return usuario.isPresent() ? ResponseEntity.ok(usuario.get()) : ResponseEntity.notFound().build();
    }


}
