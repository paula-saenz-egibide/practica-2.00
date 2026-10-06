package org.example.daos;

import org.example.dao.DoctorDao;
import org.example.models.Doctor;
import org.example.utils.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorDaoImpl implements DoctorDao {

    private boolean doctorExists(int id) {
        return getDoctor(id) != null;
    }

    @Override
    public Doctor getDoctor(int id) {
        String query = "select * from doctors where id=?";
        PreparedStatement ps = null;

        Doctor doctor = null;

        try {
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                doctor = new Doctor(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("lastname"),
                        rs.getString("dni"),
                        rs.getDouble("salary"),
                        rs.getString("speciality")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return doctor;
    }

    @Override
    public boolean update(Doctor doctor) {
        if (doctorExists(doctor.getId())) {
            String query = "update doctors set name=?, lastname=?, dni=?, salary=?, speciality=? where id=?";
            PreparedStatement ps;
            int rs = 0;

            try {
                ps = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
                ps.setString(1, doctor.getName());
                ps.setString(2, doctor.getLastname());
                ps.setString(3, doctor.getDni());
                ps.setDouble(4, doctor.getSalary());
                ps.setString(5, doctor.getSpeciality());
                ps.setInt(6, doctor.getId());

                rs = ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return (rs > 0);
        }

        return false;
    }

    @Override
    public int add(Doctor doctor) {
        String query = "insert into doctors (name, lastname, dni, salary, speciality) values (?, ?, ?, ?, ?)";
        PreparedStatement ps;
        int rs = 0;

        try {
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
            ps.setString(1, doctor.getName());
            ps.setString(2, doctor.getLastname());
            ps.setString(3, doctor.getDni());
            ps.setDouble(4, doctor.getSalary());
            ps.setString(5, doctor.getSpeciality());

            rs = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }

    @Override
    public void delete(int id) {
        String query = "delete from doctors where id=?";
        PreparedStatement ps;

        try {
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Doctor> getDoctors() {
        String query = "select * from doctors";
        PreparedStatement ps;
        List<Doctor> doctors = new ArrayList<>();

        try {
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Doctor doctor = new Doctor(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("lastname"),
                        rs.getString("dni"),
                        rs.getDouble("salary"),
                        rs.getString("speciality")
                );
                doctors.add(doctor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return doctors;
    }

    @Override
    public Doctor getDoctorByPatientId(int patient_id) {
        String query = "SELECT d.* FROM doctors d JOIN patients p ON d.id = p.doctor_id WHERE p.id = ?";
        PreparedStatement ps = null;
        Doctor doctor = null;

        try {
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
            ps.setInt(1, patient_id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                doctor = new Doctor(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("lastname"),
                        rs.getString("dni"),
                        rs.getDouble("salary"),
                        rs.getString("speciality")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return doctor;
    }
}