package app.Brand;

import app.Default.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service("brandService")
public class BrandService implements IService<Brand> {
    @Autowired
    private BrandRepository brandRepo;

    @Override
    public Brand Add(Brand item) throws SQLException {
        item.setId(GetAll().size());
        return brandRepo.save(item);
    }

    @Override
    public void Delete(Brand item) throws SQLException {
        brandRepo.delete(item);
    }

    @Override
    public void DeleteById(int id) throws SQLException {
        brandRepo.delete((long) id);
    }

    @Override
    public void Update(Brand item) throws SQLException {
        Brand b = brandRepo.findOne(item.getId());
        b.setName(item.getName());
        brandRepo.save(b);
    }

    @Override
    public List<Brand> GetAll() throws SQLException {
        List<Brand> list = new ArrayList<>();
        brandRepo.findAll().forEach(list::add);
        return list;
    }

    @Override
    public Brand GetById(int id) throws SQLException {
        return brandRepo.findOne((long) id);
    }
}
