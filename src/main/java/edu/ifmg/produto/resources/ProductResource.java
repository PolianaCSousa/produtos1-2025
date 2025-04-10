package edu.ifmg.produto.resources;


import edu.ifmg.produto.dtos.ProductDTO;
import edu.ifmg.produto.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/product")
public class ProductResource {

    @Autowired
    private ProductService productService;

    //apenas adicionar o Pageable como parametro já exclui a necessidade de adicionar os @RequestParam porque ele já tem tudo pronto
    @GetMapping
    public ResponseEntity<Page<ProductDTO>> findAll(Pageable pageable) {
        Page<ProductDTO> products = productService.findAll(pageable);
        return ResponseEntity.ok(products); //é a mesma coisa que ok().body(products);
    }

    @GetMapping (value = "/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        ProductDTO product = productService.findById(id);
        return ResponseEntity.ok(product); //é a mesma coisa que ok().body(products);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> insert(@RequestBody ProductDTO dto) {
        dto = productService.insert(dto);

        URI uri = ServletUriComponentsBuilder.
                        fromCurrentRequest(). //pega o caminho da minha aplicação
                        path("/{id}").//ele adiciona o id na rota
                        buildAndExpand(dto.getId()).
                        toUri();

        return ResponseEntity.created(uri).body(dto);
    }
}
