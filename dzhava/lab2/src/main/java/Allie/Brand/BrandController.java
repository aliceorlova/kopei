package Allie.Brand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping(path = "/brands")
public class BrandController {

    @Autowired
    private BrandService service;
    /*
     BrandController() {
         service = new BrandService();
     }


         public BrandController(BrandDAO dao) {
             this.service = new BrandService(dao);
         }

     @Autowired
     public BrandController(IRepository<Brand> repo) {
         this.service = new BrandService(repo);
     }
 */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    Brand GetById(@PathVariable int id) throws SQLException {
        return service.GetById(id);
    }

    @GetMapping("/")
    public Iterable<Brand> getAll() throws SQLException {
        return service.GetAll();
    }

    @PostMapping("/")
    Brand Add(@RequestBody Brand brand) throws SQLException {
        return service.Add(brand);
    }

}
