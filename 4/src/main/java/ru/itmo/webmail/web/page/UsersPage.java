package ru.itmo.webmail.web.page;

import ru.itmo.webmail.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class UsersPage extends Page{
    private UserService userService = new UserService();

    @Override
    void before(HttpServletRequest request, Map<String, Object> view) {
        super.before(request, view);
    }

    private void action(Map<String, Object> view) {
        view.put("users", userService.findAll());
    }

    @Override
    void after(HttpServletRequest request, Map<String, Object> view) {
        super.after(request, view);
    }
}
