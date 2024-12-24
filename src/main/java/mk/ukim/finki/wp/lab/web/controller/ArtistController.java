package mk.ukim.finki.wp.lab.web.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.service.SongService;
import mk.ukim.finki.wp.lab.service.implementation.ArtistServiceImpl;
import mk.ukim.finki.wp.lab.service.implementation.SongServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@Controller
public class ArtistController {
    private String selectedId = "";
    private final ArtistServiceImpl artistService;
    private final SpringTemplateEngine springTemplateEngine;

    public ArtistController(ArtistServiceImpl artistService, SpringTemplateEngine springTemplateEngine) {
        this.artistService = artistService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @GetMapping("/artist")
    public String getArtist(Model model){
        model.addAttribute("artists", artistService.listArtists());
        model.addAttribute("trackId", selectedId);
        return "artistsList";//artistsList.html
    }

    @PostMapping("/artist")
    public String postArtist(@RequestParam String trackId){//na pesnata track id
        this.selectedId = trackId;
        return "redirect:/artist";
    }
}

