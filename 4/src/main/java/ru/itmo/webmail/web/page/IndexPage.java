package ru.itmo.webmail.web.page;

import ru.itmo.webmail.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class IndexPage extends Page {
    private UserService userService = new UserService();
    private void action() {
        // No operations.
    }
    @Override
    void before(HttpServletRequest request, Map<String, Object> view) {
        super.before(request, view);
    }



    private void registrationDone(HttpServletRequest request, Map<String, Object> view) {
        view.put("message", "You have been registered");
    }

    private void loggingDone(HttpServletRequest request, Map<String, Object> view) {
        view.put("message", "You have logged in");
    }

    private void loggedOut(HttpServletRequest request, Map<String, Object> view) {
        view.put("message", "You have logged out");
    }

    private void newsAdded(Map<String, Object> view) {
        view.put("message", "Your news have been added");
    }

    @Override
    void after(HttpServletRequest request, Map<String, Object> view) {
        super.after(request, view);
    }

}
