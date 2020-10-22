package br.com.jatoba.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import br.com.jatoba.modelo.Email;
import br.com.jatoba.modelo.Orcamento;
import br.com.jatoba.modelo.Produto;

@Service
public class EmailService {

    @Autowired
    JavaMailSender mailSender;

    Logger logger = LoggerFactory.getLogger(EmailService.class);

    public void sendEmail(Orcamento orcamento, Produto produto){

        MimeMessage mail = mailSender.createMimeMessage();
        Email email = new Email(orcamento,produto);

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail);

            helper.setTo("pedrohbchaves@gmail.com");
            //helper.setFrom(orcamento.getEmail());
            helper.setSubject(orcamento.getAssunto());

            if(produto.getId() == null){
                helper.setText(email.montaEmailSemProduto(), true);

            }else{
                helper.setText(email.montaEmail(), true);
            }
            
            mailSender.send(mail);

             logger.info("Email enviado com Sucesso");

        } catch (Exception e) {
            e.printStackTrace();

            logger.error("Erro ao enviar email", e);
        }

    }
    
}
