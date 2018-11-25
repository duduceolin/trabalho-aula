package trabalho.repository;

import trabalho.entity.Objetivo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ObjetivoRepository extends CrudRepository<Objetivo, Integer> {
    List<Objetivo> findByUsuarioId(Integer usuarioId);

    Optional<Objetivo> findByIdAndUsuarioId(Integer id, Integer usuarioId);

    void deleteByIdAndUsuarioId(Integer id, Integer usuarioId);
}
