package com.example.SMS.Controller;
import com.example.SMS.Models.ClassName;
import com.example.SMS.Models.Student;
import com.example.SMS.Service.ClassService;
import com.example.SMS.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    ClassService classService;

    @PostMapping("/addstudent")
    public ResponseEntity<String> addStudent(
            @RequestParam("id") int id,
            @RequestParam("name") String name,
            @RequestParam("gender") String gender,
            @RequestParam("dob") String dob,
            @RequestParam("className") String className,
            @RequestParam("email") String email,
            @RequestParam("phone") String phone,
            @RequestParam("percentage") double percentage,
            @RequestParam("file") MultipartFile file) {

        try {
            Student student = new Student();
            student.setId(id);
            student.setName(name);
            student.setGender(gender);
            student.setDob(dob);
            student.setClassName(className);
            student.setEmail(email);
            student.setPhone(phone);
            student.setPercentage(percentage);

            if (!file.isEmpty()) {
                String fileName = file.getOriginalFilename();
                Path path = Paths.get("uploads", fileName);
                Files.createDirectories(path.getParent());
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                student.setFileName(fileName);
            }

            // Save student directly here
            studentService.save(student);

            return ResponseEntity.ok("Student saved successfully with file!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error saving student");
        }
    }

    @GetMapping("/allstudents")
    public List<Student> allStudents(){
        return studentService.allStudents();
    }


    @GetMapping("/image/{filename:.+}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) throws MalformedURLException {
        Path path = Paths.get("uploads").resolve(filename);
        Resource resource = new UrlResource(path.toUri());
        if (!resource.exists() || !resource.isReadable()) {
            return ResponseEntity.notFound().build();
        }
        String contentType = "image/jpeg";
        try {
            contentType = Files.probeContentType(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }

    @PostMapping("/className")
    public String addClass(@RequestParam("className") String className,
                           @RequestParam("description") String description){
        classService.addClass(className,description);
        return "Class Created";
    }

    @GetMapping("/className")
    public List<ClassName> getClassNames(){
        return classService.getclassNames();
    }

    @GetMapping("/byClass")
    public List<Student> getStudentsByClassName(@RequestParam String className) {
        return studentService.getStudentsByClassName(className);
    }

    @GetMapping("/studentbyid")
    public Student getStudentById(@RequestParam int id){
        return studentService.getStudentById(id);
    }

    @PostMapping("/addfeedback")
    public void addFeedback(@RequestParam("name") String name,
                            @RequestParam("email") String email,
                            @RequestParam("subject") String subject,
                            @RequestParam("message") String message){
        studentService.addFeedback(name,email,subject,message);
    }

    @DeleteMapping("deletestudent/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id) {
        try {
            boolean deleted = studentService.deleteById(id); // custom method you define
            if (deleted) {
                return ResponseEntity.ok("Student deleted successfully!");
            } else {
                return ResponseEntity.status(404).body("Student not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error deleting student");
        }
    }
}
