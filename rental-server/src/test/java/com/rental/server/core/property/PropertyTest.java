package com.rental.server.core.property;

import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;

class PropertyTest {

    @Test
    void generateProperties() throws IOException {

        double latStart = 49.361083;
        double lngStart = -123.141661;

        double latEnd = 49.095624;
        double lngEnd = -122.721434;

        int rows = 50;
        int columns = 50;
        ArrayList<String> url = new ArrayList<>();
        url.add("https://images.dailyhive.com/20170316133620/4670-Connaught-Drive-Vancouver-feature-iconco..jpg");
        url.add("https://www.rcinet.ca/en/wp-content/uploads/sites/3/2018/07/lemieux-home-635x357.jpg");
        url.add("https://luxport.s3.amazonaws.com/53174/629+Senanus+Drive+Victoria+BC+Canada+488289_1-EXT.jpg");
        url.add("https://www.mtlblog.com/u/2018/09/20/93769409fdd48310a4d7d7baa804bc6c7b8e392a.png_1200x630.png");
        url.add("https://i.cbc.ca/1.4789930.1534538769!/fileImage/httpImage/image.jpg_gen/derivatives/16x9_780/68-the-bridle-path.jpg");
        url.add("https://myfancyhouse.com/wp-content/uploads/2014/05/Fabulous-Caulfeild-Mansion-in-Canada-40.jpg");
        url.add("https://img.huffingtonpost.com/asset/5cd4fb9d2500005600a0ea7b.jpeg?ops=scalefit_630_noupscale");
        url.add("https://o.aolcdn.com/images/dims?crop=1024%2C682%2C0%2C0&quality=85&format=jpg&resize=630%2C420&image_uri=http%3A%2F%2Fo.aolcdn.com%2Fhss%2Fstorage%2Fmidas%2F9d6826418784ebb5825c722db59e0eca%2F205611997%2F225%2Bsaskatchewan%2B01.jpeg&client=a1acac3e1b3290917d92&signature=4eb982ea40dc9ed358ad8bc73bce34b52706f908");
        url.add("https://o.aolcdn.com/images/dims?crop=1024%2C678%2C0%2C0&quality=85&format=jpg&resize=630%2C417&image_uri=http%3A%2F%2Fo.aolcdn.com%2Fhss%2Fstorage%2Fmidas%2Fb2463e1e2965047058b45ac03535c8ee%2F205612003%2F225%2Bsaskatchewan%2B02.jpeg&client=a1acac3e1b3290917d92&signature=c2d50e605245f76e0dc0f8f966b82e080330a67a");

        PrintWriter pw = new PrintWriter(new FileWriter("out.txt"));
        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < columns; j++) {

                double random = Math.random() * (2 - 0.1);
                double paceLong = (lngEnd - lngStart) / columns * j * random;
                double paceLat = (latEnd - latStart) / rows * i * random;

                Property property = Property.builder()
                        .urls(new PropertyUrls(url.get((int) (Math.random() * (url.size())))))
                        .latitude(latStart + paceLat)
                        .longitude(lngStart + paceLong)
                        .price(BigDecimal.valueOf(10000 + (Math.random() * (25000 - -7000))))
                        .bedrooms(2 + ((int) (Math.random() * (5 - -1))))
                        .size(600 + ((int) (Math.random() * (300 - -300))))
                        .neighbourhood("North Vancouver")
                        .type("Apartment")
                        .address((4912 + ((int) (Math.random() * (2000 - -4000)))) + " Tatooine Street")
                        .build();

                String propertyInsert = "INSERT INTO property (url_thumb, latitude, longitude, type, address, neighbourhood, size, bedrooms, price) VALUES (" +
                        "'" + property.getUrls().getThumb() + "', " +
                        property.getLatitude() + ", " +
                        property.getLongitude() + ", " +
                        "'" + property.getType() + "', " +
                        "'" + property.getAddress() + "', " +
                        "'" + property.getNeighbourhood() + "', " +
                        property.getSize() + ", " +
                        property.getBedrooms() + ", " +
                        property.getPrice() +
                        ");\n";
                pw.write(propertyInsert);
            }
        }

        pw.close();
    }

}