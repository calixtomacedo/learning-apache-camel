package br.com.cmdev.learning.apachecamel.routers;

import br.com.cmdev.learning.apachecamel.processors.IntegrationFileProcessor;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.file.FileConstants;
import org.springframework.stereotype.Component;

@Component
public class IntegrationFileRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        // consumer
        from("file:/temp/apache-camel/input?delay=5000")
                .routeId("integration-file-id")
                .log(LoggingLevel.INFO, "Processando o arquivo: ${file:name}")
                .setHeader(FileConstants.FILE_NAME, simple("${date:now:ddMMyyyyHHmmssSSS}_${file:name}"))

                .process(new IntegrationFileProcessor())

                // producer
                .to("file:/temp/apache-camel/output")

                .end();

    }

}

