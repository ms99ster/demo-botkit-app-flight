package demo.botkit.app.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import koredotai.botkit.sdk.SDK;
import koredotai.botkit.sdk.handler.BotHandler;
import koredotai.botkit.sdk.payload.OnHookPayload;
import koredotai.botkit.sdk.payload.Payload;

@Component
public class FindFlightHandler implements BotHandler {

    private static final Logger log = LoggerFactory.getLogger(FindFlightHandler.class);

    @Autowired
    SDK sdk;

    @Override
    public Payload onUserMessage(String requestId, Payload payload, Object callback) throws Exception {
        log.debug("requestId: " + requestId + "   payload:" + payload + "   callback" + callback);
        sdk.sendBotMessage(payload, callback);
        // sdk.sendUserMessage(payload, callback);
        return payload;
    }

    @Override
    public Payload onBotMessage(String requestId, Payload payload, Object callback) throws Exception {
        log.debug("requestId" + requestId + "   payload:" + payload + "   callback" + callback);
        sdk.sendUserMessage(payload, callback);
        return payload;
    }

    @Override
    public Payload onWebhook(String requestId, Payload payload, Object callback) {
        OnHookPayload hookPayload = (OnHookPayload) payload;
        String componentName = hookPayload.getComponentName();
        log.debug("requestId" + requestId + "   hokkPayload:" + hookPayload + "  componentName " + componentName + "   callback" + callback);
        if ("GetSourceAirports".equals(componentName)) {
            JsonNode context = hookPayload.getContext();
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode airPort = mapper.createObjectNode();
            airPort.put("value", "MEE");
            airPort.put("label", "La Roche Ile Mare Airport [MEE]");
            ((ObjectNode) context).putArray("sourceAirports").add(airPort);
            ((ObjectNode) context).put("SourceAirportName", "La Roche Ile Mare Airport [MEE]");
        } else if ("GetDestAirports".equals(componentName)) {
            JsonNode context = hookPayload.getContext();
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode airPort = mapper.createObjectNode();
            airPort.put("value", "PVG");
            airPort.put("label", "Shanghai Pudong International Airport [PVG]");
            ((ObjectNode) context).putArray("destAirports").add(airPort);
            ((ObjectNode) context).put("DestAirportName", "Shanghai Pudong International Airport [PVG]");
        } else if ("FlightsInfo".equals(componentName)) {
            JsonNode context = hookPayload.getContext();
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode flightResults = mapper.createObjectNode();
            ((ObjectNode) context).put("flightResults", flightResults);
        }
        log.error("onWebhook  " + hookPayload.getContext().toString());
        return payload;
    }

    @Override
    public Payload onAgentTransfer(String requestId, Payload payload, Object callback) {
        log.debug("requestId" + requestId + "   data:" + payload + "   callback" + callback);
        return null;
    }

    @Override
    public Payload onEvent(String requestId, Payload payload, Object callback) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Payload onAlert(String requestId, Payload payload, Object callback) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getBotId() {
        // TODO Auto-generated method stub
        return "st-6a94e21f-fc8e-5ed3-bd48-7cc3682a12bf";
    }

    @Override
    public String getBotName() {
        // TODO Auto-generated method stub
        return "Flight search Sample To Try Bot Kit SDK";
    }

}
