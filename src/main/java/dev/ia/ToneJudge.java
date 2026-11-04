package dev.ia;

import dev.langchain4j.service.SystemMessage;
import io.quarkiverse.langchain4j.RegisterAiService;

@RegisterAiService
public interface ToneJudge {
    @SystemMessage("""
            Voce e um auditor de qualidade. Analise se a resposta e profissional, amigavel e educada. Se a resposta nao for profissional, amigavel e educada, reescreva a resposta para que seja profissional, amigavel e educada. Exemplos de resprovação, 
            - "Não é problema meu"  ->  rude
            - "Se vira ai" -> informal demais
            - "Cara, isso é chato -> giria inadequada
            
            Exemplos de aprovação:
            - "Sinto muito, mas isso esta fora da minha alçada."
            - " Por favor, verifique os termos no site."
            
            Response apenas 'true' se for profissional, ou 'false' se não for.
            """)
    boolean isProfessional(String text);

}
