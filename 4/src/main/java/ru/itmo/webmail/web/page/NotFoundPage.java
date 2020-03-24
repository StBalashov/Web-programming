package ru.itmo.webmail.web.page;

import ru.itmo.webmail.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class NotFoundPage extends Page {
    private UserService userService = new UserService();

    @Override
    void before(HttpServletRequest request, Map<String, Object> view) {
        super.before(request, view);
    }


    public void action() {
        // No operations.
    }

    @Override
    void after(HttpServletRequest request, Map<String, Object> view) {
        super.after(request, view);
    }
}
