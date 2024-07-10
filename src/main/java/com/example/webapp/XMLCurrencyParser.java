package com.example.webapp;

import java.io.IOException;
import java.net.MalformedURLException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLCurrencyParser
{
    public XMLCurrencyParser() {}

    private static String CURRENCY_URL = "https://www.nbrb.by/Services/XmlExRates.aspx";

    private static Document loadDocument(String url) {
        Document doc = null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        try {
            doc = factory.newDocumentBuilder().parse(new java.net.URL(url).openStream());
        } catch (java.net.ConnectException e) {
            System.err.print(" Oops! Something goes wrong! This is Belarus, baby! \nConnection timed out. ");
            System.err.print(CURRENCY_URL + " is not responsible. Please, try again later");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return doc;
    }












    public static String getCurrency(String currencyCode)
    {
        boolean isCurrencyCodeNext = false;
        Document doc = loadDocument(CURRENCY_URL);

        if (doc != null) {
            NodeList nodes = doc.getFirstChild().getChildNodes();


            for (int i = 0; i < nodes.getLength(); i++) {
                Node parent = nodes.item(i);

                if (parent.getNodeType() == 1) {
                    NodeList childs = parent.getChildNodes();

                    for (int ii = 0; ii < childs.getLength(); ii++) {
                        Node child = childs.item(ii);
                        if (child.hasChildNodes()) {
                            if ((child.getNodeName().trim().equalsIgnoreCase("Rate")) && (isCurrencyCodeNext)) {
                                isCurrencyCodeNext = false;
                                return child.getFirstChild().getTextContent();
                            }

                            if (child.getFirstChild().getTextContent().trim().equalsIgnoreCase(currencyCode)) {
                                isCurrencyCodeNext = true;
                            }
                        }
                    }
                }
            }
        }
        return "0.0";
    }
}
