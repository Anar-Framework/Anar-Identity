package af.gov.anar.identity_jwt.authentication.environment.impl;

import java.util.List;
import java.util.Optional;

import af.gov.anar.identity_jwt.authentication.environment.Environment;
import af.gov.anar.identity_jwt.authentication.environment.EnvironmentRepository;
import af.gov.anar.identity_jwt.authentication.environment.EnvironmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnvironmentServiceImpl implements EnvironmentService {

    @Autowired
    private EnvironmentRepository environmentRepository;

    @Override
    public Environment create(Environment environment) {
        return environmentRepository.save(environment);
    }

    @Override
    public Environment delete(Long id) {
    	Environment environment = findById(id);
        if(environment != null){
        	environmentRepository.delete(environment);
        }
        return environment;
    }

    @Override
    public List<Environment> findAll() {
        return environmentRepository.findAll();
    }

    @Override
    public Environment findById(Long id) {
    	Optional<Environment> optionalObj =  environmentRepository.findById(id);
		return optionalObj.get();
    }
    
    @Override
    public Environment findBySlug(String slug) {
    	return environmentRepository.findBySlug(slug);
    }

    @Override
    public Environment findBySecretKey(String secretKey) {
        return environmentRepository.findBySecretKey(secretKey);
    }

    @Override
    public boolean update(Long id, Environment environment) {
        if(id != null) {
        	environment.setId(id);
        }
        environmentRepository.save(environment);
        return true;
    }
}