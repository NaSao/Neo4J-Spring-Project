package hello.config;

import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.server.Neo4jServer;
import org.springframework.data.neo4j.server.RemoteServer;
import org.springframework.data.repository.query.QueryLookupStrategy;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * Database Configuration
 * Created by Panos on 31-Dec-15.
 */

@Configuration
@EnableNeo4jRepositories(basePackages = "hello.repository", queryLookupStrategy = QueryLookupStrategy.Key.CREATE_IF_NOT_FOUND)
@EnableTransactionManagement
public class DatabaseConfiguration extends Neo4jConfiguration {

    private final Logger log = LoggerFactory.getLogger(DatabaseConfiguration.class);

    @Override
    @Bean
    public Neo4jServer neo4jServer() {
        log.info("Initialising server connection");
        return new RemoteServer("http://localhost:7474", "neo4j", "neo4j_322nyq");
    }

    @Override
    @Bean
    public SessionFactory getSessionFactory() {
        log.info("Initialising Session Factory");
        return new SessionFactory("hello.domain");
    }

    @Override
    @Bean
    public Session getSession() throws Exception {
        log.info("Initialising session-scoped Session Bean");
        return super.getSession();
    }
}