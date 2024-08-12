package com.javatechie.controller;

import com.javatechie.constants.RoleConstants;
import com.javatechie.dto.Product;
import com.javatechie.entity.UserInfo;
import com.javatechie.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @PostMapping("/new")
    public String addNewUser(@RequestBody UserInfo userInfo){
        return service.addUser(userInfo);
    }

    @GetMapping("/all")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
   // @PreAuthorize("hasRole('"+ RoleConstants.ADMIN+ "')")
    @PreAuthorize("hasAuthority('"+ RoleConstants.ADMIN+ "')")
    public List<Product> getAllTheProducts() {
        return service.getProducts();
    }

    @GetMapping("/{id}")
    //@PreAuthorize("hasAuthority('ROLE_USER')")
   // @PreAuthorize("hasRole('"+ RoleConstants.ADMIN+ "')")
    @PreAuthorize("hasAnyRole('" + RoleConstants.USER + "','" + RoleConstants.ADMIN + "')")
   // @PreAuthorize("hasRole('" + RoleConstants.USER + "')" +
    //        " || hasRole('" + RoleConstants.ADMIN + "')" )
    public Product getProductById(@PathVariable int id) {
        return service.getProduct(id);
    }
}
