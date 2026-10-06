package org.example.repositories;

import org.example.dao.DoctorDao;
import org.example.daos.DoctorDaoImpl;
import org.example.dao.PatientDao;
import org.example.idao.PatientDaoImpl;
import org.example.irepositories.DoctorRepository;
import org.example.models.Doctor;
import org.example.models.Patient;

import java.util.List;

public class DoctorRepositoryImpl implements DoctorRepository {

    private DoctorDao doctorDao = new DoctorDaoImpl();
    private PatientDao patientDao = new PatientDaoImpl();

    @Override
    public Doctor getDoctor(int doctor_id) {
        Doctor doctor = doctorDao.getDoctor(doctor_id);
        if (doctor != null) {
            List<Patient> patients = patientDao.getPatientsByDoctorId(doctor_id);
            doctor.setAttendedPatients(patients);
        }
        return doctor;
    }
}