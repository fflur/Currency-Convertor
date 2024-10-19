package org.database;

import org.database.Curr;
import org.scrapers.Currency;
import nu.xom.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.FileSystems;

public class Operator {
    public Document currencies;
    public List<Curr> curr_list;
    public Map<String, Currency> map;

    public Operator() throws ParsingException, IOException {
        Builder builder = new Builder();
        this.currencies =  builder.build("list-one.xml");
        this.curr_list = new ArrayList<Curr>();
        this.map = new HashMap<String, Currency>();
    }

    public void loadOldCurrencies() {
        Element curr_table = this.currencies.getRootElement().getFirstChildElement("CcyTbl");
        Elements curr_entries = curr_table.getChildElements();

        for (int i = 0; i < curr_entries.size(); i++) {
            Element ele = curr_entries.get(i);
            String country, name, code, number;
            try {
                country = ele.getFirstChildElement("CtryNm").getValue();
                name = ele.getFirstChildElement("CcyNm").getValue();
                code = ele.getFirstChildElement("Ccy").getValue();
                number = ele.getFirstChildElement("CcyNbr").getValue();
            } catch (Exception e) { continue; }
            this.curr_list.add(
                new Curr(country, name, code, Integer.parseInt(number))
            );
        }
    }

    public void convertor() {
        while (this.curr_list.size() > 0) {
            Curr curr_ob = this.curr_list.removeLast();
            if (this.map.containsKey(curr_ob.code) == true) {
                this.map.get(curr_ob.code).addCountry(curr_ob.country);
            } else if (this.map.containsKey(curr_ob.code) == false) {
                Currency cy = new Currency(
                    curr_ob.code,
                    curr_ob.name,
                    curr_ob.number
                );
                this.map.put(curr_ob.code, cy);
            }
        }
    }

    public void writeOut() throws IOException {
        Element root = new Element("Currencies");
        for (Map.Entry<String, Currency> entry : map.entrySet()) {
            root.appendChild(entry.getValue().toXML());
        }
        Document output = new Document(root);
        Serializer srlzr = new Serializer(
            Files.newOutputStream(FileSystems.getDefault().getPath("Currencies.xml"))
        );
        srlzr.setIndent(2);
        srlzr.write(output);
    }
}
