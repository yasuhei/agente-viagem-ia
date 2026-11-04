package dev.ia;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.guardrail.OutputGuardrail;
import dev.langchain4j.guardrail.OutputGuardrailResult;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ToneGuardrail  implements OutputGuardrail {
    @Inject
    ToneJudge judge;

    @Override
    public OutputGuardrailResult validate(AiMessage aiMessage) {
        if(!judge.isProfessional(aiMessage.text())) {
            return reprompt(aiMessage.text(), "Sua resposta não está no tom profissional esperado. Por favor, reformule sua resposta para que seja mais profissional.");
        }
        return OutputGuardrailResult.success();
    }
}
