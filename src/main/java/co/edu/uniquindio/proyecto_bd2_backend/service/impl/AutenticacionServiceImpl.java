package co.edu.uniquindio.proyecto_bd2_backend.service.impl;

import co.edu.uniquindio.proyecto_bd2_backend.Dto.LoginDTO;
import co.edu.uniquindio.proyecto_bd2_backend.service.AutenticacionService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class AutenticacionServiceImpl implements AutenticacionService {


    private final EntityManager entityManager;

    @Transactional
    @Override
    public Character login(LoginDTO user) {

        StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("login");

        storedProcedure.registerStoredProcedureParameter("id", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("rol", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("res", Character.class, ParameterMode.OUT);

        storedProcedure.setParameter("id", user.id());
        storedProcedure.setParameter("rol", user.rol());

        storedProcedure.execute();

        return (Character) storedProcedure.getOutputParameterValue("res");
    }
}
