package com.conatuseus.communityservice.domain.crawler.domain;

import com.conatuseus.communityservice.domain.community.domain.Community;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

public class Crawler {

    private final Community community;
    private final Elements elements;

    public Crawler(final Community community) throws IOException {
        this.community = community;
        this.elements = initializeElements();
    }

    public Elements initializeElements() throws IOException {
        Document doc = Jsoup.connect(community.getSearchUrl()).get();
        return doc.select(community.getCssQuery());
    }

    public List<String> getTitles() {
        return elements.eachText();
    }

    public List<String> getLinks() {
        return elements.eachAttr(community.getAttributeKey());
    }
}
