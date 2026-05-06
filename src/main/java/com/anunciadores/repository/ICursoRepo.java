package  com.anunciadores.repository;

import com.anunciadores.model.Curso;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ICursoRepo extends JpaRepository<Curso, Integer> {
  List<Curso> findTopByOrderByIdDesc();
  
  List<Curso> findByActivo(boolean paramBoolean);
  
  @Query(nativeQuery = true, value = "select * from curso c where c.id not in (select ins.id_curso  from persona p join inscripciones ins on p.id = ins.id_persona WHERE p.id  = ?1 )")
  List<Curso> findByActivos(int paramInt);
  
  @Query(nativeQuery = true, value = "SELECT c.* FROM persona p   join inscripciones ins on p.id = ins.id_persona join curso c on ins.id_curso = c.id  where p.id= ?1 ")
  List<Curso> cursosByIdPersona(int paramInt);
  
  @Query(nativeQuery = true, value = "SELECT c.* FROM persona p  join curso c on p.id  = c.profesor  where p.id= ?1 ")
  List<Curso> cursosByIdProfesor(int paramInt);
}


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\repository\ICursoRepo.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */