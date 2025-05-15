package edu.ifmg.produto.repository;


import edu.ifmg.produto.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //o metodo abaixo é um metodo de consulta ao banco que busco o email o padrao findByAlguma coisa é um mét0do para buscar Alguma coisa no banco
    User findByEmail(String email);
    User findByEmailAndPassword(String email, String password);

}
