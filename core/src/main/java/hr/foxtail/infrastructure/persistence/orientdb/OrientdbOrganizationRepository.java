/*
 * OrientdbOrganizationRepository.java
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

import cc.foxtail.assistant.id.LongId;
import cc.foxtail.investor.domain.model.organization.*;
import cc.foxtail.investor.domain.model.organization.Organization.Status;
import cc.foxtail.investor.domain.model.share.*;
import cc.foxtail.investor.infrastructure.EncryptionServiceAdapter;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Element;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;
import com.tinkerpop.gremlin.java.GremlinPipeline;
import com.tinkerpop.pipes.Pipe;
import com.tinkerpop.pipes.PipeFunction;
import hr.foxtail.domain.model.organization.Organization;
import org.nustaq.serialization.FSTConfiguration;

import javax.money.MonetaryAmount;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/***
 * @author <a href="mailto:myis1000@126.com">guan xiangHuan</a>
 * @since JDK8.0
 * @version 0.0.1 20170328
 */
public final class OrientdbOrganizationRepository implements OrganizationRepository {
    private static final String DECORATION = ".*";
    private static final FSTConfiguration FST = FSTConfiguration.createDefaultConfiguration();
    private static EncryptionServiceAdapter ENCRYPTION_SERVICE = new EncryptionServiceAdapter();
    private OrientGraphFactory factory;

    /**
     * @param factory
     */
    public OrientdbOrganizationRepository(OrientGraphFactory factory) {
        super();
        this.factory = factory;
    }

    private void dispatchAccount(OrientGraph graph, Vertex vertex, long id, Set<Account> accounts) {
        if (null == accounts)
            return;
        for (Account account : accounts) {
            Vertex aVertex = graph.addVertex("class:Account", "sign", account.sign(), "account",
                    ENCRYPTION_SERVICE.encryp(id, account.account()));
            if (null != account.accountName())
                aVertex.setProperty("accountName", account.accountName());
            if (null != account.bankAccount())
                aVertex.setProperty("bankAccount", account.bankAccount());
            vertex.addEdge("Has", aVertex);
        }
    }

    /**
     * @param graph
     * @param vertex
     * @param pie
     */
    private void dispatchInvestmentPie(OrientGraph graph, Vertex vertex, InvestmentPie investmentPie) {
        if (null == investmentPie)
            return;
        for (Investment investment : investmentPie.investments()) {
            Investor investor = investment.investor();
            InvestorType type = investor.investorType();
            switch (type) {
                case PERSON:
                    Pipe<Vertex, ? extends Element> pPipe = new GremlinPipeline<Vertex, Vertex>(
                            graph.getVerticesOfClass("Person", false)).has("key", investor.personOrOrganizationId());
                    if (pPipe.hasNext()) {
                        Edge e = ((Vertex) pPipe.next()).addEdge("Invest", vertex);
                        setInvestProperty(e, investmentPie, investment, type);
                    } else {
                        Edge e = graph.addVertex("class:Person", "key", investor.personOrOrganizationId()).addEdge("Invest",
                                vertex);
                        setInvestProperty(e, investmentPie, investment, type);
                    }
                case ORGANIZATION:
                    Pipe<Vertex, ? extends Element> oPipe = new GremlinPipeline<Vertex, Vertex>(
                            graph.getVerticesOfClass("Organization")).has("key", investor.personOrOrganizationId());
                    if (oPipe.hasNext()) {
                        Edge e = ((Vertex) oPipe.next()).addEdge("Invest", vertex);
                        setInvestProperty(e, investmentPie, investment, type);
                    }
            }
        }
    }

    /**
     * @param graph
     * @param vertex
     * @param licenses
     */
    private void dispatchLicense(OrientGraph graph, Vertex vertex, Set<License> licenses) {
        if (null == licenses)
            return;
        for (License license : licenses) {
            Vertex v = graph.addVertex("class:License", "name", license.name(), "number", license.number());
            if (null != license.issuer())
                v.setProperty("issuer", license.issuer());
            if (null != license.issueOn())
                v.setProperty("issueOn", license.issueOn());
            if (null != license.expiryOn())
                v.setProperty("expiryOn", license.expiryOn());
            if (null != license.picture())
                v.setProperty("picture", ImageBytesConversion.imageToByte(license.picture()));
            vertex.addEdge("Permit", v);
        }

    }

    /**
     * @param pie
     * @param graph
     * @param vertex
     */
    private void dispatchSharePie(OrientGraph graph, Vertex vertex, SharePie sharePie) {
        if (null == sharePie)
            return;
        for (Share share : sharePie.shares()) {
            Investor investor = share.investor();
            InvestorType type = investor.investorType();
            switch (type) {
                case PERSON:
                    Pipe<Vertex, ? extends Element> pPipe = new GremlinPipeline<Vertex, Vertex>(
                            graph.getVerticesOfClass("Person", false)).has("key", investor.personOrOrganizationId());
                    if (pPipe.hasNext()) {
                        Edge e = ((Vertex) pPipe.next()).addEdge("Share", vertex);
                        setShareProperty(e, share, type);
                    } else {
                        Edge e = graph.addVertex("class:Person", "key", investor.personOrOrganizationId()).addEdge("Share",
                                vertex);
                        setShareProperty(e, share, type);
                    }
                case ORGANIZATION:
                    Pipe<Vertex, ? extends Element> oPipe = new GremlinPipeline<Vertex, Vertex>(
                            graph.getVerticesOfClass("Organization", false)).has("key", investor.personOrOrganizationId());
                    if (oPipe.hasNext()) {
                        Edge e = ((Vertex) oPipe.next()).addEdge("Share", vertex);
                        setShareProperty(e, share, type);
                    }
            }
        }
    }

