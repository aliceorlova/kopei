package app.Brand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "/brands")
public class BrandController {

    @Autowired
    private BrandService service;

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    Brand GetById(@PathVariable int id) throws SQLException {
        return service.GetById(id);
    }

    @GetMapping("/")
    public List<Brand> getAll() throws SQLException {
        return service.GetAll();
    }

    @PostMapping("/")
    Brand Add(@RequestBody Brand brand) throws SQLException {
        return service.Add(brand);
    }
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    void Delete(@PathVariable int id) throws SQLException {
         service.DeleteById(id);
    }
    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    Brand Update(@RequestBody Brand brand) throws SQLException{
        return service.Update(brand);
    }
}
