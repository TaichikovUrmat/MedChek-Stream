package Service.Impl;

import Models.Hospital;
import Models.Patient;
import Service.GenericService;
import Service.PatientService;
import dao.Impl.HospitalDaoImpl;
import dao.Impl.PatientDaoImpl;

import java.util.*;
import java.util.stream.Collectors;

public class PatientServiceImpl implements GenericService<Patient>, PatientService {

    private final PatientDaoImpl patientDao;
    private final HospitalDaoImpl hospitalDao;

    public PatientServiceImpl(PatientDaoImpl patientDao, HospitalDaoImpl hospitalDao) {
        this.patientDao = patientDao;
        this.hospitalDao = hospitalDao;
    }


    @Override
    public String add(Long hospitalId, List<Patient> patients) {
        patientDao.add(hospitalId,patients);
        return "Saktaldy";
    }

    @Override
    public void removeById(Long id) {
        patientDao.delete(id);
    }

    @Override
    public String updateById(Long id, Patient patient) {
        patientDao.updateById(id,patient);

        return "almashty";
    }

    @Override
    public String addPatientsToHospital(Long id, List<Patient> patients) {
        patientDao.add(id,patients);
        return "saktaldy";
    }

    @Override
    public Patient getPatientById(Long id) {
        Patient patient1 = hospitalDao.getAll().stream().flatMap(hospital -> hospital.getPatients().stream().filter(patient -> patient.getId() == id)).
                findFirst().orElseThrow(() -> new RuntimeException("Not fount patient id !! "));
             return  patient1;
    }
    @Override
    public Map<Integer, List<Patient>> getPatientByAge() {
        return patientDao.getAll().stream().collect(Collectors.groupingBy(Patient::getAge));
    }

    @Override
    public List<Patient> sortPatientsByAge(String ascOrDesc) {
        LinkedList<Patient> patients = new LinkedList<>(patientDao.getAll());
        if ("asc".equalsIgnoreCase(ascOrDesc)){
            patients.sort(Patient.sortByPatientName);
        }else {
            patients.sort(Patient.sortByPatientName.reversed());
        }
        return patients;
    }
}
