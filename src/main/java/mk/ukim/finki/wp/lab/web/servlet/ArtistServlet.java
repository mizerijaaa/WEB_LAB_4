package mk.ukim.finki.wp.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.service.SongService;
import mk.ukim.finki.wp.lab.service.implementation.ArtistServiceImpl;
import mk.ukim.finki.wp.lab.service.implementation.SongServiceImpl;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "artistServlet",urlPatterns = "/servlet/artist")
public class ArtistServlet extends HttpServlet {
    private String selectedId = "";
    private final ArtistServiceImpl artistService;
    private final SpringTemplateEngine springTemplateEngine;

    public ArtistServlet(ArtistServiceImpl artistService, SpringTemplateEngine springTemplateEngine) {
        this.artistService = artistService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange exchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);
        WebContext context = new WebContext(exchange);
        context.setVariable("artists",artistService.listArtists());
        context.setVariable("trackId",selectedId);
        springTemplateEngine.process("artistsList.html",context,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        selectedId = req.getParameter("trackId");
        resp.sendRedirect("/artist");
    }
}
