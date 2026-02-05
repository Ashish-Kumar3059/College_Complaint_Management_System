package com.example.demo.controller;

import java.util.List;

import com.example.demo.dto.ComplaintRequestDTO;
import com.example.demo.dto.ComplaintResponseDTO;
import com.example.demo.model.Complaint;
import com.example.demo.service.ComplaintService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/complaints")
public class ComplaintController {

    private final ComplaintService complaintService;

    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    // GET ALL
    @GetMapping
    public List<Complaint> getAllComplaints() {
        return complaintService.getAllComplaints();
    }

    // CREATE
    @PostMapping
    public ComplaintResponseDTO createComplaint(
            @RequestBody ComplaintRequestDTO req,
            @RequestParam Long userId) {

        Complaint complaint = new Complaint();
        complaint.setCategory(req.getCategory());
        complaint.setDescription(req.getDescription());
        complaint.setPriority(req.getPriority());

        Complaint saved = complaintService.createComplaint(complaint, userId);
        return complaintService.mapToDTO(saved);
    }

    // UPDATE STATUS
    @PutMapping("/{id}/status")
    public ComplaintResponseDTO updateStatus(
            @PathVariable Long id,
            @RequestParam Long userId,
            @RequestParam String status) {

        Complaint updated = complaintService.updateStatus(id, userId, status);
        return complaintService.mapToDTO(updated);
    }

    // UPDATE FULL
    @PutMapping("/{id}")
    public Complaint update(@PathVariable Long id,
                            @RequestBody Complaint complaint) {
        return complaintService.updateComplaint(id, complaint);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        complaintService.deleteComplaint(id);
    }
}
