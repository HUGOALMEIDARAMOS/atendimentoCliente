package com.cielo.ada.Cliente.controllers;

import com.cielo.ada.Cliente.models.FilaAtendimento;
import com.cielo.ada.Cliente.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/atendimento")
public class AtendimentoController {


    @Autowired
    FilaAtendimento filaAtendimento;

    @GetMapping("/listar")
    public List<UserModel> listarClientesNaFila() {
        return filaAtendimento.listarClientesNaFila();
    }

    @GetMapping("/atenderProximo")
    public ResponseEntity<UserModel> atenderProximoCliente() {
        UserModel clienteAtendido = filaAtendimento.proximoCliente();
        if (clienteAtendido != null) {
            return ResponseEntity.ok(clienteAtendido);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/listarAtendidos")
    public List<UserModel> listarClientesAtendidos() {
        return filaAtendimento.getAtendimentosConcluidos();
    }

}
