/*
 * Copyright (c) 2019 www.foxtail.cc All rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 * limitations under the License.
 */
package hr.hoprxi.infrastructure.persistence;

import cc.foxtail.assistant.util.UrlHelper;
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

/***
 * @author <a href="mailto:myis1000@gmail.com">guan xiangHuan</a>
 * @since JDK8.0
 * @version 0.0.1 2016年11月25日
 */
public final class Orientdb {
    private static int max;
    private static int min;
    private static String password;
    private static String url;
    private static String username;

    static {
        URL settings = UrlHelper.toUrl(
                UrlHelper.toUniversalFilePath("cc.foxtail.investor.infrastructure.persistence.orientdb.settings.xml"));
        Objects.requireNonNull(settings, "not find settings.xml file");
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            XPath xPath = XPathFactory.newInstance().newXPath();
            DocumentBuilder bulider = factory.newDocumentBuilder();
            Document document = bulider.parse(settings.openStream());
            url = xPath.evaluate("/settings/datasource/url", document);
            username = xPath.evaluate("/settings/datasource/username", document);
            password = xPath.evaluate("/settings/datasource/password", document);
            min = Integer.parseInt(xPath.evaluate("/settings/datasource/pool/@min", document));
            max = Integer.parseInt(xPath.evaluate("/settings/datasource/pool/@max", document));
        } catch (NumberFormatException | XPathExpressionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static OrientGraphFactory factory() {
        return new OrientGraphFactory(url, username, password).setupPool(min, max);
    }
}
