package com.epam.rd.izh.controller;

import com.epam.rd.izh.entity.AuthorizedUser;
import com.epam.rd.izh.repository.UserRepository;
import com.epam.rd.izh.service.UserDetailsServiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.sql.SQLException;

/**
 * В аргументы контроллеров, которые обрабатывают запросы, можно указать дополнительные входные параметры: Например:
 * HttpServletRequest и HttpServletResponse. Данные объекты автоматически заполняться данными о реквесте и респонсе. Эти
 * данные можно использовать, например достать куки, сессию, хедеры итд.
 */

@Controller
public class AuthenticationController {

  @Autowired
  UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  UserDetailsServiceMapper userDetailsServiceMapper = new UserDetailsServiceMapper();

  /**
   * Метод, отвечающий за логику авторизации пользователя.
   * /login - определяет URL, по которому пользователь должен перейти, чтобы запустить данный метод-обработчик.
   */
  @GetMapping("/login")
  public String login(Model model, @RequestParam(required = false) String error) {
    if (error != null) {
      /**
       * Model представляет из себя Map коллекцию ключ-значения, распознаваемую View элементами MVC.
       * Добавляется String "invalid login or password!", с ключем "error_login_placeholder".
       * При создании View шаблона плейсхолдер ${error_login_placeholder} будет заменен на переданное значение.
       *
       * В класс Model можно передавать любые объекты, необходимые для генерации View.
       */
      model.addAttribute("error_login_placeholder", "invalid login or password!");
    }
    /**
     * Контроллер возвращает String название JSP страницы.
     * В application.properties есть следующие строки:
     * spring.mvc.view.prefix=/WEB-INF/pages/
     * spring.mvc.view.suffix=.jsp
     * Spring MVC, используя суффикс и префикс, создаст итоговый путь к JSP: /WEB-INF/pages/login.jsp
     */
    if (!model.containsAttribute("loginForm")) {
      model.addAttribute("loginForm", new AuthorizedUser(null,null,null,null));
    }
    return "login";
  }
  @PostMapping("/login/proceed")
  public String processLogin(@Valid @ModelAttribute("loginForm") AuthorizedUser loginUser, BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      return "redirect:/login";
    }
    AuthorizedUser authorizedUser;
    try {
      authorizedUser = userRepository.getAuthorizedUserByLogin(loginUser.getLogin());
    } catch (SQLException throwables) {
      throwables.printStackTrace();
      return "redirect:/login";
    }
    if (authorizedUser != null) {
      if (!passwordEncoder.matches(loginUser.getPassword(), authorizedUser.getPassword())){
        return "redirect:/login";
      }
      else {
        return "redirect:/index";
      }
    }
    return "redirect:/login";
  }
  /**
   * Метод, отвечающий за логику регистрации пользователя.
   */
  @GetMapping("/registration")
  public String viewRegistration(Model model) {
    if(!model.containsAttribute("registrationForm")){
      model.addAttribute("registrationForm", new AuthorizedUser(null, null,null, null));
    }
    return "registration";
  }

  /**
   * Метод, отвечающий за подтверждение регистрации пользователя и сохранение данных в репозиторий или DAO.
   */
  @PostMapping("/registration/proceed")
  public String processRegistration(@Valid @ModelAttribute("registrationForm") AuthorizedUser registeredUser,
      BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      //логика отображения ошибки, не является обязательной
      //...
      //...
      return "redirect:/registration";
    }

    if(registeredUser != null){
      registeredUser.setPassword(passwordEncoder.encode(registeredUser.getPassword()));
      if(userDetailsServiceMapper.userRegistration(registeredUser)){
        return "redirect:/login";
      }
    }
    return "redirect:/registration";
  }

  /**
   * Метод отвечающий за повторную проверку пароля перед изменением данных
   */
  @GetMapping("/checkuserpass")
  public String checkUserPass(ModelMap model){
    if (!model.containsAttribute("checkUserPass")){
      model.addAttribute("checkUserPass", new AuthorizedUser());
    }
    return "checkuserpass";
  }

  /**
   * Метод отвечающий за повторную проверку пароля перед изменением данных
   */
  @PostMapping("/checkuserpass/proceed")
  public String processCheckUserPass(@Valid @ModelAttribute("checkUserPass") AuthorizedUser authorizedUser, BindingResult bindingResult, Authentication authentication){
    if(bindingResult.hasErrors()){
      return "redirect:/checkuserpass";
    }
    if (authorizedUser.getPassword() == null) {
      return "redirect:/userdatachange";
    }
    System.out.println(authentication.getName() + authorizedUser.getPassword());
    if(!passwordEncoder.matches(authorizedUser.getPassword(), userDetailsServiceMapper.loadUserByUsername(authentication.getName()).getPassword())){
      return "redirect:/checkuserpass";
    }
    return "redirect:/userdatachange";
  }
}