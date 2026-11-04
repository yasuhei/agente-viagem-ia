package dev.ia;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.guardrail.OutputGuardrail;
import dev.langchain4j.guardrail.OutputGuardrailResult;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

import java.io.StringReader;

@ApplicationScoped
public class JsonStructoreGuard implements OutputGuardrail {

    @Override
    public OutputGuardrailResult validate(AiMessage aiMessage) {
        String response = aiMessage.text();
        try  (JsonReader reader = Json.createReader(new StringReader(response))) {
            JsonObject jsonObject = reader.readObject();
            return OutputGuardrailResult.success();
        }

        catch (Exception e) {
            return reprompt(aiMessage.text(), "A resposta do modelo não está em formato JSON válido. Por favor, forneça uma resposta JSON válida.");
        }}
}
