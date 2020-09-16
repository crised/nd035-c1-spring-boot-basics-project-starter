package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.service.CredentialService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/credential")
public class CredentialController {

    private CredentialService credentialService;

    public CredentialController(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @PostMapping
    public String receiveCredential(@ModelAttribute("credentialForm") Credential credentialForm,
                                    Authentication authentication,
                                    Model model) {
        System.out.println("on post receiveCredential");
        if (credentialForm.getCredentialid() == null)
            credentialService.addCredential(credentialForm, authentication.getName());
//        else credentialService.updateNote(noteForm);
        return "redirect:/home";
    }



}
