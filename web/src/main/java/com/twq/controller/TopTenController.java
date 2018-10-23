package com.twq.controller;

import com.twq.model.TopTenMovie;
import com.twq.service.TopTenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class TopTenController {

    @Autowired
    private TopTenService topTenService;

    @RequestMapping("/douban/topten/{year}")
    public String getTopTenMovies(Model model, @PathVariable int year) {

        System.out.println("year = " + year);

        List<TopTenMovie> topTenMovies = topTenService.getTopTenMovie(year);

        model.addAttribute("topTenMovies", topTenMovies);

        return "topTenMovies4Year";
    }
}
