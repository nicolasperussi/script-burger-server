package com.nicolasperussi.scriptburger.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.nicolasperussi.scriptburger.domain.Courier;
import com.nicolasperussi.scriptburger.domain.User;
import com.nicolasperussi.scriptburger.exceptions.ResourceNotFoundException;
import com.nicolasperussi.scriptburger.repositories.UserRepository;

@Service
public class UserService {
  @Autowired
  private UserRepository repository;

  public List<User> findAll() {
    return repository.findAll();
  }

  public List<Courier> findAllCouriers() {
    return repository.findAllCouriers();
  }

  public User findById(@NonNull Long id) {
    return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Couldn't find user with id " + id));
  }

  public User create(@NonNull User user) {
    return repository.save(user);
  }

  public void delete(@NonNull Long id) {
    repository.deleteById(id);
  }

}
