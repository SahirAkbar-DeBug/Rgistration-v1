package com.api.services;

import com.api.entity.Registration;
import com.api.exception.ResourseNotFoundException;
import com.api.payload.RegistrationDto;
import com.api.repository.RegistrationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationService {
    private RegistrationRepository registrationRepository;
    private ModelMapper modelMapper;
    public RegistrationService(RegistrationRepository registrationRepository, ModelMapper modelMapper){
        this.registrationRepository=registrationRepository;
        this.modelMapper=modelMapper;
    }

    public List<RegistrationDto> getRegistrations(){
        List<Registration> all = registrationRepository.findAll();
        List<RegistrationDto> list = all.stream().map(registration -> modelMapper.map(registration, RegistrationDto.class)).toList();
        return list;
    }

    public RegistrationDto createRegistion(RegistrationDto registrationDto) {
        return modelMapper.map(registrationRepository.save(modelMapper.map(registrationDto, Registration.class)), RegistrationDto.class);
    }

    public void deleteRegistration(long id) {
        registrationRepository.findById(id).orElseThrow(
                ()-> new ResourseNotFoundException("No Record found for the deletion with the id "+id)
        );
        registrationRepository.deleteById(id);
    }

    public Registration updateRegistration(long id,Registration registration) {
        Registration r = registrationRepository.findById(id).orElseThrow(
                ()-> new ResourseNotFoundException("No Record found with id "+id+"  Please Register First")
        );
        r.setName(registration.getName());
        r.setEmail(registration.getEmail());
        r.setMobile(registration.getMobile());
        Registration save = registrationRepository.save(r);
        return save;
    }

    public RegistrationDto getRegistrationById(long id) {
        Registration registration = registrationRepository.findById(id).orElseThrow(
                () -> new ResourseNotFoundException("Record not found with the id " + id+ " "+System.currentTimeMillis())
        );
        return modelMapper.map(registration, RegistrationDto.class);
    }

}
