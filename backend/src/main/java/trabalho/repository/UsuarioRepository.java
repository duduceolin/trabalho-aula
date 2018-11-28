package trabalho.repository;

import trabalho.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuario, String> {

    List<Usuario> findAll();

    Usuario findByUsuarioAndSenha(String usuario, String password);

    Optional<Usuario> findByUsuario(String usuario);
}
