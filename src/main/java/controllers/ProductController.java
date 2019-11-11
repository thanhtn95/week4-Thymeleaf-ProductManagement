package controllers;

import dao.productDao;
import model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
public class ProductController {
    private productDao productDao = new productDao();

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("productList", productDao.getProductList());
        return "index";
    }

    @GetMapping("/newProduct")
    public ModelAndView getAddForm() {
        ModelAndView modelAndView = new ModelAndView("addNewProduct", "product", new Product());
        return modelAndView;
    }

    @PostMapping("/doAdd")
    public String doAddProduct(Product product) {
        productDao.AddProduct(product);
        return "redirect:/";
    }

    @GetMapping("/{id}/delete")
    public String deleteProduct(@PathVariable int id) {
        productDao.deleteProduct(id);
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public ModelAndView getEditForm(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("editForm");
        modelAndView.addObject("product", productDao.getProductById(id));
        return modelAndView;
    }

    @PostMapping("/doEdit")
    public String doEdit(Product product) {
        productDao.updateProductInfo(product);
        return "redirect:/";
    }

    @GetMapping("/{id}/view")
    public ModelAndView getSelectedInfo(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("view");
        modelAndView.addObject("product", productDao.getProductById(id));
        return modelAndView;
    }
}
