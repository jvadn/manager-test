package com.ch.manager.utils;

import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XMLUtil {

    public static <T> String toXML(T t){
        XStream xs = new XStream();
        xs.alias("xml",t.getClass());
        return xs.toXML(t);
    }

    public static Map<String, String> toMap(HttpServletRequest req) {
        Map<String, String> map = new HashMap<>();
        SAXReader reader = new SAXReader();
        InputStream is = null;
        try {
            is = req.getInputStream();
            Document doc = reader.read(is);
            Element element = doc.getRootElement();
            List<Element> elements = element.elements();
            for (Element e : elements) {
                map.put(e.getName(), e.getText());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return map;
    }

}
