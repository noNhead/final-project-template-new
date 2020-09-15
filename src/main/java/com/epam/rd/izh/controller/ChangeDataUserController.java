package com.epam.rd.izh.controller;

import com.epam.rd.izh.entity.AuthorizedUser;
import com.epam.rd.izh.service.UserDetailsServiceMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class ChangeDataUserController {
    UserDetailsServiceMapper userDetailsServiceMapper = new UserDetailsServiceMapper();

    /**
     * Изменение данных пользователя
     */
    @GetMapping("/userdatachange")
    public String userDataChange(Model model, HttpServletRequest httpServletRequest){
        String referer = httpServletRequest.getHeader("Referer");
        if(referer != null){
            if ((referer.contains("checkuserpass") || referer.contains("userdatachange"))
                    && (!model.containsAttribute("userDataChange"))) {

                model.addAttribute("userDataChange", new AuthorizedUser());
            }
            return "userdatachange";
        }
        return "redirect:/checkuserpass";
    }

    /**
     * Изменение данных пользователя
     */
    @PostMapping("/userdatachange/proceed")
    public String processNewPasswordChange(@Valid @ModelAttribute("userDataChange") AuthorizedUser authorizedUser, BindingResult bindingResult, Authentication authentication){
        if(bindingResult.hasErrors()){
            return "userdatachange";
        }
        userDetailsServiceMapper.newChangePass(authentication.getName(), authorizedUser.getPassword());
        return "redirect:/";
    }

    /**
     * Удаление пользователя
     */
    @GetMapping("/userdatachange/delete/process")
    public String checkDeleteUser(){
        return "/userdatachange";
    }

    /**
     * Удаление пользователя
     */
    @GetMapping("/userdelete/process")
    public String deleteUser(Authentication authentication){
        userDetailsServiceMapper.DeleteUser(authentication.getName());
        return "redirect:/login";
    }
}
