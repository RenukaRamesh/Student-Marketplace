package com.renuka.marketplace.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.net.http.*;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AiController {

    @Value("${groq.api.key:NOT_SET}")
    private String groqKey;

    @PostMapping("/generate-description")
    public ResponseEntity<Map<String, String>> generateDescription(
            @RequestBody Map<String, String> body) {
        String title = body.getOrDefault("title", "").trim();
        if (title.isEmpty())
            return ok("Please enter a project title first.");
        String prompt =
                "Write a short professional freelance project description for: "
                        + title + ". 3-4 sentences. Include what to build, "
                        + "deliverables, and skills needed. No bullet points.";
        return callGroq(prompt);
    }

    @PostMapping("/rank-bids")
    public ResponseEntity<Map<String, String>> rankBids(
            @RequestBody Map<String, String> body) {
        String bids = body.getOrDefault("bids", "").trim();
        if (bids.isEmpty()) return ok("No bids to rank.");
        String prompt =
                "Rank these freelancer bids from best to worst. "
                        + "Show 🥇🥈🥉 medal, name, one short reason for each. "
                        + "End with: 💡 Recommendation: one sentence.\n\n" + bids;
        return callGroq(prompt);
    }

    private ResponseEntity<Map<String, String>> callGroq(String prompt) {
        try {
            String requestBody =
                    "{\"model\":\"llama-3.1-8b-instant\","
                            + "\"messages\":[{\"role\":\"user\",\"content\":\""
                            + esc(prompt) + "\"}],"
                            + "\"max_tokens\":400}";

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest req = HttpRequest.newBuilder()
                    .uri(URI.create(
                            "https://api.groq.com/openai/v1/chat/completions"))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + groqKey)
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> resp =
                    client.send(req,
                            HttpResponse.BodyHandlers.ofString());

            System.out.println("Groq Status: " + resp.statusCode());

            if (resp.statusCode() == 200) {
                System.out.println("✅ Groq AI working!");
                return ok(extractGroq(resp.body()));
            } else if (resp.statusCode() == 429) {
                return ok("Rate limited. Wait 1 min and try again.");
            } else {
                System.out.println("Groq Error: " + resp.body());
                return ok("AI error. Try again.");
            }
        } catch (Exception e) {
            System.out.println("Groq Exception: " + e.getMessage());
            return ok("Could not reach AI. Check internet.");
        }
    }

    private String extractGroq(String json) {
        try {
            // Groq returns OpenAI format:
            // {"choices":[{"message":{"content":"response here"}}]}
            String marker = "\"content\":\"";
            int idx = json.lastIndexOf(marker);
            if (idx == -1) return "No response.";
            int start = idx + marker.length();
            int end = start;
            while (end < json.length()) {
                if (json.charAt(end) == '"'
                        && json.charAt(end - 1) != '\\') break;
                end++;
            }
            return json.substring(start, end)
                    .replace("\\n", "\n")
                    .replace("\\\"", "\"")
                    .replace("\\\\", "\\");
        } catch (Exception e) {
            return "Parse error.";
        }
    }

    private ResponseEntity<Map<String, String>> ok(String text) {
        return ResponseEntity.ok(Map.of("text", text));
    }

    private String esc(String t) {
        return t.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r");
    }
}