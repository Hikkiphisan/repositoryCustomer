package org.example.customerrepositorymanagement.controller;

import org.example.customerrepositorymanagement.model.Customer;
import org.example.customerrepositorymanagement.model.Province;
import org.example.customerrepositorymanagement.service.ICustomerService;
import org.example.customerrepositorymanagement.service.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.imageio.ImageTranscoder;
import java.util.Optional;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IProvinceService provinceService;

    @ModelAttribute("provinces")  //vì phương thức này được đánh dấu bằng @ModelAttribute. Dữ liệu trả về từ listProvinces() sẽ được tự động đưa vào model với tên "provinces".
    public Iterable<Province> listProvinces() {
        Iterable<Province> findall =  provinceService.findAll();
        return findall;
    }

    @GetMapping
    public ModelAndView listCustomer(@RequestParam("search") Optional<String> search,Pageable pageable) {

        Page<Customer> customers;
        if(search.isPresent()){
            customers = customerService.findAllByFirstNameContaining(pageable, search.get());;
        } else {
            customers = customerService.findAll(pageable);
        }








        ModelAndView modelAndView = new ModelAndView("/customer/list"); //modelandviews đã chứa cả model provinces

//        Iterable<Customer> customers = customerService.findAll();
        modelAndView.addObject("customers", customers);
        modelAndView.addObject("search", search.orElse("")); // Giữ giá trị tìm kiếm nếu có
//        auto thêm model "provinces" vào view mà không cần gọi trực tiếp từ phương thức
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView createForm() {
        ModelAndView modelAndView = new ModelAndView("/customer/create");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("customer") Customer customer,
                         RedirectAttributes redirectAttributes) {
        customerService.save(customer);
        redirectAttributes.addFlashAttribute("message", "Create new customer successfully");
        return "redirect:/customers";
    }

    @GetMapping("/update/{id}")
    public ModelAndView updateForm(@PathVariable Long id) {
        Optional<Customer> customer = customerService.findById(id);
        if (customer.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/customer/update");
            modelAndView.addObject("customer", customer.get());
            return modelAndView;
        } else {
            return new ModelAndView("/error_404");
        }
    }

    @PostMapping("/update/{id}")
    public String update(@ModelAttribute("customer") Customer customer,
                         RedirectAttributes redirect) {
        customerService.save(customer);
        redirect.addFlashAttribute("message", "Update customer successfully");
        return "redirect:/customers";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id,
                         RedirectAttributes redirect) {
        customerService.remove(id);
        redirect.addFlashAttribute("message", "Delete customer successfully");
        return "redirect:/customers";
    }


}