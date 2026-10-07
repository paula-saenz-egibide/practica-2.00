package org.example.repositories;

import org.example.dao.DoctorDao;
import org.example.daos.DoctorDaoImpl;
import org.example.dao.PatientDao;
import org.example.idao.PatientDaoImpl;
import org.example.irepositories.PatientRepository;
import org.example.models.Doctor;
import org.example.models.Patient;

public class PatientRepositoryImpl implements PatientRepository {

    private PatientDao patientDao = new PatientDaoImpl();
    private DoctorDao doctorDao = new DoctorDaoImpl();

    @Override
    public Patient getPatient(int id) {
        Patient patient = patientDao.getPatient(id);
        if (patient != null) {
            Doctor doctor = doctorDao.getDoctorByPatientId(patient.getId());
            if (doctor != null) {
                doctor.setAttendedPatients(patientDao.getPatientsByDoctorId(doctor.getId()));
            }
            patient.setDoctor(doctor);
        }
        return patient;
    }

    @Override
    public boolean isPatientAttendedByDoctor(int patient_id, int doctor_id) {
        Doctor doctor = doctorDao.getDoctorByPatientId(patient_id);

        // Comprobamos si se encontró el doctor y si coincide su ID con el buscado
        return doctor != null && doctor.getId() == doctor_id;
    }
}