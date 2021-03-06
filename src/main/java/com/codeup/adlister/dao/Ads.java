package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;

import java.util.List;

public interface Ads {
    // get a list of all the ads
    List<Ad> all();

    List<Ad> userAds(long id);

    Ad adById(long id);

    void delete(long id);

    void update(long id, String title, String image_url, int start_year, int end_year, String description);
    // insert a new ad and return the new ad's id
    Long insert(Ad ad);
}
