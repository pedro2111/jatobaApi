package br.com.jatoba.controller;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private JavaMailSender mailSender;

    @GetMapping
    public String sendEmail (){

        MimeMessage mail = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail);

            helper.setTo("pedrohbchaves@gmail.com");
            helper.setFrom("pedrohbchaves@gmail.com");
            helper.setSubject("Orçamento 55");

            helper.setText("<h2>Orçamento Cod: 50</h2>"+
            "<hr />"+
            "<h4>Informações do Pedido</h4>"+
            "<table>"+
            "<tbody>"+
            "<tr>"+
            "<td>Nome</td>"+
            "<td>Pedro Chaves</td>"+
            "</tr>"+
            "<tr>"+
            "<td>Telefone</td>"+
            "<td>9 9157-9675</td>"+
            "</tr>"+
            "<tr>"+
            "<td>Email</td>"+
            "<td>pedrohbchaves@gmail.com</td>"+
            "</tr>"+
            "<tr>"+
            "<td>Produto</td>"+
            "<td>Cod: 55 - Mesa de 5 lugares</td>"+
            "</tr>"+
            "</tbody>"+
            "</table>", true);

            mailSender.send(mail);

            return "email enviado com sucesso 2";
        } catch (Exception e) {
            e.printStackTrace();
            return "falha ao enviar o email";
        }
        
    }
    
}
