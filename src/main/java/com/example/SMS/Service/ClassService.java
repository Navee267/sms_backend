package com.example.SMS.Service;

import com.example.SMS.Models.ClassName;
import com.example.SMS.Repository.ClassRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassService {

    @Autowired
    ClassRepo classRepo;

    public void addClass(String className, String description) {
        ClassName className1 = new ClassName();
        className1.setClassName(className);
        className1.setDescription(description);
        classRepo.save(className1);
    }

    public List<ClassName> getclassNames() {
        return classRepo.findAll();
    }
}
