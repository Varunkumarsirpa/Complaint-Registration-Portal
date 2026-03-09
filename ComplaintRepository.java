package Reg.example.complaintportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import Reg.example.complaintportal.entity.Complaint;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
}