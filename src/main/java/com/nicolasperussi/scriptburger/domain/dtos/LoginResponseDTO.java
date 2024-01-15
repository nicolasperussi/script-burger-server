package com.nicolasperussi.scriptburger.domain.dtos;

import com.nicolasperussi.scriptburger.domain.User;

public record LoginResponseDTO(String token, User user) {
}
