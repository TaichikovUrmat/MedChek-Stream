import Models.*;
import Service.DepartmentService;
import Service.DoctorService;
import Service.HospitalService;
import Service.Impl.DepartmentServiceImpl;
import Service.Impl.DoctorServiceImpl;
import Service.Impl.HospitalServiceImpl;
import Service.Impl.PatientServiceImpl;
import Service.PatientService;
import dao.Impl.DepartmentDaoImpl;
import dao.Impl.DoctorDaoImpl;
import dao.Impl.HospitalDaoImpl;
import dao.Impl.PatientDaoImpl;
import db.DataBace;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        DataBace dataBace = new DataBace();
        HospitalDaoImpl hospitalDao = new HospitalDaoImpl(dataBace);
        HospitalService hossss = new HospitalServiceImpl(hospitalDao);

        DoctorDaoImpl doctorDaoImpl = new DoctorDaoImpl(dataBace);
        DoctorService doctor = new DoctorServiceImpl(doctorDaoImpl, hospitalDao);

        PatientDaoImpl patientDao = new PatientDaoImpl(dataBace);
        PatientService patientService = new PatientServiceImpl(patientDao, hospitalDao);

        DepartmentDaoImpl departmentDao = new DepartmentDaoImpl(dataBace);
        DepartmentService departmentService = new DepartmentServiceImpl(departmentDao, hospitalDao);

        ArrayList<Doctor> doctorArrayListt = new ArrayList<>(List.of(
                new Doctor("Urmat", "Taichikov", Genders.Male, 4),
                new Doctor("Jigit","Turumbekov",Genders.Male,2),
                new Doctor("Myrzaйym","Keldibrkova",Genders.Female,5)
        ));
        ArrayList<Department> departmentArrayList = new ArrayList<>(List.of(
                new Department("14-Чирургия болуму"),
                new Department("11-Кабыл алуу болуму"),
                new Department("10-Кан алуу болуму")
        ));
        ArrayList<Patient> patientArrayList = new ArrayList<>(List.of(
                new Patient("Mirlan", "Arstanov", 22, Genders.Male),
                new Patient("Ajybek","Sadykov",17,Genders.Male),
                new Patient("Nurlan","Abidilaev",19,Genders.Male),
                new Patient("Nurkamil","Kamchiev",22,Genders.Male)
        ));

        Hospital hosp = new Hospital("Бишкек город hospital", "Chyu");
        hossss.addHospital(hosp);

        Department department = new Department();
        department.setDepartmentName("Стамалотогия");

        Doctor doctor1 = new Doctor();
        doctor1.setFirsName("Samat");
        Patient patient = new Patient();
        patient.setFirsName("Kanat");


        System.out.println("""
                Hospital:                                                  Doctor:
                  1. addHospital(Hospital hospital);                           7. Doctor findDoctorById(Long id);
                  2. findHospitalById(Long id);                                8. String assignDoctorToDepartment(Long departmentId, List<Long> doctorsId);
                  3. getAllHospital();                                         9. List<Doctor> getAllDoctorsByHospitalId(Long id);
                  4. getAllPatientFromHospital(Long id);                       10. List<Doctor> getAllDoctorsByDepartmentId(Long id);
                  5. deleteHospitalById(Long id);                              11. String add(Long  hospitalId, List<T> t);
                  6. getAllHospitalByAddress(String address);                  12. void removeById(Long id);
                                                                               13. String updateById(Long id, T t);  
                                                                               
                                                                               
                Patient:                                                   
                  14. add(Long hospitalId, List<Patient> t)                Department:                                                                      
                  15. void removeById(Long id)                                  21.getAllDepartmentByHospital(Long id);                                                                      
                  16. updateById(Long id, Patient patient)                      22.findDepartmentByName(String name);
                  17. addPatientsToHospital(Long id, List<Patient> patients);   23.add(Long  hospitalId, List<T> t);                                                                 
                  18. getPatientById(Long id);                                  24.removeById(Long id);
                  19. Map<Integer, Patient> getPatientByAge();                  25.updateById(Long id, T t);
                  20. List<Patient> sortPatientsByAge(String ascOrDesc);           
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          
                          """);

        while (true) {
            switch (new Scanner(System.in).nextLine()) {
                /// hospital
                case "1" -> System.out.println(" Koshuldu");
                case "2" -> {try {
                        System.out.println(hossss.findHospitalById(1L));
                    }catch (RuntimeException e){
                        System.out.println(e.getMessage());
                    }
                }
                case "3" -> System.out.println(hossss.getAllHospital());
                case "4" ->{try {
                        System.out.println(hossss.getAllPatientFromHospital(1L));
                    }catch (RuntimeException e){
                        System.out.println(e.getMessage());
                    }
                }
                case "5" -> {
                    try {
                        System.out.println(hossss.deleteHospitalById(1L));
                    }catch (RuntimeException e){
                        System.out.println(e.getMessage());
                    }
                }
                case "6" -> System.out.println(hossss.getAllHospitalByAddress("Chyu"));

                ///  Doctors
                case "7" -> {
                   try {
                       System.out.println(doctor.findDoctorById(1L));
                   }catch (RuntimeException e){
                       System.out.println(e.getMessage());
                   }
                }
                case "8" -> System.out.println(doctor.assignDoctorToDepartment(1L, Collections.singletonList(1L)));
                case "9" -> {
                    try {
                        System.out.println(doctor.getAllDoctorsByHospitalId(1L));
                    }catch (RuntimeException e){
                        System.out.println(e.getMessage());
                    }
                }
                case "10" ->{
                    try {
                        System.out.println(doctor.getAllDoctorsByDepartmentId(1L));
                    }catch (RuntimeException e){
                        System.out.println(e.getMessage());
                    }
                }
                case "11" -> {
                    try {
                        System.out.println(doctorDaoImpl.add(1L, doctorArrayListt));
                    }catch (RuntimeException e){
                        System.out.println(e.getMessage());
                    }
                }
                case "12" -> {
                    try {
                        System.out.println(doctorDaoImpl.delete(1L));
                    }catch (RuntimeException e){
                        System.out.println(e.getMessage());
                    }
                }
                case "13"-> { try {
                    System.out.println(doctorDaoImpl.updateById(1L,doctor1));
                }catch (RuntimeException e){
                    System.out.println(e.getMessage());
                }
                    }


                /// Patients
                case "14" -> {
                    try {
                        System.out.println(patientDao.add(1L, patientArrayList));
                    }catch (RuntimeException e){
                        System.out.println(e.getMessage());
                    }
                }
                case "15" ->{
                    try {
                        System.out.println(patientDao.delete(1L));
                    }catch (RuntimeException e){
                        System.out.println(e.getMessage());
                    }
                }
                case "16"->{
                    try {
                        System.out.println(patientDao.updateById(1L,patient));
                    }catch (RuntimeException e){
                        System.out.println(e.getMessage());
                    }
                }
                case "17" -> {
                    try {
                        System.out.println(patientService.addPatientsToHospital(1L, patientArrayList));
                    }catch (RuntimeException e){
                        System.out.println(e.getMessage());
                    }
                }
                case "18" ->{
                   try {
                       System.out.println(patientService.getPatientById(1L));
                   }catch (RuntimeException e){
                       System.out.println(e.getMessage());
                   }
                }
                case "19" ->{
                    try {
                        System.out.println(patientService.getPatientByAge());
                    }catch (RuntimeException e){
                        System.out.println(e.getMessage());
                    }
                }
                case "20" -> System.out.println(patientService.sortPatientsByAge("asc"));


                ////// Department
                case "21" -> {
                    try {
                        System.out.println(departmentService.getAllDepartmentByHospital(1L));
                    }catch (RuntimeException e){
                        System.out.println(e.getMessage());
                    }
                }
                case "22" ->{
                    try {
                        System.out.println(departmentService.findDepartmentByName("14-Чирургия болуму"));
                    }catch (RuntimeException e){
                        System.out.println(e.getMessage());
                    }
                }
                case "23" -> {
                    try {
                        System.out.println(departmentDao.add(1L, departmentArrayList));
                    }catch (RuntimeException e){
                        System.out.println(e.getMessage());
                    }
                }
                case "24" -> {
                    try {
                        System.out.println(departmentDao.delete(1L));
                    }catch (RuntimeException e){
                        System.out.println(e.getMessage());
                    }
                }
                case "25"->{
                    try {
                        System.out.println(departmentDao.updateById(1L, department));
                    }catch (RuntimeException e){
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }
        }

