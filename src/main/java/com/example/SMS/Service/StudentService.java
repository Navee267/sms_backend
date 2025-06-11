package com.example.SMS.Service;

import com.example.SMS.Models.FeedBack;
import com.example.SMS.Models.Student;
import com.example.SMS.Repository.FeedBackRepo;
import com.example.SMS.Repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepo studentRepo;

    public void saveStudentWithFile(int id,String name, String gender, String dob,String className, String email,
                                    String phone, double percentage, MultipartFile file) {
        try {
            String folderPath = "uploads";
            File directory = new File(folderPath);
            if (!directory.exists()) directory.mkdirs();

            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

            Path path = Paths.get(folderPath, fileName);

            file.transferTo(path.toFile());

            Student student = new Student();
            student.setName(name);
            student.setGender(gender);
            student.setDob(dob);
            student.setClassName(className);
            student.setEmail(email);
            student.setPhone(phone);
            student.setPercentage(percentage);
            student.setFileName(fileName);

            studentRepo.save(student);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public List<Student> allStudents() {
        return studentRepo.findAll();
    }


    public void save(Student student) {
        studentRepo.save(student);
    }

    public List<Student> getStudentsByClassName(String className) {
        return studentRepo.findByClassName(className);
    }

    public Student getStudentById(int id) {
        return studentRepo.findById(id).orElse(null);
    }

    @Autowired
    FeedBackRepo feedBackRepo;

    public void addFeedback(String name, String email, String subject, String message) {
        FeedBack feedBack = new FeedBack();
        feedBack.setName(name);
        feedBack.setEmail(email);
        feedBack.setSubject(subject);
        feedBack.setMessage(message);

        feedBackRepo.save(feedBack);
    }

    public boolean deleteById(int id) {
        if(studentRepo.existsById(id)){
            studentRepo.deleteById(id);
            return true;
        }
        else{
            return false;
        }
    }
}
