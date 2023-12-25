package Service.Impl;

import Models.Department;
import Models.Doctor;
import Models.Hospital;
import Service.DoctorService;
import Service.GenericService;
import dao.Impl.DoctorDaoImpl;
import dao.Impl.HospitalDaoImpl;

import java.net.PortUnreachableException;
import java.util.List;
import java.util.Optional;

public class DoctorServiceImpl implements GenericService<Doctor>, DoctorService {

    private final DoctorDaoImpl doctorDaoImpl;
    private final HospitalDaoImpl hospitalDao;

    public DoctorServiceImpl(DoctorDaoImpl doctorDaoImp, HospitalDaoImpl hospitalDao) {
        this.doctorDaoImpl = doctorDaoImp;
        this.hospitalDao = hospitalDao;
    }

    @Override
    public String add(Long hospitalId, List<Doctor> doctors) {
        doctorDaoImpl.add(hospitalId,doctors);
        return "Saktalby! " ;
    }

    @Override
    public void removeById(Long id) {
        doctorDaoImpl.delete(id);
    }

    @Override
    public String updateById(Long id, Doctor doctor) {
        doctorDaoImpl.updateById(id,doctor);
        return " almashty";
    }


    @Override
    public Doctor findDoctorById(Long id) {
        Doctor doctor1 = hospitalDao.getAll().stream().flatMap(hospital -> hospital.getDoctors().
                        stream().filter(doctor -> doctor.getId() == id)).findFirst().
                orElseThrow(() -> new RuntimeException(" Not found id!!"));
        return doctor1;
    }

    @Override
    public String assignDoctorToDepartment(Long departmentId, List<Long> doctorsId) {
        for (Hospital hospital : hospitalDao.getAll()) {
            for (Department department : hospital.getDepartments()) {
                if (departmentId.equals(department.getId())){
                    for (Doctor doctor : hospital.getDoctors()) {

                        if (doctorsId.contains(doctor.getId())){
                            department.getDoctors().add(doctor);
                            return "Successful!";
                        }
                        throw new RuntimeException("Doctor not found! ");
                    }
                    throw  new RuntimeException("Departments not found !");
                }
            }
        }

        return null;
    }

    @Override
    public List<Doctor> getAllDoctorsByHospitalId(Long id) {
        return hospitalDao.getAll().stream()
                .filter(hospital -> hospital.getId()==id).findFirst().
                orElseThrow(()->new RuntimeException(" Not found id !! ")).getDoctors();
    }

    @Override
    public List<Doctor> getAllDoctorsByDepartmentId(Long id) {
      return  hospitalDao.getAll().stream().flatMap(hospital -> hospital.getDepartments().stream().filter(department -> department.getId() == id)).
              findFirst().orElseThrow(()-> new RuntimeException(" Department not id !! ")).getDoctors();

    }
}
