package com.epam.rd.izh.controller;

import com.epam.rd.izh.entity.AuthorizedUser;
import com.epam.rd.izh.service.UserDetailsServiceMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;

@Controller
public class ChangeDataUserController {
    UserDetailsServiceMapper userDetailsServiceMapper = new UserDetailsServiceMapper();

    @GetMapping("/checkuserpass")
    public String checkUserPass(ModelMap model){
        if (!model.containsAttribute("checkUserPass")){
            model.addAttribute("checkUserPass", new AuthorizedUser());
        }
        return "checkuserpass";
    }

    @PostMapping("/checkuserpass/proceed")
    public String processCheckUserPass(@Valid @ModelAttribute("checkUserPass") AuthorizedUser authorizedUser, BindingResult bindingResult, Authentication authentication){
        if(bindingResult.hasErrors()){
            return "redirect:/checkuserpass";
        }
        if(!userDetailsServiceMapper.checkPass(authentication.getName(), authorizedUser.getPassword())){
            return "redirect:/checkuserpass";
        }
        return "redirect:/userdatachange";
    }

    @GetMapping("/userdatachange")
    public String userDataChange(Model model){
        if(!model.containsAttribute("userDataChange")){
            model.addAttribute("userDataChange", new AuthorizedUser());
        }
        return "userdatachange";
    }

    @PostMapping("/userdatachange/proceed")
    public String processNewPasswordChange(@Valid @ModelAttribute("userDataChange") AuthorizedUser authorizedUser, BindingResult bindingResult, Authentication authentication){
        if(bindingResult.hasErrors()){
            return "userdatachange";
        }
        userDetailsServiceMapper.newChangePass(authentication.getName(), authorizedUser.getPassword());
        return "redirect:/";
    }

    @GetMapping("/userdatachange/delete/process")
    public String checkDeleteUser(){
        return "/userdatachange";
    }

    @GetMapping("/userdelete/process")
    public String deleteUser(Authentication authentication){
        userDetailsServiceMapper.DeleteUser(authentication.getName());
        return "redirect:/login";
    }
}
