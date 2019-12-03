package Allie.Brand;

import org.springframework.beans.factory.annotation.Autowired;
import Allie.Default.Service;

import java.sql.SQLException;

@org.springframework.stereotype.Service("BrandService")
public class BrandService implements Service<Brand> {

    /*  @Autowired
      private BrandDAO dao;*/
   // private final IRepository<Brand> brandRepo;
    @Autowired
    private BrandRepository brandRepo;
    /*
        public BrandService(BrandDAO dao) {
            this.dao = dao;
        }

    @Autowired
    BrandService(IRepository<Brand> brandRepo) {
        this.brandRepo = brandRepo;
    }
 */
    @Override
    public Brand Add(Brand item) throws SQLException {
        //  return dao.Add(item);
        return brandRepo.save(item);
    }

    @Override
    public void Delete(Brand item) throws SQLException {
        //  return dao.Delete(item);
        brandRepo.delete(item);
    }

    @Override
    public void DeleteById(int id) throws SQLException {
        //  return dao.DeleteById(id);
        brandRepo.delete((long) id);
    }

    @Override
    public void Update(Brand item) throws SQLException {
        // return dao.Update(item);
        Brand b = brandRepo.findOne(item.getId());
        b.setName(item.getName());
        brandRepo.save(b);
    }

    @Override
    public Iterable<Brand> GetAll() throws SQLException {
        //  return dao.GetAll();
        return brandRepo.findAll();
    }

    @Override
    public Brand GetById(int id) throws SQLException {
        // return dao.GetById(id);
        return brandRepo.findOne((long) id);
    }
}
