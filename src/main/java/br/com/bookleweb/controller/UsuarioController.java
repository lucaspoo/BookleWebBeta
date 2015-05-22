package br.com.bookleweb.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.bookleweb.dao.UsuarioDAO;
import br.com.bookleweb.modelo.Usuario;

@Controller
public class UsuarioController {

	private UsuarioDAO usuarioDAO;
	
	// Construtor da classe com injeção de dependencia do Spring 
		@Autowired
		public UsuarioController(UsuarioDAO usuarioDAO) {
			this.usuarioDAO = usuarioDAO;
		}
		
		@RequestMapping(value = "/gerenciadorusuario")
		public ModelAndView executeUsuario(){
			ModelAndView mv = new ModelAndView("/admin/gerenciadorusuario");
			mv.addObject("listausuarios",usuarioDAO.getListaTodosUsuarios());
			return mv;
		}

		@RequestMapping(value= "/adicionausuario", method= RequestMethod.POST)
		public ModelAndView adicionaUsuario(@ModelAttribute Usuario usuario){
			ModelAndView mv =  new ModelAndView("forward:/gerenciadorusuario");
			if(usuarioDAO.adiciona(usuario)){
				String mensagem = "Opa! Usuario adicionado com Sucesso!";
				mv.addObject("sucesso",mensagem);
			}else{
				String mensagem = "Ixi! Erro ao cadastrar usuario!";
				mv.addObject("erro",mensagem);	
			}
			return mv;
		}
		
		@RequestMapping(value= "/adicionausuariologin", method= RequestMethod.POST)
		public ModelAndView adicionaUsuarioTelaLogin(@ModelAttribute Usuario usuario){
			ModelAndView mv =  new ModelAndView("forward:/login");
			if(usuarioDAO.isRegistrado(usuario)){
				String mensagem = "Opa! Usuario já está cadastrado!";
				mv.addObject("erro",mensagem);
			}
			else{
				Integer quantidadeDigitos = Integer.toString(usuario.getMatricula()).length();
				if(quantidadeDigitos < 7){
					usuario.setPermissao("ROLE_ALUNO");
				}else{
					usuario.setPermissao("ROLE_PROFESSOR");
				}
				
				if(usuarioDAO.adiciona(usuario)){
					String mensagem = "Opa! Usuario adicionado com Sucesso! Faça Login!";
					mv.addObject("sucesso",mensagem);
				}else{
					String mensagem = "Ixi! Erro ao cadastrar usuario!";
					mv.addObject("erro",mensagem);	
				}
			}
			return mv;
		}

		
		@RequestMapping(value= "/editausuario", method= RequestMethod.POST)
		public ModelAndView editaUsuario(@ModelAttribute Usuario usuario){
			ModelAndView mv =  new ModelAndView("forward:/gerenciadorusuario");
			if(usuarioDAO.edita(usuario)){
				String mensagem = "Opa! Usuario editado com Sucesso!";
				mv.addObject("sucesso",mensagem);
			}else{
				String mensagem = "Ixi! Erro ao editar usuario!";
				mv.addObject("erro",mensagem);	
			}
			return mv;
		}
		
		@RequestMapping(value= "/alterasenha", method= RequestMethod.POST)
		public ModelAndView alteraSenha(@ModelAttribute Usuario usuario){
			ModelAndView mv =  new ModelAndView("forward:/gerenciadorusuario");
			if(usuarioDAO.alteraSenha(usuario)){
				String mensagem = "Opa! Senha alterada com sucesso!";
				mv.addObject("sucesso",mensagem);
			}else{
				String mensagem = "Ixi! Erro ao alterar senha do usuario!";
				mv.addObject("erro",mensagem);	
			}
			return mv;
		}
		
		@RequestMapping(value= "/removeusuario", method= RequestMethod.POST)
		public ModelAndView removeDisciplina(@ModelAttribute Usuario usuario){
			ModelAndView mv =  new ModelAndView("forward:/gerenciadorusuario");
			usuarioDAO.remove(usuario);
			return mv;
		}
		
		@RequestMapping(value= "/pesquisausuario", method= RequestMethod.POST)
		public ModelAndView pesquisaDisciplina(@ModelAttribute Usuario usuario, @RequestParam String opcaopesquisa){
			ModelAndView mv =  new ModelAndView("/admin/gerenciadorusuario");
			opcaopesquisa = opcaopesquisa.toLowerCase();
			if(opcaopesquisa.equals("matricula")){
				mv.addObject("listausuarios",usuarioDAO.pesquisaPelaMatricula(usuario));
			}
			else if(opcaopesquisa.equals("nome")){
				mv.addObject("listausuarios",usuarioDAO.pesquisaPeloNome(usuario));
			}
			return mv;
		}
		
	
	
}
