package com.Ecommer.controller;

import com.Ecommer.dto.ProductDto;
import com.Ecommer.dto.user.SignInDto;
import com.Ecommer.dto.user.signupdto;
import com.Ecommer.model.Category;
import com.Ecommer.service.Categorieservice;
import com.Ecommer.service.productservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private Categorieservice categoryService;

    @Autowired
    private productservice productService;

    @GetMapping("/categories")
    public String categories(Model model) {
        List<Category> categories = categoryService.listCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("category", new Category()); // For creating a new category
        return "categories"; // Name of the Thymeleaf template (categories.html)
    }

    @GetMapping("/products")
    public String products(Model model) {
        List<ProductDto> products = productService.getAllProducts().getBody();
        model.addAttribute("products", products);
        model.addAttribute("product", new ProductDto()); // For adding a new product
        return "products"; // Name of the Thymeleaf template (products.html)
    }

    @GetMapping("/user/signup")
    public String signup(Model model) {
        model.addAttribute("signup", new signupdto());
        return "signup"; // Name of the Thymeleaf template (signup.html)
    }

    @GetMapping("/user/signin")
    public String signin(Model model) {
        model.addAttribute("signin", new SignInDto());
        return "signin"; // Name of the Thymeleaf template (signin.html)
    }
}
