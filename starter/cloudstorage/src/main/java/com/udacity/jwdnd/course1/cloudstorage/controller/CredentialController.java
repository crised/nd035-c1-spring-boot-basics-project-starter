package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.service.CredentialService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        String username = authentication.getName();
        if (credentialForm.getCredentialid() == null)
            credentialService.addCredential(credentialForm, username);
        else credentialService.editCredential(credentialForm, username);
        return "redirect:/home";
    }

    @GetMapping(path = "/delete")
    public String delete(@RequestParam("credentialid") String credentialid) {
        credentialService.removeCredential(Integer.valueOf(credentialid));
        return "redirect:/home";
    }




}
