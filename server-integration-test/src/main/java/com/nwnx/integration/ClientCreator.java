package com.nwnx.integration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.glassfish.jersey.apache.connector.ApacheClientProperties;
import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.filter.CsrfProtectionFilter;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

import javax.net.ssl.SSLContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;

public class ClientCreator {
    private static final Logger logger = LoggerFactory.getLogger(ClientCreator.class);

    public static Client create() throws Exception {
        logger.info("Creating Integration test client");

        ClientConfig config = new ClientConfig();
        config.register(CsrfProtectionFilter.class)
                .register(MultiPartFeature.class)
                .register(getJacksonJsonProvider());

        // register logging feature
        setupJulToSlf4j();
        config.property(LoggingFeature.LOGGING_FEATURE_LOGGER_LEVEL_CLIENT, Level.INFO.getName());

        PoolingHttpClientConnectionManager connectionManager = getConnectionManager();
        config.property(ApacheClientProperties.CONNECTION_MANAGER, connectionManager);
        config.connectorProvider(new ApacheConnectorProvider());

        Client client = ClientBuilder.newBuilder().withConfig(config).build();
        logger.info("Created Integration test client");
        return client;
    }


    private static void setupJulToSlf4j() {
        // Optionally remove existing handlers attached to j.u.l root logger
        SLF4JBridgeHandler.removeHandlersForRootLogger();  // (since SLF4J 1.6.5)

        // add SLF4JBridgeHandler to j.u.l's root logger, should be done once during
        // the initialization phase of your application
        SLF4JBridgeHandler.install();
    }

    private static JacksonJsonProvider getJacksonJsonProvider() {
        ObjectMapper mapper = new ObjectMapper()
                .registerModule(new ParameterNamesModule())
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule())
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        return new JacksonJsonProvider(mapper);
    }

    private static PoolingHttpClientConnectionManager getConnectionManager() throws Exception {
        Registry<ConnectionSocketFactory> socketFactoryRegistry = getSocketFactoryRegistry();
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        connectionManager.setMaxTotal(30);
        connectionManager.setDefaultMaxPerRoute(6);
        return connectionManager;
    }

    private static Registry<ConnectionSocketFactory> getSocketFactoryRegistry() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        SSLContextBuilder builder = SSLContexts.custom();
        builder.loadTrustMaterial(null, ((chain, authType) -> true));
        SSLContext sslContext = builder.build();
        SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);

        return RegistryBuilder.<ConnectionSocketFactory>create()
                .register("https", sslConnectionSocketFactory)
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .build();
    }
}
