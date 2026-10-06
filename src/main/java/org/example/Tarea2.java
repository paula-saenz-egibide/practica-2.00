package org.example;

import org.example.irepositories.PatientRepository;
import org.example.repositories.PatientRepositoryImpl;

public class Tarea2 {

    public static void main(String[] args) {
        PatientRepository patientRepository = new PatientRepositoryImpl();

        System.out.println("   EJECUTANDO PRUEBAS: TAREA 2 (Atendido) ");
        int patientId = 1;
        int doctorIdCorrecto = 1;
        int doctorIdIncorrecto = 99;

        // Comprobación 1: Caso en el que SÍ coinciden
        boolean fueAtendido1 = patientRepository.isPatientAttendedByDoctor(patientId, doctorIdCorrecto);
        System.out.println("¿El paciente " + patientId + " fue atendido por el doctor " + doctorIdCorrecto + "?: " + fueAtendido1);

        // Comprobación 2: Caso en el que NO coinciden
        boolean fueAtendido2 = patientRepository.isPatientAttendedByDoctor(patientId, doctorIdIncorrecto);
        System.out.println("¿El paciente " + patientId + " fue atendido por el doctor " + doctorIdIncorrecto + "?: " + fueAtendido2);
    }
}