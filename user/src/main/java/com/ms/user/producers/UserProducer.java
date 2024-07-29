package com.ms.user.producers;

import com.ms.user.dtos.EmailDTO;
import com.ms.user.models.UserModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserProducer {

    @Value("${broker.queue.email.name}")
    private String routingKey;

    final RabbitTemplate rabbitTemplate;

    public UserProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void pusblishMessage(UserModel userModel){
        var emailDTO = new EmailDTO();
        emailDTO.setUserId(userModel.getUserId());
        emailDTO.setEmailTo(userModel.getEmail());
        emailDTO.setSubject("Cadastro de pessoa realizado com sucesso");
        emailDTO.setText(userModel.getName() + ", seja bom vindo(a)!\nAgradecemos seu cadastro");

        rabbitTemplate.convertAndSend("", routingKey, emailDTO);
    }

}
