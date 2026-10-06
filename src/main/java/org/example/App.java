package org.example;

import org.example.irepositories.PatientRepository;
import org.example.models.Patient;
import org.example.repositories.PatientRepositoryImpl;

public class App {

    public static void main(String[] args) {
        PatientRepository patientRepository = new PatientRepositoryImpl();

        System.out.println("--- Prueba del Patrón Repositorio ---");

        Patient patient = patientRepository.getPatient(1);

        if (patient != null) {
            System.out.println("Paciente obtenido exitosamente:");
            System.out.println("Nombre del Paciente: " + patient.getName() + " " + patient.getLastname());

            if (patient.getDoctor() != null) {
                System.out.println("Atendido por el Doctor: " + patient.getDoctor().getName() + " " + patient.getDoctor().getLastname() + " (" + patient.getDoctor().getSpeciality() + ")");
            } else {
                System.out.println("Este paciente no tiene asignado ningún doctor.");
            }

            System.out.println("\nObjeto paciente completo: " + patient);
        } else {
            System.out.println("No se encontró ningún paciente con el ID proporcionado.");
        }
    }
}