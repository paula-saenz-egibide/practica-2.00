package org.example.daos;

import org.example.dao.PatientDao;
import org.example.models.Patient;
import org.example.utils.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientDaoImpl implements PatientDao {

    private boolean patientExists(int id) {
        return getPatient(id) != null;
    }

    @Override
    public Patient getPatient(int id) {
        String query = "select * from patients where id=?";
        PreparedStatement ps = null;
        Patient patient = null;

        try {
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                patient = new Patient(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("lastname"),
                        rs.getString("dni"),
                        rs.getInt("age"),
                        rs.getString("phone"),
                        rs.getString("disease")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patient;
    }

    @Override
    public List<Patient> getPatients() {
        String query = "select * from patients";
        PreparedStatement ps;
        List<Patient> patients = new ArrayList<>();

        try {
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Patient patient = new Patient(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("lastname"),
                        rs.getString("dni"),
                        rs.getInt("age"),
                        rs.getString("phone"),
                        rs.getString("disease")
                );
                patients.add(patient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patients;
    }

    @Override
    public int add(Patient patient) {
        String query = "insert into patients (name, lastname, dni, age, phone, disease) values (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps;
        int rs = 0;

        try {
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
            ps.setString(1, patient.getName());
            ps.setString(2, patient.getLastname());
            ps.setString(3, patient.getDni());
            ps.setInt(4, patient.getAge());
            ps.setString(5, patient.getPhone());
            ps.setString(6, patient.getDisease());

            rs = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }

    @Override
    public boolean update(Patient patient) {
        if (patientExists(patient.getId())) {
            String query = "update patients set name=?, lastname=?, dni=?, age=?, phone=?, disease=? where id=?";
            PreparedStatement ps;
            int rs = 0;

            try {
                ps = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
                ps.setString(1, patient.getName());
                ps.setString(2, patient.getLastname());
                ps.setString(3, patient.getDni());
                ps.setInt(4, patient.getAge());
                ps.setString(5, patient.getPhone());
                ps.setString(6, patient.getDisease());
                ps.setInt(7, patient.getId());

                rs = ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return (rs > 0);
        }

        return false;
    }

    @Override
    public void delete(int id) {
        String query = "delete from patients where id=?";
        PreparedStatement ps;

        try {
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}