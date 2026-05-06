package com.anunciadores.service;

import com.anunciadores.auth.dto.LoginRequest;
import com.anunciadores.auth.dto.LoginResponse;
import com.anunciadores.model.Persona;
import com.anunciadores.model.RolPersona;
import com.anunciadores.repository.IPersonaRepo;
import com.anunciadores.repository.IRolesPersonaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class AuthService {

    @Autowired
    private IPersonaRepo personaRepo;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private IRolesPersonaRepo rolesPersonaRepo;

    public LoginResponse login(LoginRequest request) {

        // 1. Convertir cédula de String a Integer
        Integer cedula;
        try {
            cedula = Integer.parseInt(request.getCedula());
        } catch (NumberFormatException e) {
            throw new RuntimeException("La cédula debe ser un número válido");
        }

        // 2. Buscar persona por documento
        Persona persona = personaRepo.findByDocumento(cedula);
        if (persona == null) {
            throw new RuntimeException("Cédula o contraseña incorrectos");
        }

        // 3. Verificar que la cuenta esté activa
        if (persona.getEstado() == null || !persona.getEstado()) {
            throw new RuntimeException("Tu cuenta está inactiva. Contacta al administrador.");
        }

        // 4. Verificar contraseña con MD5
        String passwordMd5 = toMd5(request.getPassword());
        if (!passwordMd5.equals(persona.getPassword())) {
            throw new RuntimeException("Cédula o contraseña incorrectos");
        }

        // 5. Rol — sin campo rol en la entidad, asignamos ADMIN por defecto
        List<RolPersona> roldb = rolesPersonaRepo.findRolByidPersona(persona.getId());
        RolPersona rolP = roldb.get(0);
        String rol = (rolP.getIdRol() == 1 ? "ADMIN" : "USER");

        // 6. Generar token
        String nombreCompleto = persona.getNombre() + " " + persona.getApellido();
        String token = jwtService.generarToken(
                String.valueOf(persona.getDocumento()),
                rol,
                nombreCompleto
        );

        // 7. Retornar respuesta
        return new LoginResponse(token, rol, nombreCompleto, persona.getId().longValue());
    }

    // ─── Convierte un String a su hash MD5 ───────────────────────────────────
    private String toMd5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hash = number.toString(16);
            // Asegura que siempre tenga 32 caracteres con ceros a la izquierda
            while (hash.length() < 32) {
                hash = "0" + hash;
            }
            return hash;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al procesar la contraseña");
        }
    }
}
