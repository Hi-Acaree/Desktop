module org.acaree.aptscheduledesk {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.slf4j;
    requires unirest.java;

    requires ch.qos.logback.classic;
    requires spring.websocket;
    requires spring.messaging;
    requires com.fasterxml.jackson.databind;
    requires org.apache.httpcomponents.httpclient;


    opens org.acaree.aptscheduledesk.aptscheduledesk.controller to javafx.fxml;
    opens org.acaree.aptscheduledesk.aptscheduledesk.ui to javafx.fxml;

    exports org.acaree.aptscheduledesk.aptscheduledesk to javafx.graphics;

    exports org.acaree.aptscheduledesk.aptscheduledesk.controller;
}