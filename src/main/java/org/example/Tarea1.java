package org.example;

import org.example.irepositories.DoctorRepository;
import org.example.models.Doctor;
import org.example.models.Patient;
import org.example.repositories.DoctorRepositoryImpl;

public class Tarea1 {

    public static void main(String[] args) {
        DoctorRepository doctorRepository = new DoctorRepositoryImpl();

        System.out.println("EJECUTANDO PRUEBAS: TAREA 1 (Doctor)   ");

        // doctor con ID = 1
        Doctor doctor = doctorRepository.getDoctor(1);

        if (doctor != null) {
            System.out.println("\nDoctor encontrado:");
            System.out.println("Nombre: " + doctor.getName() + " " + doctor.getLastname());
            System.out.println("Especialidad: " + doctor.getSpeciality());

            System.out.println("\n--- Lista de pacientes atendidos ---");
            if (doctor.getAttendedPatients() != null && !doctor.getAttendedPatients().isEmpty()) {
                for (Patient p : doctor.getAttendedPatients()) {
                    System.out.println(" • " + p.getName() + " " + p.getLastname() + " | Enfermedad: " + p.getDisease());
                }
            } else {
                System.out.println("Este doctor no tiene pacientes asociados.");
            }

            System.out.println("\nObjeto completo impreso:");
            System.out.println(doctor);
        } else {
            System.out.println("No se encontró ningún doctor con el ID especificado.");
        }
    }
}