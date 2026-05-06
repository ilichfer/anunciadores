package  com.anunciadores.repository;

import com.anunciadores.model.NotasCurso;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface INotasCursoRepo extends JpaRepository<NotasCurso, Integer> {
  @Query(nativeQuery = true, value = "select nc.* from curso c join notas_curso nc on c.id  = nc.id_curso where nc.id_curso = :idCurso and nc.id_persona = :idPersona ")
  NotasCurso findNotasByCurso(int paramInt1, int paramInt2);
  
  @Query(nativeQuery = true, value = "select nc.* from notas_curso nc where nc.id_persona = :idPersona ")
  Optional<List<NotasCurso>> findHistoricoNotas(int paramInt);
}


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\repository\INotasCursoRepo.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */