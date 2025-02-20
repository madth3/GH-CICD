package mx.lania.taller.repositorios;

import mx.lania.taller.entidades.Entrada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface RepositorioEntradas extends JpaRepository<Entrada,Integer> {
    List<Entrada> findByEstatus(String st);

    @Query(value = "SELECT placas,fecha_ingreso FROM entradas WHERE estatus = :status",
            nativeQuery = true)
    List<Entrada> buscarPorEstatus(@Param("status") String st);

    @Query(value = "SELECT DATE_TRUNC(MONTH,FECHA_SALIDA) AS FECHA_SALIDA," +
            " COUNT(ENTRADA_ID) AS TOTAL" +
            " FROM ENTRADAS" +
            " GROUP BY DATE_TRUNC(MONTH,FECHA_SALIDA)" +
            " ORDER BY DATE_TRUNC(MONTH,FECHA_SALIDA)", nativeQuery = true)
    List<Object[]> obtenerTotalesPorMes();

    @Modifying
    @Query(value = "UPDATE entradas SET fecha_salida = :fecha" +
            " WHERE fecha_salida IS NULL", nativeQuery = true)
    int cerrarEntradasPendientes(@Param("fecha")LocalDateTime fSalida);
}
