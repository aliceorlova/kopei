package app.Model;

import app.Default.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service("modelService")
public class ModelService implements IService<Model> {

    @Autowired
    private ModelRepository modelRepo;

    @Override
    public Model Add(Model model) throws SQLException {
        model.setId(GetAll().size());
        return modelRepo.save(model);
    }

    @Override
    public void Delete(Model model) throws SQLException {
        modelRepo.delete(model);
    }

    @Override
    public void DeleteById(int id) throws SQLException {
        modelRepo.delete((long) id);
    }

    @Override
    public Model Update(Model model) throws SQLException {
        Model m = modelRepo.findOne(model.getId());
        m.setModelName(model.getModelName());
        modelRepo.save(m);
        return m;
    }

    @Override
    public List<Model> GetAll() throws SQLException {
        List<Model> list = new ArrayList<>();
        modelRepo.findAll().forEach(list::add);
        return list;
    }

    @Override
    public Model GetById(int id) throws SQLException {
        return modelRepo.findOne((long) id);
    }
}
