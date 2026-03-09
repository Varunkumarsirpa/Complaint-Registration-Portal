package Reg.example.complaintportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import Reg.example.complaintportal.entity.Complaint;
import Reg.example.complaintportal.repository.ComplaintRepository;
import Reg.example.complaintportal.service.EmailService;

@RestController
@RequestMapping("/complaints")
public class ComplaintController {

    @Autowired
    private ComplaintRepository repository;

    @Autowired
    private EmailService emailService;

    // Register complaint
    @PostMapping
    public Complaint registerComplaint(@RequestBody Complaint complaint) {

        Complaint saved = repository.save(complaint);
        emailService.sendEmail(complaint.getEmail());

        return saved;
    }

    // Get all complaints (for admin dashboard)
    @GetMapping
    public List<Complaint> getAllComplaints() {
        return repository.findAll();
    }

    // Update complaint status
    @PutMapping("/{id}")
    public Complaint updateStatus(@PathVariable Long id, @RequestBody Complaint updated) {

        Complaint complaint = repository.findById(id).orElseThrow();

        complaint.setStatus(updated.getStatus());

        return repository.save(complaint);
    }
}