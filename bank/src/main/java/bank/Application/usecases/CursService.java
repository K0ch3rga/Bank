package bank.Application.usecases;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import bank.Domain.CurrencyType;

@Service
public class CursService {
    private LocalDate lastUpdate;
    private HashMap<String, Float> currenciesCost = new HashMap<>();

    public float getCost(CurrencyType cur) {
        checkOutdated();
        return currenciesCost.getOrDefault(cur.getCode(), 1f);
    }

    private synchronized void checkOutdated() {
        if ( LocalDate.now().minusDays(1).isAfter(lastUpdate) ) {
            getCursData();
        }
    }

    private synchronized void getCursData() {
        HttpClient client = HttpClient.newHttpClient();
        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://www.cbr.ru/scripts/XML_daily.asp"))
                .build();
            HttpResponse<Path> response = client.send(request, HttpResponse.BodyHandlers.ofFile(Paths.get("rate")));
            File file = response.body().toFile();

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder(); 
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize(); 

            NodeList nodeList = doc.getElementsByTagName("ValCurs");
            LocalDate cursDate = LocalDate.parse(nodeList.item(0).getAttributes().getNamedItem("Date").getNodeValue(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            if (cursDate.isEqual(lastUpdate)) {
                return;
            }

            lastUpdate = cursDate;
            NodeList valutes = doc.getElementsByTagName("cursDate");
            for (int i = 0; i < valutes.getLength(); i++) {
                Node node = valutes.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE){
                    Element valute = (Element) node;
                    String code = valute.getElementsByTagName("NumCode").item(0).getNodeValue();

                    String vunitRate = valute.getElementsByTagName("VunitRate").item(0).getNodeValue();
                    float rate = Float.parseFloat(vunitRate);

                    currenciesCost.put(code, rate);
                }
            }
        } catch (URISyntaxException e) {
            // TODO: handle exception
        } catch (InterruptedException e) {

        } catch (IOException e) {

        } catch (ParserConfigurationException e) {
            
        } catch (SAXException e) {

        }
    }

}
