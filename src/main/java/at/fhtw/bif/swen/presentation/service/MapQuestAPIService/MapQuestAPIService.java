package at.fhtw.bif.swen.presentation.service.MapQuestAPIService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;

public class MapQuestAPIService {
    private static final String ROUTE_API = "https://www.mapquestapi.com/directions/v2/route";
    private static final String STATIC_MAP_API = "https://www.mapquestapi.com/staticmap/v5/map";
    private static final String API_KEY = System.getProperty("MAP_API_KEY");
    private static final Logger logger = LogManager.getLogger(MapQuestAPIService.class.getName());

    public static CompletableFuture<TourMapData> getTourData(String from, String to) throws URISyntaxException {
        String requestUrl = ROUTE_API + "?key=" + API_KEY + "&from=" + URLEncoder.encode(from, StandardCharsets.UTF_8)
                    + "&to=" + URLEncoder.encode(to, StandardCharsets.UTF_8);
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(new URI(requestUrl)).build();
        logger.debug("Sending request to MAP API");
        logger.debug(requestUrl);
        return httpClient.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString())
                .thenApply(
                        stringHttpResponse -> {
                            try {
                                return parseResponse(stringHttpResponse.body());
                            } catch (JsonProcessingException e) {
                                logger.error("Error parsing HTTP response");
                                e.printStackTrace();
                            }
                            return null;
                        });
    }

    private static TourMapData parseResponse(String toParse) throws JsonProcessingException {
        logger.debug("Parse HTTP response");
        var objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(toParse).path("route");
        if (rootNode.isEmpty() || !rootNode.path("routeError").isEmpty()) {
            logger.error("Failed to parse HTTP response");
            return null;
        }
        JsonNode boundingBox = rootNode.path("boundingBox");

        return new TourMapData(rootNode.path("sessionId").asText(),
                rootNode.path("distance").asInt(),
                boundingBox.path("ul").path("lat").asText() + "," +
                        boundingBox.path("ul").path("lng").asText() + "," +
                        boundingBox.path("lr").path("lat").asText() + "," +
                        boundingBox.path("lr").path("lng").asText());
    }

    public static String buildStaticMapRequest(TourMapData tourMapData) {
        logger.debug("Building URL for static map");
        if (tourMapData == null) {
            return null;
        }
        return STATIC_MAP_API + "?key=" + API_KEY +
                "&session=" + tourMapData.getSessionId() +
                "&boundingBox=" + tourMapData.getBoundingBox() +
                "&size=500,400" +
                "&format=jpg90";
    }
}
