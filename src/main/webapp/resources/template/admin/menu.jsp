<!-- Import java-->
<%@ page contentType="text/html; charset=UTF-8" %>
<!-- Import JSTL-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- Spring Famework -->
<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
    
<link href='http://fonts.googleapis.com/css?family=Roboto:100,300,400|Open+Sans' rel='stylesheet' type='text/css'>
   
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand logo" href="#">BOOKLEWEB</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="active"><a href="admin">Home 
            <span class="sr-only">(current)</span></a>
        </li>
        <li class="dropdown">
            <li><a href="pesquisa">Pesquisar</a></li>
        </li>
    <sec:authorize access="hasRole('ROLE_ADMIN')">      
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
              Gerenciar<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="gerenciadorcurso">Cursos</a></li>
            <li><a href="gerenciadordisciplina">Disciplinas</a></li>

            <li class="dropdown-submenu">
                <a tabindex="-1" href="gerenciadorlivro">Livros</a>
                <ul class="dropdown-menu">
                  <li><a href="relationshipdisciplinacurso">Vincular Livro a Disciplina</a></li>
                </ul>
              </li>
              
            <li><a href="gerenciadorusuario">Usuários</a></li>
          </ul>
        </li>
      </sec:authorize>    
 
     <sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_PROFESSOR')">    
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
              Relatórios<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
          	<sec:authorize access="hasRole('ROLE_ADMIN')"> 
            <li><a href="relatoriocurso" target="_blank">Cursos</a></li>
            <li><a href="relatoriodisciplina" target="_blank">Disciplinas</a></li>
             </sec:authorize> 
            <li><a href="relatoriolivro" target="_blank">Livros</a></li>
 			<sec:authorize access="hasRole('ROLE_ADMIN')"> 
            <li><a href="relatoriousuario" target="_blank">Usuários</a></li>
            </sec:authorize>  
          </ul>
        </li>
      </sec:authorize>   
      </ul>
      <ul class="nav navbar-nav navbar-right">
          <li class="dropdown"> 
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
			<div class="form-inline" id="avatar_menu">
			  <div class="form-group form-group-sm">
			    <img src="<c:url value="/resources/img/avatar.png" />" class="img-circle img-responsive" alt="Cinque Terre">
			  </div>
			  ${nome_usuario_sessao} 
                 <span class="caret"></span>
           </div>            
                          
                <!--Matrícula: <%= SecurityContextHolder.getContext().getAuthentication().getName() %>-->
           
          </a>
          <ul class="dropdown-menu" role="menu">
            <li><a data-toggle="modal" data-target="#modalNovaSenhaMenu" name="novasenha"> 
                Alterar Senha
                </a>
              </li>
    
            <li class="divider"></li>
            <li><a href="<c:url value="/j_spring_security_logout"/>">
                <span class="glyphicon glyphicon-off"></span> Logout
                </a>
              </li>
          </ul>
        </li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
<c:import url="/resources/template/admin/modal/usuario/alterasenha_menu.jsp"></c:import>    