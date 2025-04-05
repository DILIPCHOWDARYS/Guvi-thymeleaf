package com.ProductCatalog.controller;

import com.ProductCatalog.model.Product;
import com.ProductCatalog.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/addProduct")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "addProduct";
    }

    @PostMapping("/saveProduct")
    public String saveProduct(@ModelAttribute Product product, Model model) {
        productService.saveProduct(product);
        model.addAttribute("product", new Product()); // reset form
        model.addAttribute("successMessage", "Product added successfully!");

        return "addProduct";
    }

    @GetMapping("/displayProducts")
    public String displayProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "displayProducts";
    }
}
