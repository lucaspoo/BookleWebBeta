package br.com.bookleweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.bookleweb.dao.CursoDAO;
import br.com.bookleweb.modelo.Curso;

/**
 * Classe responsável por implementar Servlets de controle que estão
 * relacionados a Curso.
 * 
 * @author Kélvin Santiago
 *
 */
@Controller
public class CursoController {

	private CursoDAO cursoDAO;

	/**
	 * Construtor da Classe aplicando injeção de dependência do Spring MVC
	 * 
	 * @param cursoDAO
	 */
	@Autowired
	public CursoController(CursoDAO cursoDAO) {
		this.cursoDAO = cursoDAO;
	}

	/**
	 * Método Servlet responsável por permitir acesso a página do gerenciador de
	 * curso.
	 * 
	 * @return ModelAndView - Lista com todos os cursos
	 */
	@RequestMapping(value = "/gerenciadorcurso")
	public ModelAndView executeCurso() {
		ModelAndView mv = new ModelAndView("/admin/gerenciadorcurso");
		mv.addObject("listacursos", cursoDAO.getTodosCursos());
		return mv;
	}

	/**
	 * Método Servlet responsável por controlar o adicionar novo curso entre o
	 * JSP e o DAO.
	 * 
	 * @param curso - Model curso
	 *            
	 * @return ModelAndView - Retorna uma mensagem de sucesso, ou erro.
	 */
	@RequestMapping(value = "/adicionacurso", method = RequestMethod.POST)
	public ModelAndView adicionaCurso(@ModelAttribute Curso curso) {
		ModelAndView mv = new ModelAndView("forward:/gerenciadorcurso");
		if (cursoDAO.adiciona(curso)) {
			String mensagem = "Opa! Curso adicionado com Sucesso!";
			mv.addObject("sucesso", mensagem);
		} else {
			String mensagem = "Ixi! Erro ao cadastrar curso!";
			mv.addObject("erro", mensagem);
		}
		return mv;
	}

	/**
	 * Método Servlet responsável por controlar o editar curso entre o JSP e o
	 * DAO.
	 * 
	 * @param curso - Model curso
	 * 
	 * @return ModelAndView - Retorna uma mensagem de sucesso, ou erro.
	 */
	@RequestMapping(value = "/editacurso", method = RequestMethod.POST)
	public ModelAndView editaCurso(@ModelAttribute Curso curso) {
		ModelAndView mv = new ModelAndView("forward:/gerenciadorcurso");
		if (cursoDAO.edita(curso)) {
			String mensagem = "Opa! Curso editado com Sucesso!";
			mv.addObject("sucesso", mensagem);
		} else {
			String mensagem = "Ixi! Erro ao editar curso!";
			mv.addObject("erro", mensagem);
		}
		return mv;
	}

	/**
	 * Método Servlet responsável por controlar o remover curso entre o JSP e o
	 * DAO.
	 * 
	 * @param curso - Model curso
	 *            
	 * @return ModelAndView - Somente redireciona.
	 */
	@RequestMapping(value = "/removecurso", method = RequestMethod.POST)
	public ModelAndView removeCurso(@ModelAttribute Curso curso) {
		ModelAndView mv = new ModelAndView("forward:/gerenciadorcurso");
		cursoDAO.remove(curso);
		return mv;
	}

	/**
	 * Método Servlet responsável por controlar o filtro (Pesquisa) da pagina
	 * gerenciador do curso.
	 * 
	 * @param curso - Model curso
	 *            
	 * @param opcaofiltro - Filtrar pelo Codigo ou Nome
	 *            
	 * @return ModelAndView - Retorna uma lista com os cursos de acordo com o
	 *         filtro.
	 */
	@RequestMapping(value = "/filtrocurso", method = RequestMethod.POST)
	public ModelAndView pesquisaCurso(@ModelAttribute Curso curso,
			@RequestParam String opcaofiltro) {
		ModelAndView mv = new ModelAndView("/admin/gerenciadorcurso");
		opcaofiltro = opcaofiltro.toLowerCase();
		if (opcaofiltro.equals("codigo")) {
			mv.addObject("listacursos", cursoDAO.procuraCursoPeloCodigo(curso));

		} else if (opcaofiltro.equals("nome")) {
			mv.addObject("listacursos", cursoDAO.procuraCursoPeloNome(curso));
		}
		return mv;
	}

}
