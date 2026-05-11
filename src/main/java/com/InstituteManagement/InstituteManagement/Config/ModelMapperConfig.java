package com.InstituteManagement.InstituteManagement.Config;

import com.InstituteManagement.InstituteManagement.User.DTO.UserResponseDTO;
import com.InstituteManagement.InstituteManagement.User.Entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
