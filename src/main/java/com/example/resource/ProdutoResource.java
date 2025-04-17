package com.example.resource;

import com.example.dto.ProdutoDTO;
import com.example.model.Produto;
import com.example.repository.ProdutoRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.eclipse.microprofile.openapi.annotations.*;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;

@Path("/produtos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Produtos", description = "Operações para gerenciamento de produtos")
public class ProdutoResource {

    @Inject
    ProdutoRepository repository;
    
    @GET
    @Operation(summary = "Listar todos os produtos")
    @APIResponse(responseCode = "200", description = "Lista de produtos")
    public List<Produto> listar() {
        return repository.listAll();
    }
    
    @POST
    @Transactional
    @Operation(summary = "Criar novo produto")
    @APIResponse(responseCode = "201", description = "Produto criado")
    @APIResponse(responseCode = "400", description = "Dados inválidos")
    public Response criar(@Valid ProdutoDTO dto, @Context UriInfo uriInfo) {
        Produto produto = new Produto();
        produto.setNome(dto.nome);
        produto.setPreco(dto.preco);
        repository.persist(produto);
        
        UriBuilder builder = uriInfo.getAbsolutePathBuilder()
                                .path(Long.toString(produto.getId()));
        return Response.created(builder.build()).entity(produto).build();
    }
    
    @GET
    @Path("/{id}")
    @Operation(summary = "Buscar produto por ID")
    @APIResponse(responseCode = "200", description = "Produto encontrado")
    @APIResponse(responseCode = "404", description = "Produto não encontrado")
    public Produto buscar(@PathParam("id") Long id) {
        return repository.findByIdOptional(id)
                      .orElseThrow(() -> new NotFoundException("Produto não encontrado"));
    }
}
