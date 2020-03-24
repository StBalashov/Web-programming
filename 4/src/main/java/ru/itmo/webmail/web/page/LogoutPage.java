package ru.itmo.webmail.web.page;

import com.google.common.hash.Hashing;
import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.model.exception.*;
import ru.itmo.webmail.model.repository.UserRepository;
import ru.itmo.webmail.model.repository.impl.UserRepositoryImpl;
import ru.itmo.webmail.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class LogoutPage extends Page {
    @Override
    void before(HttpServletRequest request, Map<String, Object> view) {
        super.after(request, view);
    }

    public void action(HttpServletRequest request, Map<String, Object> view) {
        request.getSession().removeAttribute("user");
        throw new RedirectException("/index", "loggedOut");
    }
    @Override
    void after(HttpServletRequest request, Map<String, Object> view) {
        super.after(request, view);
    }

}
