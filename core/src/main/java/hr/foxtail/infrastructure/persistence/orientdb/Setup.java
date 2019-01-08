/*
 * Setup.java
 *
 * Copyright 2016 www.foxtail.cc rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package hr.foxtail.infrastructure.persistence.orientdb;

import cc.foxtail.assistant.util.UrlHelper;
import com.orientechnologies.orient.client.remote.OServerAdmin;
import com.orientechnologies.orient.core.metadata.schema.OClass.INDEX_TYPE;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.tinkerpop.blueprints.impls.orient.OrientEdgeType;
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;
import com.tinkerpop.blueprints.impls.orient.OrientGraphNoTx;
import com.tinkerpop.blueprints.impls.orient.OrientVertexType;
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
 * @version 0.0.1 2016年11月13日
 */
public final class Setup {
    private static String database;
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
            database = url.substring(url.lastIndexOf('/') + 1);
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

    public static void main(String[] args) throws IOException {
        Setup setup = new Setup();
        setup.createDatabse();
    }

    public void createDatabse() throws IOException {
        OServerAdmin serverAdmin = null;
        try {
            serverAdmin = new OServerAdmin(url);
            serverAdmin.connect(username, password);
            if (!serverAdmin.existsDatabase()) {
                System.out.println("Creating database...");
                serverAdmin.createDatabase(database, "graph", "plocal");
                newSchema();
                System.out.println("Database created");
            } else {
                System.out.println("Database already exists,deleteing database...");
                serverAdmin.dropDatabase("plocal");
                System.out.println("Database deleted");
            }
        } finally {
            if (serverAdmin != null)
                serverAdmin.close();
        }
    }

    private void newSchema() {
        OrientGraphFactory factory = new OrientGraphFactory(url, username, password).setupPool(min, max);
        OrientGraphNoTx graphNoTx = factory.getNoTx();
        // Organization vertex
        OrientVertexType v = graphNoTx.createVertexType("Organization");
        v.createProperty("key", OType.LONG).setOrdered(true).setNotNull(true).setMandatory(true).setReadonly(true)
                .createIndex(INDEX_TYPE.UNIQUE_HASH_INDEX);
        v.createProperty("creditNumber", OType.STRING).setOrdered(true).createIndex(INDEX_TYPE.UNIQUE_HASH_INDEX,
                new ODocument().field("ignoreNullValues", true));
        v.createProperty("name", OType.STRING).setOrdered(true).setNotNull(true).setMandatory(true)
                .createIndex(INDEX_TYPE.NOTUNIQUE_HASH_INDEX);
        v.createProperty("mnemonic", OType.STRING).setNotNull(true).setMandatory(true)
                .createIndex(INDEX_TYPE.NOTUNIQUE_HASH_INDEX);
        v.createProperty("status", OType.STRING).setNotNull(true).setMandatory(true);
        v.createProperty("address", OType.BINARY);
        v.createProperty("logo", OType.BINARY);
        v.createProperty("aboutUs", OType.STRING);
        // Person vertex
        v = graphNoTx.createVertexType("Person");
        v.createProperty("key", OType.LONG).setNotNull(true).setMandatory(true).setReadonly(true)
                .createIndex(INDEX_TYPE.UNIQUE_HASH_INDEX);
        // vertex License
        v = graphNoTx.createVertexType("License");
        v.createProperty("name", OType.STRING);
        v.createProperty("number", OType.STRING);
        v.createProperty("issuer", OType.STRING);
        v.createProperty("issueOn", OType.DATETIME);
        v.createProperty("expiryOn", OType.DATETIME);
        v.createProperty("picture", OType.BINARY);
        // vertex account
        v = graphNoTx.createVertexType("Account");
        v.createProperty("sign", OType.STRING);
        v.createProperty("account", OType.STRING);
        v.createProperty("bankAccount", OType.STRING);
        v.createProperty("accountName", OType.STRING);
        // Share edge(Person->Organization or Organization->Organization)
        OrientEdgeType e = graphNoTx.createEdgeType("Share");
        e.createProperty("quantity", OType.LONG);
        e.createProperty("investorType", OType.STRING);
        // Percent edge((Person->Organization or Organization->Organization)
        e = graphNoTx.createEdgeType("Invest");
        e.createProperty("percent", OType.DECIMAL);
        e.createProperty("money", OType.BINARY);
        e.createProperty("limit", OType.BINARY);
        e.createProperty("investorType", OType.STRING);
        // Permit edge(Organization->License)
        graphNoTx.createEdgeType("Permit");
        // edge has(Organization->Account)
        graphNoTx.createEdgeType("Has");
        graphNoTx.shutdown();
    }
}
