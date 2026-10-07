package org.example;

import org.example.irepositories.DoctorRepository;
import org.example.irepositories.PatientRepository;
import org.example.models.Doctor;
import org.example.models.Patient;
import org.example.repositories.DoctorRepositoryImpl;
import org.example.repositories.PatientRepositoryImpl;

public class Tarea3 {

    public static void main(String[] args) {
        System.out.println("   EJECUTANDO PRUEBAS: TAREA 3 (SQLite)   ");

        DoctorRepository doctorRepository = new DoctorRepositoryImpl();
        PatientRepository patientRepository = new PatientRepositoryImpl();

        // 1. Probar lectura del doctor desde SQLite
        Doctor doctor = doctorRepository.getDoctor(1);
        System.out.println("\nDoctor recuperado desde SQLite:");
        System.out.println(doctor);

        // 2. Probar lectura de relación paciente-doctor desde SQLite
        Patient patient = patientRepository.getPatient(1);
        System.out.println("\nPaciente recuperado desde SQLite:");
        System.out.println(patient);
    }
}