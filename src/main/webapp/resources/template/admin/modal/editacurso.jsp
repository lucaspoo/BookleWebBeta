<!-- Import java-->
<%@ page contentType="text/html; charset=UTF-8" %>
<!-- Modal -->
<div class="modal fade" id="modalEditaCurso" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header text-center">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4>Editar Curso</h4>
      </div>
      <div class="modal-body">
        <form id="formlogin" action="editacurso" method="POST">
                <div class="panel-body">  
                    
                    <div class="form-group">
                            <input type="text" class="form-control" placeholder="${cod_curso}" name="cod_curso" readonly/>
                    </div>
                    
                    <div class="form-group">
                            <input type="text" class="form-control" placeholder="${curso.cod_curso}" name="nome_curso"/>
                    </div>  
                    
                     <div class="form-group">
                            <textarea class="form-control" rows="5" placeholder="Descrição sobre o Curso" name="descricao_curso"></textarea>
                    </div>
                </div>
            <div class="col-md-offset-9">
    
                   <button type="submit" class="btn btn-sm btn-success" >Editar</button>
 

                   <button type="button" class="btn btn-sm btn-default" data-dismiss="modal">Cancelar</button>

            </div>
        </form>
    </div>
    </div>
  </div>
</div>