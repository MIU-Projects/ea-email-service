package emailservice.service;

import emailservice.domain.Messages;
import emailservice.dto.RegistrationEventDTO;
import emailservice.repository.MessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@Transactional
@Slf4j
public class EmailServiceReceiver {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MessageRepository messageRepository;
    @Value("${spring.mail.username}")
    private String from;

    @KafkaListener(topics = {"eventReminder"})
    public void receiveMessage(@Payload String messaage) {
        log.info("inside receiveMessage method of EmailServiceReceiver");
        sendEmail(Long.valueOf(messaage));
        messageRepository.save(new Messages(Long.valueOf(messaage), LocalDateTime.now()));

    }

    public Mono<RegistrationEventDTO> getAllEventDetailsAndSendEmail(Long eventId) {
        log.info("inside getAllEventDetailsAndSendEmail method of EmailServiceReceiver");

        return WebClient.create("http://localhost:8080/").get().uri("/email-list/" + eventId)
                .retrieve().bodyToMono(RegistrationEventDTO.class);


    }

    public void sendEmail(Long eventId) {
        log.info("inside getAllEventDetailsAndSendEmail method of EmailServiceReceiver");
        getAllEventDetailsAndSendEmail(eventId).subscribe(s ->
                {
                    s.getRegistrationGroups().stream().forEach(rG -> {
                        rG.getStudents().stream().forEach(st -> {
                            SimpleMailMessage message = new SimpleMailMessage();
                            message.setFrom(from);
                            message.setTo(st.getEmail());
                            message.setSubject("Registration Event Closing Soon");
                            message.setText("" +
                                    "Dear " + st.getName() + "," +
                                    "Reg is Going to Close Soon");
                            javaMailSender.send(message);
                        });
                    });
                }

        );
    }
}
