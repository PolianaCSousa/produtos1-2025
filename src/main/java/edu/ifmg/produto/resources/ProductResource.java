package edu.ifmg.produto.resources;


import edu.ifmg.produto.dtos.ProductDTO;
import edu.ifmg.produto.dtos.ProductListDTO;
import edu.ifmg.produto.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/product")
@Tag(name = "Product", description = "Controller/Resource for Products")
public class ProductResource {

    @Autowired
    private ProductService productService;

    //apenas adicionar o Pageable como parametro já exclui a necessidade de adicionar os @RequestParam porque ele já tem tudo pronto
    @GetMapping(produces = "application/json")
    @Operation(
            description = "Get all products",
            summary = "Get all products",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200")
            }
    )
    public ResponseEntity<Page<ProductDTO>> findAll(Pageable pageable) {
        Page<ProductDTO> products = productService.findAll(pageable);
        return ResponseEntity.ok(products); //é a mesma coisa que ok().body(products);
    }

    @GetMapping (value = "/{id}", produces = "application/json")
    @Operation(
            description = "Get a product",
            summary = "Get a product",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200"),
                    @ApiResponse(description = "Not found", responseCode = "404")
            }
    )
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        ProductDTO product = productService.findById(id);
        return ResponseEntity.ok(product); //é a mesma coisa que ok().body(products);
    }

    @PostMapping(produces = "application/json")
    @Operation(
            description = "Create a new product",
            summary = "Create a new product",
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201"),
                    @ApiResponse(description = "Bad Request", responseCode = "400"),
                    @ApiResponse(description = "Unauthorized", responseCode = "401"),
                    @ApiResponse(description = "Forbidden", responseCode = "403"),
            }
    )
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_OPERATOR')")
    public ResponseEntity<ProductDTO> insert(@Valid @RequestBody ProductDTO dto) { //o @Valid serve pra aplicar as validaçoes que adicionamos no DTO (@Size, @Positive, ect.)
        dto = productService.insert(dto);

        //essa uri que retornamos já está atendendo ao HATEOAS - eu insiro um produto e ja digo pro cara o que eu posso fazer com aquele produto, e nesse caço ele foi no cabeçalho da resposta
        URI uri = ServletUriComponentsBuilder.
                        fromCurrentRequest(). //pega o caminho da minha aplicação
                        path("/{id}").//ele adiciona o id na rota
                        buildAndExpand(dto.getId()).
                        toUri();

        return ResponseEntity.created(uri).body(dto);
    }


    @PutMapping(value = "/{id}", produces = "application/json")
    @Operation(
            description = "Update a product",
            summary = "Update a product",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200"),
                    @ApiResponse(description = "Bad Request", responseCode = "400"),
                    @ApiResponse(description = "Unauthorized", responseCode = "401"),
                    @ApiResponse(description = "Forbidden", responseCode = "403"),
                    @ApiResponse(description = "Not found", responseCode = "404")
            }
    )
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_OPERATOR')")
    public ResponseEntity<ProductDTO> update(@Valid @PathVariable Long id, @RequestBody ProductDTO dto) {
        dto = productService.update(id,dto);

        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value="/{id}")
    @Operation(
            description = "Delete a product",
            summary = "Delete a product",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200"),
                    @ApiResponse(description = "Bad Request", responseCode = "400"),
                    @ApiResponse(description = "Unauthorized", responseCode = "401"),
                    @ApiResponse(description = "Forbidden", responseCode = "403"),
                    @ApiResponse(description = "Not found", responseCode = "404")
            }
    )
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_OPERATOR')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);

        return ResponseEntity.noContent().build();
    }






    @GetMapping(value = "/paged",produces = "application/json")
    @Operation(
            description = "Get all products paged",
            summary = "Get all products paged",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200")
            }
    )
    public ResponseEntity<Page<ProductListDTO>> findAllPaged(Pageable pageable,
                                                             @RequestParam(value = "categoryId", defaultValue = "0") String categoryId,
                                                             @RequestParam(value = "name", defaultValue = "") String name)
    {
        Page<ProductListDTO> products = productService.findAllPaged(name, categoryId, pageable);
        return ResponseEntity.ok().body(products); //é a mesma coisa que ok().body(products);
    }



}
