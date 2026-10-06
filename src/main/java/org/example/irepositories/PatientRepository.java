package org.example.irepositories;

import org.example.models.Patient;

public interface PatientRepository {
    Patient getPatient(int id);
    boolean isPatientAttendedByDoctor(int patient_id, int doctor_id);
}