package priv.ns.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import priv.ns.ecommerce.entity.EcommerceUser;

public interface UserDao extends JpaRepository<EcommerceUser,Long> {
    EcommerceUser findByUsername(String username);
    EcommerceUser findByUsernameAndPassword(String username, String password);
}
