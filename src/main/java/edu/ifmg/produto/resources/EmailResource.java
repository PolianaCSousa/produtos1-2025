package edu.ifmg.produto.resources;


import edu.ifmg.produto.dtos.EmailDTO;
import edu.ifmg.produto.services.EmailService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/email")
public class EmailResource {

    @Autowired
    private EmailService mailService;

    @PostMapping
    public ResponseEntity<Void> sendEmail(@Valid @RequestBody EmailDTO dto) {

        mailService.sendMail(dto);
        return ResponseEntity.noContent().build();
    }
}
