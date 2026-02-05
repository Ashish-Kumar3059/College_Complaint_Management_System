package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.ComplaintResponseDTO;
import com.example.demo.exception.AccessDeniedException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Complaint;
import com.example.demo.model.User;
import com.example.demo.repository.ComplaintRepository;
import com.example.demo.repository.UserRepository;

import org.springframework.stereotype.Service;

@Service
public class ComplaintService {

    private final ComplaintRepository complaintRepository;
    private final UserRepository userRepository;

    public ComplaintService(ComplaintRepository complaintRepository,
                            UserRepository userRepository) {
        this.complaintRepository = complaintRepository;
        this.userRepository = userRepository;
    }

    // GET ALL
    public List<Complaint> getAllComplaints() {
        return complaintRepository.findAll();
    }

    // CREATE
    public Complaint createComplaint(Complaint complaint, Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        complaint.setUser(user);
        complaint.setStatus("OPEN");

        return complaintRepository.save(complaint);
    }

    // UPDATE STATUS (ADMIN / TECHNICIAN)
    public Complaint updateStatus(Long complaintId, Long userId, String status) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if ("STUDENT".equalsIgnoreCase(user.getRole())) {
            throw new AccessDeniedException("Only ADMIN or TECHNICIAN can update status");
        }

        Complaint complaint = complaintRepository.findById(complaintId)
                .orElseThrow(() -> new ResourceNotFoundException("Complaint not found"));

        complaint.setStatus(status);

        return complaintRepository.save(complaint);
    }

    // UPDATE FULL COMPLAINT
    public Complaint updateComplaint(Long id, Complaint updatedComplaint) {

        Complaint existing = complaintRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Complaint not found"));

        existing.setCategory(updatedComplaint.getCategory());
        existing.setDescription(updatedComplaint.getDescription());
        existing.setPriority(updatedComplaint.getPriority());
        existing.setStatus(updatedComplaint.getStatus());

        return complaintRepository.save(existing);
    }

    // DELETE
    public void deleteComplaint(Long id) {

        Complaint complaint = complaintRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Complaint not found"));

        complaintRepository.delete(complaint);
    }

    // MAP TO DTO
    public ComplaintResponseDTO mapToDTO(Complaint complaint) {

        ComplaintResponseDTO dto = new ComplaintResponseDTO();
        dto.setId(complaint.getId());
        dto.setCategory(complaint.getCategory());
        dto.setDescription(complaint.getDescription());
        dto.setPriority(complaint.getPriority());
        dto.setStatus(complaint.getStatus());

        return dto;
    }
}
