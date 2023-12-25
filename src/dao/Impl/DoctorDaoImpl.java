package dao.Impl;

import Models.Doctor;
import Models.Hospital;
import dao.DaoGroup;
import db.DataBace;

import java.util.List;

public class DoctorDaoImpl implements DaoGroup<Doctor,Long> {
    private final DataBace dataBace;

    public DoctorDaoImpl(DataBace dataBace) {
        this.dataBace = dataBace;
    }

    @Override
    public boolean add(Long hospitalId, List<Doctor> doctors) {
        dataBace.getHospitalList().stream().filter(hospital -> hospital.getId() == hospitalId).
                findFirst().orElseThrow(() -> new RuntimeException("dsf")).setDoctors(doctors);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        dataBace.getHospitalList().removeIf(hospital -> hospital.getDoctors().removeIf(doctor -> doctor.getId() == id));
        return true;
    }

    @Override
    public String updateById(Long id, Doctor doctor) {
        Doctor doctor2 = dataBace.getHospitalList().stream().flatMap(hospital -> hospital.getDoctors().stream().filter(doctor1 -> doctor1.getId() == id)).findFirst().orElseThrow(() -> new RuntimeException("fgdgd"));
        doctor2.setFirsName(doctor.getFirsName());
//        doctor2.setGender(doctor.getGender());
//        doctor2.setLastName(doctor.getLastName());
//        doctor2.setExperienceYear(doctor.getExperienceYear());
        dataBace.getHospitalList().stream().flatMap(hospital -> hospital.getDoctors().stream().filter(doctor1 -> doctor1.getId() == id))
                .findFirst().orElseThrow(() -> new RuntimeException(" Not fount id or doctor !!")).setFirsName(doctor.getFirsName());
        return "Almashty ";
    }

    @Override
    public List<Doctor> getAll() {
        for (Hospital hospital : dataBace.getHospitalList()) {
            return hospital.getDoctors();
        }
        throw new RuntimeException(" Incorrect type");
    }
}