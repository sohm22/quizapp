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
    private final String regex = "\\[(.*?)]";
    private final Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
    private static final String MODEL_NAME = "llama-3.1-70b-versatile";
    private static final String MAX_QUESTIONS = "10";
    private static final String DIFFICULTY_LEVEL = "mixed of easy, medium, hard ";
    // Prompt creation
    private static final String PROMPT = "Generate " + MAX_QUESTIONS + " multiple choice questions with a difficulty level of " + DIFFICULTY_LEVEL
            + " on the topic of %s. Ensure the correct answer is one of the options. "
            + "The output format should be an array of JSON objects with the following properties: "
            + "questionTitle, option1, option2, option3, option4, correctAnswer, difficultyLevel, and category. "
            + "Sample output format:\n"
            + "[\n"
            + "    {\n"
            + "        \"questionTitle\": \"Question Title\",\n"
            + "        \"option1\": \"Option 1\",\n"
            + "        \"option2\": \"Option 2\",\n"
            + "        \"option3\": \"Option 3\",\n"
            + "        \"option4\": \"Option 4\",\n"
            + "        \"correctAnswer\": \"Correct answer\",\n"
            + "        \"difficultyLevel\": \"Difficulty level\",\n"
            + "        \"category\": \"Category\"\n"
            + "    }\n"
            + "]\n";

    public AIChatService(OpenAiChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String queryAiService(String userQuery) {
        //String modifiedQuery = getModifiedQuery(userQuery);
        ChatResponse modelResponse = chatModel.call(
                new Prompt(
                        String.format(PROMPT, userQuery) ,
                        OpenAiChatOptions.builder()
                                .withModel(MODEL_NAME)
                                .withTemperature(1.0f)
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
