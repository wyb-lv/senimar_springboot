package com.wyb.web.senimar_demo.controller;

import com.wyb.web.senimar_demo.entity.Account;
import com.wyb.web.senimar_demo.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AccountController {

    @Autowired
    private AccountService accountService;

    /**
     * Display all customers
     */
    @GetMapping("/customers")
    public String showCustomers(Model model) {
        List<Account> accounts = accountService.getAllAccounts();
        model.addAttribute("accounts", accounts);
        return "displayCustomers";
    }

    /**
     * Delete customer
     */
    @GetMapping("/customers/delete")
    public String deleteCustomer(@RequestParam("id") Long id,
                                 RedirectAttributes redirectAttributes) {
        try {
            accountService.deleteAccount(id);
            redirectAttributes.addFlashAttribute("success", "Customer deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to delete customer: " + e.getMessage());
        }
        return "redirect:/admin/customers";
    }

    /**
     * Find user by name
     */
    @PostMapping("/customers/search")
    public String searchCustomers(@RequestParam(value = "name",required = false, defaultValue = "") String name,
                                 Model model) {
        if (!name.equals("")){
            List<Account> accounts = accountService.searchCustomersbyUsername(name);
            model.addAttribute("accounts", accounts);
            model.addAttribute("name", name);
        } else {
            List<Account> accounts = accountService.getAllAccounts();
            model.addAttribute("accounts", accounts);
        }
        return "displayCustomers";
    }
}
