package com.demo.ecom.repository;

import com.demo.ecom.model.Cart;
import com.demo.ecom.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    List<Cart> findAllByUserOrderByCreatedDateDesc(User user);

    List<Cart> deleteByUser(User user);

    List<Cart> deleteByIdAndUser(Integer id, User user);

}

