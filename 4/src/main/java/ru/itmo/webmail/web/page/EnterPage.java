package ru.itmo.webmail.web.page;

import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.model.exception.*;
import ru.itmo.webmail.model.repository.UserRepository;
import ru.itmo.webmail.model.service.UserService;
import ru.itmo.webmail.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class EnterPage extends Page {
    private UserService userService = new UserService();
//    private UserRepository userRepository = new UserRepository() {
//    }

    private void enter(HttpServletRequest request, Map<String, Object> view){
        User user = new User();
        user.setLogin(request.getParameter("login"));
        String password = request.getParameter("password");

        try{
            userService.validateEnter(user, password);
        } catch(EnterException e){
            view.put("login", user.getLogin());
            view.put("password", password);
//            view.put("passwordConf", passwordConfirmation);
//            view.put("E-mail", eMail);
            view.put("error", e.getMessage());
            return;
        }
        user = userService.findByLogin(request.getParameter("login"));
        request.getSession().setAttribute("user", user);
        throw new RedirectException("/index", "loggingDone");
    }

    @Override
    void before(HttpServletRequest request, Map<String, Object> view) {
        super.before(request, view);
    }

    private void action() {
        // No operations.
    }

    @Override
    void after(HttpServletRequest request, Map<String, Object> view) {
        super.after(request, view);
    }
}
