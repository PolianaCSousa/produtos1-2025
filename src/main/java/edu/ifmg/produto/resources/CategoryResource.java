package edu.ifmg.produto.resources;

import edu.ifmg.produto.entities.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController //diz que vai receber requisições
@RequestMapping(value = "/category") // category é um endpoint

public class CategoryResource {

    @GetMapping //quando eu usar o verbo Get ele chamará o metodo findALl
    public ResponseEntity<List<Category>> findAll(){

        List<Category> categories = new ArrayList<>();
        categories.add(new Category(1L, "eletronicos"));
        categories.add(new Category(2L, "jogos"));

        return ResponseEntity.ok().body(categories);
        //o responseentity é um objeto de resposta que monta a resposta toda pra mim
        //body() é o méto do que retorna o json
    }


}
