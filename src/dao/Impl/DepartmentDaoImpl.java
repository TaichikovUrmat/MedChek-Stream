package dao.Impl;

import Models.Department;
import Models.Hospital;
import dao.DaoGroup;
import db.DataBace;

import java.util.List;

public class DepartmentDaoImpl implements DaoGroup<Department,Long> {
    private final DataBace dataBace;

    public DepartmentDaoImpl(DataBace dataBace) {
        this.dataBace = dataBace;
    }

    @Override
    public boolean add(Long hospitalId, List<Department> departments) {
        dataBace.getHospitalList().stream().filter(hospital -> hospital.getId() == hospitalId).
                findFirst().orElseThrow(()->new RuntimeException("Not fount hospital id !!")).setDepartments(departments);
        return false;
    }

    @Override
    public boolean delete(Long id) {
        dataBace.getHospitalList().removeIf(hospital -> hospital.getDepartments().removeIf(department -> department.equals(id)));
        return true;
    }

    @Override
    public String updateById(Long id, Department department) {
        dataBace.getHospitalList().stream().flatMap(hospital -> hospital.getDepartments().stream().
                filter(department1 -> department1.equals(id))).findFirst().orElseThrow(() -> new RuntimeException(" Not fount id!")).setDepartmentName(department.getDepartmentName());
        return "Almashty";
    }
    @Override
    public List<Department> getAll() {
        for (Hospital hospital : dataBace.getHospitalList()) {
            return hospital.getDepartments();
        }
        throw new RuntimeException(" Incorrect type");
    }

}
