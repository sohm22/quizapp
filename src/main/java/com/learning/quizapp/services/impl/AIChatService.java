package com.learning.quizapp.services.impl;

import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AIChatService {

    private final OpenAiChatModel chatModel;
    private  final String regex = "\\[(.*?)]";
    final Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);

    public AIChatService(OpenAiChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String queryAiService(String userQuery, String extraInfo) {
        String modifiedQuery = userQuery + extraInfo;
        ChatResponse modelResponse = chatModel.call(
                new Prompt(
                        modifiedQuery,
                        OpenAiChatOptions.builder()
                                .withModel("llama3-8b-8192")
                                .withTemperature(1.0f)
                                //.withResponseFormat(
                                //        new OpenAiApi.ChatCompletionRequest.ResponseFormat(OpenAiApi.ChatCompletionRequest.ResponseFormat.Type.JSON_OBJECT, "{\"questionTitle\": \"What is a constructor?\", \"option1\": \"A member of a class\", \"option2\": \"A loop in Python\", \"option3\": \"A data type\", \"option4\": \"A special method\", \"correctAnswer\": \"A special method\", \"difficultyLevel\": \"Medium\", \"category\": \"java\"}\n"))
                                .build()
                ));

        String content = modelResponse.getResult().getOutput().getContent();
        final Matcher matcher = pattern.matcher(content);
        if (matcher.find()) {
            return matcher.group(0);
        }
        return null;
    }
}