    @Override
    public long nextIdentity() {
        return LongId.generate();
    }

    public Organization find(String unifiedSocialCreditCode) {
        OrientGraph graph = factory.getTx();
        try {
            Pipe<Vertex, ? extends Element> pipe = new GremlinPipeline<Vertex, Vertex>(
                    graph.getVerticesOfClass("Organization", false)).has("unifiedSocialCreditCode", unifiedSocialCreditCode);
            if (pipe.hasNext()) {
                return rebulider(pipe.next());
            }
        } finally {
            graph.shutdown();
        }
        return null;
    }

    @Override
    public Organization ofId(long id) {
        OrientGraph graph = factory.getTx();
        try {
            Pipe<Vertex, ? extends Element> pipe = new GremlinPipeline<Vertex, Vertex>(
                    graph.getVerticesOfClass("Organization", false)).has("key", id);
            if (pipe.hasNext()) {
                return rebulider(pipe.next());
            }
        } finally {
            graph.shutdown();
        }
        return null;
    }

    public Organization[] findByMnemonic(String mnemonic) {
        OrientGraph graph = factory.getTx();
        try {
            Pipe<Vertex, ? extends Element> pipe = new GremlinPipeline<Vertex, Vertex>(
                    graph.getVerticesOfClass("Organization", false)).filter(new PipeFunction<Vertex, Boolean>() {
                @Override
                public Boolean compute(Vertex argument) {
                    return argument.<String>getProperty("mnemonic").matches(DECORATION + mnemonic + DECORATION);
                }
            });
            Organization[] organizations = new HashSet<Organization>();
            while (pipe.hasNext())
                organizations.add(rebulider(pipe.next()));
            return organizations;
        } finally {
            graph.shutdown();
        }
    }

    public Organization[] findByName(String name) {
        OrientGraph graph = factory.getTx();
        try {
            Pipe<Vertex, ? extends Element> pipe = new GremlinPipeline<Vertex, Vertex>(
                    graph.getVerticesOfClass("Organization", false)).filter(new PipeFunction<Vertex, Boolean>() {
                @Override
                public Boolean compute(Vertex argument) {
                    return argument.<String>getProperty("name").matches(DECORATION + name + DECORATION);
                }
            });
            Organization[] organizations = new HashSet<Organization>();
            while (pipe.hasNext())
                organizations.add(rebulider(pipe.next()));
            return organizations;
        } finally {
            graph.shutdown();
        }
    }

    private Organization rebulider(Element element) {
        // sharePie
        SharePie sharePie = null;
        Pipe<Vertex, Edge> pipe = new GremlinPipeline<Vertex, Vertex>(element).inE("Share");
        if (pipe.hasNext()) {
            Set<Share> shares = new HashSet<Share>();
            while (pipe.hasNext()) {
                Edge e = pipe.next();
                InvestorType type = InvestorType.valueOf(e.<String>getProperty("investorType"));
                shares.add(new Share(new Investor(type, e.getVertex(Direction.OUT).<Long>getProperty("key")),
                        e.<Long>getProperty("quantity")));
            }
            sharePie = new SharePie(shares);
        }
        // InvestmentPie
        InvestmentPie investmentPie = null;
        pipe = new GremlinPipeline<Vertex, Vertex>(element).inE("Invest");
        if (pipe.hasNext()) {
            Set<Investment> investments = new HashSet<Investment>();
            MonetaryAmount limit = null;
            while (pipe.hasNext()) {
                Edge e = pipe.next();
                InvestorType type = InvestorType.valueOf(e.<String>getProperty("investorType"));
                investments.add(new Investment(new Investor(type, e.getVertex(Direction.OUT).<Long>getProperty("key")),
                        (MonetaryAmount) FST.asObject(e.<byte[]>getProperty("money"))));
                limit = (MonetaryAmount) FST.asObject(e.<byte[]>getProperty("limit"));
            }
            investmentPie = new InvestmentPie(limit, null, investments);
        }
        // license
        Pipe<Vertex, Vertex> lPipe = new GremlinPipeline<Vertex, Vertex>(element).out("Permit");
        Set<License> licenses = null;
        if (lPipe.hasNext()) {
            licenses = new HashSet<License>();
            while (lPipe.hasNext()) {
                Vertex vertex = lPipe.next();
                licenses.add(new License(vertex.<String>getProperty("name"), vertex.<String>getProperty("number"),
                        vertex.<String>getProperty("issuer"), vertex.<Date>getProperty("issueOn"),
                        vertex.<Date>getProperty("expiryOn"),
                        ImageBytesConversion.bytesToImage(vertex.<byte[]>getProperty("picture"))));
            }
        }
        // account
        Set<Account> accounts = null;
        Pipe<Vertex, Vertex> vPipe = new GremlinPipeline<Vertex, Vertex>(element).out("Has");
        if (vPipe.hasNext()) {
            accounts = new HashSet<Account>();
            while (vPipe.hasNext()) {
                Vertex vertex = vPipe.next();
                accounts.add(new Account(Sign.valueOf(vertex.<String>getProperty("sign")),
                        vertex.<String>getProperty("account"), vertex.<String>getProperty("bankAccount"),
                        vertex.<String>getProperty("accountName")));
            }
        }
        // return
        return new Organization(element.<Long>getProperty("key"), element.<String>getProperty("creditNumber"),
                element.<String>getProperty("name"), element.<String>getProperty("mnemonic"), investmentPie, sharePie,
                Status.valueOf(element.<String>getProperty("status")),
                (Address) FST.asObject(element.<byte[]>getProperty("address")), licenses, accounts,
                ImageBytesConversion.bytesToImage(element.<byte[]>getProperty("logo")),
                element.<String>getProperty("aboutUs"));
    }

