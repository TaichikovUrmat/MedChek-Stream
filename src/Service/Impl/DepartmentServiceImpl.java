package Service.Impl;

import Models.Department;
import Models.Hospital;
import Service.DepartmentService;
import Service.GenericService;
import dao.Impl.DepartmentDaoImpl;
import dao.Impl.HospitalDaoImpl;

import java.util.List;

public class DepartmentServiceImpl  implements GenericService<Department>, DepartmentService {

    private final DepartmentDaoImpl departmentDao;
    private final HospitalDaoImpl hospitalDao;

    public DepartmentServiceImpl(DepartmentDaoImpl departmentDao, HospitalDaoImpl hospitalDao) {
        this.departmentDao = departmentDao;
        this.hospitalDao = hospitalDao;
    }

    @Override
    public List<Department> getAllDepartmentByHospital(Long id) {
       return hospitalDao.getAll().stream().filter(hospital -> hospital.getId() == id).
                findFirst().orElseThrow(()-> new RuntimeException(" Not fount hospital id !!")).getDepartments();
    }

    @Override
    public Department findDepartmentByName(String name) {
//        Department department1 = departmentDao.getAll().stream().filter(department -> department.equals(name)).
//                findFirst().orElseThrow(() -> new RuntimeException(" not fount Name !!"));
//          department1.getDepartmentName();
//          return department1;
//
        return hospitalDao.getAll().stream().flatMap(hospital -> hospital.getDepartments().stream().
                filter(department -> department.getDepartmentName().equals(name))).findFirst().orElseThrow(() -> new RuntimeException(" not fount Name !!"));


    }

    @Override
    public String add(Long hospitalId, List<Department> departmentList) {
        departmentDao.add(hospitalId,departmentList);
        return "asadd" ;
    }

    @Override
    public void removeById(Long id) {
        departmentDao.delete(id);
        throw new RuntimeException(" Incorrect id");
    }

    @Override
    public String updateById(Long id, Department department) {
        departmentDao.updateById(id,department);

        return " almashcan jok";
    }
}
