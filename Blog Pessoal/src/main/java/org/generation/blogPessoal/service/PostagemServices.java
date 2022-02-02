package org.generation.blogPessoal.service;

import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.repository.PostagemRepository;
import org.generation.blogPessoal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PostagemServices {

    private @Autowired PostagemRepository repositoryP;

    private static String[] getUserInToken(String token){
        byte[] decode = Base64.decodeBase64(token);
        String decodeString = new String(decode);
        return decodeString.split(":"); 
    }

    public ResponseEntity deleteByIdToken(long id, String token) {
        
         String[] result = getUserInToken(token);

        return repositoryP.findById(id).map(resp -> {
            if (resp.getUsuario().getUsuario().equals(result[0])) {
                repositoryP.deleteById(id);
                return ResponseEntity.status(200).build();
            } else {
                return ResponseEntity.status(401).build();
            }
            
        }).orElse(ResponseEntity.status(404).build());
    }



}
