package edu.ifmg.produto.resources;

import edu.ifmg.produto.dtos.CategoryDTO;
import edu.ifmg.produto.entities.Category;
import edu.ifmg.produto.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //diz que vai receber requisições
@RequestMapping(value = "/category") // category é um endpoint

public class CategoryResource {

    @Autowired
    private CategoryService categoryService;

    @GetMapping //quando eu usar o verbo Get em alguma requisição ele chamará o metodo findALl
    public ResponseEntity<List<CategoryDTO>> findAll(){

        //antes estávamos inserindo os dados manualmente, e agora ele chama o metodo findAll que busca os dados no banco (que ainda não existe) e popula o objeto categories
        List<CategoryDTO> categories = categoryService.findAll();

        //o responseentity é um objeto de resposta que monta a resposta toda pra mim
        //body() é o méto do que retorna o json
        return ResponseEntity.ok().body(categories);

    }

    //a rota que acessa esse méto do é o category/id
    @GetMapping (value = "/{id}")//quando eu usar o verbo Get em alguma requisição ele chamará o metodo findALl
    public ResponseEntity<CategoryDTO>
        findById(@PathVariable Long id){ //passa o id que vem da requisição aqui, o PathVariable diz que receberá uma variavel de uma requisição

        //antes estávamos inserindo os dados manualmente, e agora ele chama o metodo findAll que busca os dados no banco (que ainda não existe) e popula o objeto categories
        CategoryDTO category = categoryService.findById(id);

        //o responseentity é um objeto de resposta que monta a resposta toda pra mim
        //body() é o méto do que retorna o json
        return ResponseEntity.ok().body(category);

    }


}
