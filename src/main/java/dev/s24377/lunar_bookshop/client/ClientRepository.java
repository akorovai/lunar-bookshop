package dev.s24377.lunar_bookshop.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByNameAndSurname(String john, String doe);

    @Transactional
    default <T extends Client> T convertClient(Client client, Class<T> targetClass) {
        deleteById(client.getId());

        T newClient;
        try {
            newClient = targetClass.getDeclaredConstructor(Client.class).newInstance(client);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return save(newClient);
    }
}
