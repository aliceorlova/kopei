package Allie.Model;

import Allie.Default.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;

@org.springframework.stereotype.Service("ModelService")
public class ModelService implements Service<Model> {

    //  private ModelDAO dao;
   // private IRepository<Model> modelRepo;
    /*
    public ModelService(ModelDAO dao) {
        this.dao = dao;
    }*/

    @Autowired
    private ModelRepository modelRepo;
   /* public ModelService(IRepository<Model> repo) {
        modelRepo = repo;
    }
*/
    @Override
    public Model Add(Model model) throws SQLException {
        //  return dao.Add(model);
        return modelRepo.save(model);
    }

    @Override
    public void Delete(Model model) throws SQLException {
        //  return dao.Delete(model);
        modelRepo.delete(model);
    }

    @Override
    public void DeleteById(int id) throws SQLException {
        //   return dao.DeleteById(id);
        modelRepo.delete((long) id);
    }

    @Override
    public void Update(Model model) throws SQLException {
        //   return dao.Update(model);
        Model m = modelRepo.findOne(model.getId());
        m.setModelName(model.getModelName());
        modelRepo.save(m);
    }

    @Override
    public Iterable<Model> GetAll() throws SQLException {
        // return dao.GetAll();
        return modelRepo.findAll();
    }

    @Override
    public Model GetById(int id) throws SQLException {
        //   return dao.GetById(id);
        return modelRepo.findOne((long) id);
    }
}
