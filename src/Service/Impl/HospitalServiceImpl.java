package Service.Impl;

import Models.Hospital;
import Models.Patient;
import Service.HospitalService;
import dao.Impl.HospitalDaoImpl;
import db.DataBace;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HospitalServiceImpl implements HospitalService {

    DataBace dataBace = new DataBace();
    private final HospitalDaoImpl hospitalDao;

    public HospitalServiceImpl(HospitalDaoImpl hospitalDao) {
        this.hospitalDao = hospitalDao;
    }
    @Override
    public String addHospital(Hospital hospital) {
        hospitalDao.getAll().add(hospital);
        return  "Saktaldy ";
    }

    @Override
    public Hospital findHospitalById(Long id) {
        return hospitalDao.getAll().stream().filter(hospital -> hospital.getId() == id).
                findFirst().orElseThrow(() -> new RuntimeException("saf"));
    }

    @Override
    public List<Hospital> getAllHospital() {
        return hospitalDao.getAll();
    }

    @Override
    public List<Patient> getAllPatientFromHospital(Long id) {
        return hospitalDao.getAll().stream().filter(hospital -> hospital.getId() == id).
                findFirst().orElseThrow(() -> new RuntimeException("hhuih")).getPatients();
    }

    @Override
    public String deleteHospitalById(Long id) {
        hospitalDao.delete(id);
        return "ochty";
    }

    @Override
    public Map<String, Hospital> getAllHospitalByAddress(String address) {
        Map<String,Hospital> map = new HashMap<>();
        for (Hospital hospital : hospitalDao.getAll()) {
            if (hospital.getAddress().equals(address)){
                map.put(address,hospital);
//                System.out.println(map);
            }
        }
        return map;
    }
}
