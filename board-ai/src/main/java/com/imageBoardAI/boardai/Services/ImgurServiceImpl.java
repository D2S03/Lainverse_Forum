package com.imageBoardAI.boardai.Services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.Map;

@Service
public class ImgurServiceImpl implements ImgurService {
    private final RestTemplate restTemplate;
    private final String imgurApiEndpoint = "https://api.imgur.com/3";

    @Value("${imgur.clientId}")
    private String clientId;

    public ImgurServiceImpl() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public String uploadImage(File imageFile) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.set("Authorization", "Client-ID " + clientId);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("image", new FileSystemResource(imageFile));

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        ResponseEntity<Map> response = restTemplate.exchange(
                imgurApiEndpoint + "/upload",
                HttpMethod.POST,
                requestEntity,
                Map.class
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            Map<String, Object> responseBody = response.getBody();
            if (responseBody != null && responseBody.containsKey("data")) {
                Map<String, Object> data = (Map<String, Object>) responseBody.get("data");
                if (data.containsKey("link")) {
                    return (String) data.get("link");
                }
            }
        }
        throw new RuntimeException("Image upload has failed!");
    }
}
