package dao.Impl;

import Models.Hospital;
import Models.Patient;
import dao.DaoGroup;
import db.DataBace;

import java.util.List;

public class PatientDaoImpl implements DaoGroup<Patient,Long> {

    private final DataBace dataBace;

    public PatientDaoImpl(DataBace dataBace) {
        this.dataBace = dataBace;
    }

    @Override
    public boolean add(Long hospitalId, List<Patient> patients) {
          dataBace.getHospitalList().stream().filter(hospital -> hospital.getId() == hospitalId).findFirst().
                orElseThrow(()->new RuntimeException(" Not fount hospital id !!")).setPatients(patients);
      return true;
    }

    @Override
    public boolean delete(Long id) {
        dataBace.getHospitalList().removeIf(hospital -> hospital.getPatients().removeIf(patient -> patient.getId()== id));
        return true;
    }

    @Override
    public String updateById(Long id, Patient patient) {
        dataBace.getHospitalList().stream().flatMap(hospital -> hospital.getPatients().stream().
                filter(patient1 -> patient1.getId() == id)).findFirst().orElseThrow(()-> new RuntimeException("Not fount patient id !!")).setFirsName(patient.getFirsName());
        return "almashty" ;
    }
    @Override
    public List<Patient> getAll() {
        for (Hospital hospital : dataBace.getHospitalList()) {
            return hospital.getPatients();
        }
        throw new RuntimeException(" Incorrect type");
    }
}
