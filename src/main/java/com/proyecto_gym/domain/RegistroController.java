package com.proyecto_gym.domain;

import com.proyecto_gym.service.RegistroService;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@Slf4j
@RequestMapping("/registro")
public class RegistroController {


    @Autowired
    private RegistroService registroService;

    @GetMapping("/nuevo")
    public String nuevo(Model model, User usuario) {
        return "/registro/nuevo";
    }

    @GetMapping("/recordar")
    public String recordar(Model model, User usuario) {
        return "/registro/recordar";
    }

    @PostMapping("/crearUsuario")
    public String crearUsuario(Model model, User usuario)
            throws MessagingException {
        model = registroService.crearUsuario(model, usuario);
        return "/registro/salida";
    }

    @GetMapping("/activacion/{usuario}/{id}")
    public String activar(
            Model model,
            @PathVariable(value = "usuario") String usuario,
            @PathVariable(value = "id") String id) {
        model = registroService.activar(model, usuario, id);
        if (model.containsAttribute("usuario")) {
            return "/registro/activa";
        } else {
            return "/registro/salida";
        }
    }

    @PostMapping("/activar")
    public String activar(User usuario) {
        registroService.activar(usuario);
        return "redirect:/";
    }

    @PostMapping("/recordarUsuario")
    public String recordarUsuario(Model model, User usuario)
            throws MessagingException {
        model = registroService.recordarUsuario(model, usuario);
        return "/registro/salida";
    }
}

