package Allie.Model;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping(path = "/models")
public class ModelController {

    @Autowired
    private ModelService service;

    /*  public ModelController(ModelDAO dao) {
          this.service = new ModelService(dao);
      }

    public ModelController(IRepository<Model> repo) {
        service = new ModelService(repo);
    }
*/
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    Model GetById(@PathVariable int id) throws SQLException {
        return service.GetById(id);
    }

    @GetMapping("/")
    public Iterable<Model> getAll() throws SQLException {
        return service.GetAll();
    }

    @PostMapping("/")
    Model Add(@RequestBody Model model) throws SQLException {
        return service.Add(model);
    }
}
