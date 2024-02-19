package com.springboot.demo.controller;

import com.springboot.demo.impl.AiServiceFactory;
import com.theokanning.openai.completion.chat.*;
import com.theokanning.openai.service.OpenAiService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class OpenAiController {

    @Resource
    private AiServiceFactory aiServiceFactory;

    @PostMapping("/testChat")
    public String testChat(@RequestBody Map<String,String> params) {

        try {
            OpenAiService service = aiServiceFactory.createService();
            final List<ChatMessage> messages = new ArrayList<>();
            final ChatMessage systemMessage = new ChatMessage(ChatMessageRole.USER.value(),
                    URLDecoder.decode(params.get("contents"),
                            "UTF-8"));

            messages.add(systemMessage);
            ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest
                    .builder()
                    .model("gpt-3.5-turbo")
                    .messages(messages)
                    .temperature(0.5)
//                .n(1)
//                .maxTokens(50)
//                .logitBias(new HashMap<>())
                    .build();


            ChatCompletionResult chatCompletionResult = service.createChatCompletion(chatCompletionRequest);
            List<ChatCompletionChoice> compList = chatCompletionResult.getChoices();
            StringBuilder sb = new StringBuilder();
            for (ChatCompletionChoice comp : compList) {
                sb.append(comp.getMessage().getContent());
            }
            return sb.toString();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
