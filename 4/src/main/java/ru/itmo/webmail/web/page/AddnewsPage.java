package ru.itmo.webmail.web.page;

import ru.itmo.webmail.model.domain.News;
import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.model.exception.ValidationException;
import ru.itmo.webmail.model.service.NewsService;
import ru.itmo.webmail.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class AddnewsPage extends Page {
    NewsService newsService = new NewsService();

    private void addnews(HttpServletRequest request, Map<String, Object> view) {
        String text = request.getParameter("newstext");
        User user = (User) request.getSession().getAttribute("user");
        System.out.println(user.getLogin());
        System.out.println(user.getId());
        try {
            newsService.validate(user, text);
        } catch (ValidationException e) {
            view.put("error", e.getMessage());
            return;
        }
        News news = new News(user.getId(), text);
        newsService.save(news);
        throw new RedirectException("/index", "newsAdded");
    }
    @Override
    void before(HttpServletRequest request, Map<String, Object> view) {
        super.before(request, view);
    }

    private void action() {
        //No operations
    }

    @Override
    void after(HttpServletRequest request, Map<String, Object> view) {
        super.before(request, view);
    }
}