package dao.Impl;

import Models.Hospital;
import dao.DaoGroup;
import db.DataBace;

import java.util.List;

public class HospitalDaoImpl implements DaoGroup<Hospital,Long> {
    private  final DataBace dataBace;


    public HospitalDaoImpl(DataBace dataBace) {
        this.dataBace = dataBace;
    }
    @Override
    public List<Hospital> getAll(){
        return dataBace.getHospitalList();
    }

    @Override
    public boolean add(Long hospitalId, List<Hospital> hospitals) {
        return dataBace.add(hospitals);

    }

    @Override
    public boolean delete(Long id) {
      return dataBace.getHospitalList().removeIf(hospital -> hospital.getId() == id);
    }

    @Override
    public String updateById(Long id, Hospital hospital) {
        return null;
    }
}