    @Override
    public void remove(String unifiedSocialCreditCode) {
        OrientGraph graph = factory.getTx();
        try {
            Pipe<Vertex, ? extends Element> pipe = new GremlinPipeline<Vertex, Vertex>(
                    graph.getVerticesOfClass("Organization", false)).has("key", unifiedSocialCreditCode.id());
            if (pipe.hasNext()) {
                Element element = pipe.next();
                Pipe<Vertex, Vertex> lPipe = new GremlinPipeline<Vertex, Vertex>(element).out("Permit", "Has");
                while (lPipe.hasNext())
                    lPipe.next().remove();
                element.remove();
            }
            graph.commit();
        } finally {
            graph.shutdown();
        }
    }

    @Override
    public void save(Organization organization) {
        OrientGraph graph = factory.getTx();
        try {
            Pipe<Vertex, ? extends Element> pipe = new GremlinPipeline<Vertex, Vertex>(
                    graph.getVerticesOfClass("Organization", false)).has("key", organization.id());
            // update
            if (pipe.hasNext()) {
                Vertex vertex = (Vertex) pipe.next();
                vertex.setProperty("name", organization.name());
                vertex.setProperty("mnemonic", organization.mnemonic());
                vertex.setProperty("status", organization.status());
                vertex.setProperty("address", FST.asByteArray(organization.address()));
                pipe = new GremlinPipeline<Vertex, Vertex>(vertex).out("Permit", "Has");
                while (pipe.hasNext())
                    pipe.next().remove();
                pipe = new GremlinPipeline<Vertex, Edge>(vertex).inE("Share", "Invest");
                while (pipe.hasNext())
                    pipe.next().remove();
                setOrganizationProperty(graph, vertex, organization);
                dispatchLicense(graph, vertex, organization.licenses());
                dispatchAccount(graph, vertex, organization.id(), organization.accounts());
                dispatchSharePie(graph, vertex, organization.sharePie());
                dispatchInvestmentPie(graph, vertex, organization.investmentPie());
            }
            // new
            else {
                Vertex vertex = graph.addVertex("class:Organization", "key", organization.id(), "name",
                        organization.name(), "mnemonic", organization.mnemonic(), "address",
                        FST.asByteArray(organization.address()), "status", organization.status());
                setOrganizationProperty(graph, vertex, organization);
                dispatchLicense(graph, vertex, organization.licenses());
                dispatchAccount(graph, vertex, organization.id(), organization.accounts());
                dispatchSharePie(graph, vertex, organization.sharePie());
                dispatchInvestmentPie(graph, vertex, organization.investmentPie());
            }
            graph.commit();
        } finally {
            graph.shutdown();
        }
    }

    /**
     * @param e
     * @param investmentPie
     * @param investment
     */
    private void setInvestProperty(Edge e, InvestmentPie investmentPie, Investment investment, InvestorType type) {
        e.setProperty("money", FST.asByteArray(investment.money()));
        e.setProperty("limit", FST.asByteArray(investmentPie.limit()));
        e.setProperty("investorType", type);
    }

    /**
     * @param graph
     * @param vertex
     * @param organization
     */
    private void setOrganizationProperty(OrientGraph graph, Vertex vertex, Organization organization) {
        if (null != organization.logo())
            vertex.setProperty("logo", ImageBytesConversion.imageToByte(organization.logo()));
        if (null != organization.aboutUs())
            vertex.setProperty("aboutUs", organization.aboutUs());
        if (null != organization.creditNumber())
            vertex.setProperty("creditNumber", organization.creditNumber());
    }

    /**
     * @param e
     * @param share
     * @param type
     */
    private void setShareProperty(Edge e, Share share, InvestorType type) {
        e.setProperty("quantity", share.quantity());
        e.setProperty("investorType", type);
    }
}
