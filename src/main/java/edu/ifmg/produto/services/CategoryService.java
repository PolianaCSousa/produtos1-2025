package edu.ifmg.produto.services;

import edu.ifmg.produto.dtos.CategoryDTO;
import edu.ifmg.produto.entities.Category;
import edu.ifmg.produto.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired //isso é pra injeção de dependencia, e o Spring vai gerenciar o objeto categoryRepository que criei
    private CategoryRepository categoryRepository;

//sempre vamos buscar os dados do repository e converter para DTO
    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll() {
       List<Category> list = categoryRepository.findAll();
        return list.stream().
                map(categoria-> //essa é uma função lambda (semelhante a arrow function)
                        new CategoryDTO(categoria)).
                collect(Collectors.toList());

    }

    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id){
        Optional<Category> obj =
                categoryRepository.findById(id);

        Category category = obj.get();
        return new CategoryDTO(category);
    }

}
