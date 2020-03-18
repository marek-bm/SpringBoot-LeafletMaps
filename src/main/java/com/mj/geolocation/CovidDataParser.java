package com.mj.geolocation;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.http.client.HttpClient;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


@Service
public class CovidDataParser {
    public static final String COVID_CONFIRMED_URL=
            "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_19-covid-Confirmed.csv";



    @EventListener(ApplicationReadyEvent.class)
    public List<Point> getCovidData() throws IOException {
        List<Point> points=new ArrayList<>();

        RestTemplate restTemplate=new RestTemplate();
        String responseString=restTemplate.getForObject(COVID_CONFIRMED_URL, String.class);
//        System.out.println(responseString);

        StringReader stringReader=new StringReader(responseString);
        CSVParser csvParser=CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(stringReader);

        for(CSVRecord record:csvParser){
            double lat=Double.parseDouble(record.get("Lat"));
            double lon=Double.parseDouble(record.get("Long"));
            String infected=record.get("3/17/20");
            points.add(new Point(lat,lon, infected));
        }
        return points;
    }
}

