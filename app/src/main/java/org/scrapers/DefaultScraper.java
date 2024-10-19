package org.scrapers;

import org.scrapers.IScraper;
import org.scrapers.Currency;

import org.jsoup.Jsoup;
import org.jsoup.HttpStatusException;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.io.IOException;

public final class DefaultScraper implements IScraper {
    private final String site_link;        
    private final Document site_doc;
    private Elements list_of_currs;
    private HashMap<String, Currency> curr_data;

    public DefaultScraper() throws
        MalformedURLException,
        HttpStatusException,
        UnsupportedMimeTypeException,
        SocketTimeoutException,
        IOException
    {
        this.site_link = "https://www.x-rates.com/table/?from=KWD&amount=1";
        this.site_doc = Jsoup.connect(this.site_link).get();
    }

    @Override
    public void scrape() {
        Element ele = this.site_doc.body();
        Elements elems = ele 
            .getElementsByTag("div")
            .select("div.wrapper")
            .select("div.grid.clearfix")
            .select("div#content")
            .select("div.grid");
        ele = elems
            .first()
            .firstElementChild()
            .firstElementChild() //div.col2.pull-right.module.bottomMargin
            .firstElementChild(); //div.moduleContent
        this.list_of_currs = ele
            .select("table.tablesorter.ratesTable")
            .select("tbody")
            .select("tr");
    }

    @Override
    public void produceCurrencies() {
        this.curr_data = new HashMap<String, Currency>();
        Element curr_name, xcge_rate;
        String tmp_link, code;

        for (Element ele : this.list_of_currs) {
            curr_name = ele.child(0);
            xcge_rate = ele.child(1).firstElementChild();
            tmp_link = xcge_rate
                .attr("href");
            code = tmp_link.substring(tmp_link.length() - 3);
            this.curr_data.put(
                code,
                new Currency(
                    code,
                    curr_name.ownText(),
                    Double.parseDouble(xcge_rate.ownText())
                )
            );
        }
    }

    @Override
    public HashMap<String, Currency> getData() {
        return this.curr_data;
    }
}
