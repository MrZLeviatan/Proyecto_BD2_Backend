package co.edu.uniquindio.proyecto_bd2_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import co.edu.uniquindio.proyecto_bd2_backend.models.Examen;

import java.util.List;

@Repository
public interface ExamenRepository extends JpaRepository<Examen, Long>{

    @Query(value = "SELECT ID_TEMA, TITULO FROM TEMA", nativeQuery = true)
    List<Object[]> obtenerCursos();
}
