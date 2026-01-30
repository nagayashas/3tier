package com.project.products.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.products.entity.Products;
import com.project.products.repository.ProductRepository;

@Controller
public class ProductsController {
	
	@Autowired
	ProductRepository productRepository;
	
	@GetMapping("/")
	public String loadHome() {
		return "home.html";
	}

	@GetMapping("/add")
	public String loadAdd() {
		return "add.html";
	}
	
	@PostMapping("/add")
	public String addData(Products product , RedirectAttributes attr) {
		productRepository.save(product);
		attr.addFlashAttribute("message","data added successfully!!!!");
		return "redirect:/view";
	}
	
	@GetMapping("/view")
	public String loadView(ModelMap map) {
			List<Products> list = productRepository.findAll();
			map.put("list",list );
		return "view.html";
	}
	
	@GetMapping("/edit")
	public String loadEdit(@RequestParam Long id,ModelMap map) {
		Products product = productRepository.findById(id).orElseThrow();
		map.put("values", product);
		return "edit";
	}
	@GetMapping("/delete")
	public String delete(@RequestParam Long id , RedirectAttributes attributes) {
		productRepository.deleteById(id);
		return "redirect:/view";
	}
	
	
	
}
