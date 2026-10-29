package dev.ia;

import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.rag.DefaultRetrievalAugmentor;
import dev.langchain4j.rag.RetrievalAugmentor;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import jakarta.inject.Singleton;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import dev.langchain4j.data.segment.TextSegment;

@ApplicationScoped
public class RagConfiguration {

    @Produces
    @Singleton
    public EmbeddingStore<TextSegment> embeddingStore() {
        return new InMemoryEmbeddingStore<>();
    }

    @Produces
    public RetrievalAugmentor retrievalAugmentor(EmbeddingStore<TextSegment> embeddingStore, EmbeddingModel embeddingModel) {
        return DefaultRetrievalAugmentor.builder()
                .contentRetriever(EmbeddingStoreContentRetriever.builder()
                        .embeddingStore(embeddingStore)
                        .embeddingModel(embeddingModel)
                        .maxResults(5)
                        .build())
                .build();
    }
}