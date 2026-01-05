package academy.devdojo.config;

import academy.devdojo.controller.ConnectionController;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;

@Slf4j
@WebMvcTest(ConnectionController.class)
@Import(ConnectionBeanConfiguration.class)
class ConnectionBeanConfigurationTest {

    @Autowired
    private ConnectionBeanConfiguration connectionBeanConfiguration;

    @Test
    void connectionMysql() {
        log.info(connectionBeanConfiguration.connectionMysql().toString());
    }
}