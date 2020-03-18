package com.mj.geolocation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MapController {
    private CovidDataParser covidDataParser;

    public MapController(CovidDataParser covidDataParser) {
        this.covidDataParser = covidDataParser;
    }

    @GetMapping("/1")
    public String getSingleMarker(Model model, @RequestParam  String x, @RequestParam String y){
        model.addAttribute("x", x);
        model.addAttribute("y", y);
        return "map";
    }

    @GetMapping("/2")
    public String getTwoMarkers(Model model){
        List<Point> pointList= new ArrayList<>();
        pointList.add(new Point(51.76, 19.48, "Wykryte przypadki: 1"));
        pointList.add(new Point(50.82, 17.14, "Wykryte przypadki: 2"));
        model.addAttribute("pointList", pointList);
        return "map2";
    }

    @GetMapping("/all")
    public String getAllMakers(Model model) throws IOException {

        List<Point> points=covidDataParser.getCovidData();
        model.addAttribute("pointList", points);
        return "mapAll";
    }

}
