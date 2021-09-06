package com.zup.mercado.config.security;
import com.zup.mercado.config.security.usuario.NovoUsuarioResponse;
import com.zup.mercado.config.security.usuario.Usuario;
import com.zup.mercado.config.security.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SenhaLimpa {

    @Autowired
    UsuarioRepository usuarioRepository;


    public NovoUsuarioResponse salvarUsuario (Usuario usuario) {
        usuario.setSenha(hashSenha(usuario.getSenha()));
        return new NovoUsuarioResponse(usuarioRepository.save(usuario));
    }

    public String hashSenha(String senhaAberta) {
        BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
        String senhaCriptografada;

        senhaCriptografada = bCrypt.encode(senhaAberta);

        return senhaCriptografada;
    }

}
