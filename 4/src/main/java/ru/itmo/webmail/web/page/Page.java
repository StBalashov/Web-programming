package ru.itmo.webmail.web.page;

import java.util.ArrayList;
import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.model.domain.News;
import ru.itmo.webmail.model.service.UserService;
import ru.itmo.webmail.model.service.NewsService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Map;

public class Page {
    private UserService userService = new UserService();
    private NewsService newsService = new NewsService();
    void before(HttpServletRequest request, Map<String, Object> view){
        User user = (User)request.getSession().getAttribute("user");
        if (user != null) {
            view.put("userName", user.getLogin());
        }
        view.put("userCount",  userService.findCount());
    }

    void after(HttpServletRequest request, Map<String, Object> view){
        view.put("userCount", userService.findCount());
        ArrayList<DefiniteNews> allNews = new ArrayList<DefiniteNews>();
        for (News n : newsService.findAll()) {
            System.out.println(n.getId());
            allNews.add(new DefiniteNews(userService.find(n.getId()), n.getText()));
        }
        if (!allNews.isEmpty()) {
            view.put("allNews", allNews);
        }
    }

    public class DefiniteNews extends News {
        private String user;
        public DefiniteNews(String username, String text) {
            super(text);
            user = username;
        }
        public String getUser() {
            return user;
        }
    }
}
