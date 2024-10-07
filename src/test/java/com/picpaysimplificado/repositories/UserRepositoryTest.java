package com.picpaysimplificado.repositories;

import com.picpaysimplificado.domain.dtos.UserDTO;
import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.domain.user.UserType;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Optional;


@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Should get User successfully from DB")
    void findUserByDocumentCase1() {
        String document = "9999999999";
        UserDTO data = new UserDTO("Rafael", "Teste", document, new BigDecimal(10), "test@gmail.com","44444", UserType.COMMON);
        this.creatUser(data);

        Optional<User> result = this.userRepository.findUserByDocument(document);
        assertThat(result.isPresent());
    }

    @Test
    @DisplayName("Should not get User successfully from DB")
    void findUserByDocumentCase2() {
        String document = "9999999999";

        Optional<User> result = this.userRepository.findUserByDocument(document);

        assertThat(result.isEmpty());
    }

    private User creatUser(UserDTO data){
        User newUser = new User();
        this.entityManager.persist(newUser);
        return newUser;
    }

}