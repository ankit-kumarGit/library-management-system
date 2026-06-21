package com.ankit.lms.controller;

import com.ankit.lms.model.Member;
import com.ankit.lms.model.enums.MemberStatus;
import com.ankit.lms.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    // DI
    private final MemberService service;

    // Constructor Injection
    public MemberController(MemberService service) {
        this.service = service;
    }

    // Create Member
    @PostMapping
    public Member saveMember(@Valid @RequestBody Member member) {
        return service.saveMember(member);
    }

    // Get All Members
    @GetMapping
    public List<Member> getAllMembers() {
        return service.getAllMembers();
    }

    // Get Member By Id
    @GetMapping("/{id}")
    public Member getMemberById(@PathVariable Long id) {
        return service.getMemberById(id);
    }

    // Update Member
    @PutMapping("/{id}")
    public Member updateMember(
            @PathVariable Long id,
            @Valid @RequestBody Member updatedMember) {

        return service.updateMember(id, updatedMember);
    }

    // Delete Member
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {

        service.deleteMember(id);

        return ResponseEntity.noContent().build();
    }

    // Change Member Status
    @PatchMapping("/{id}/status")
    public Member changeStatus(
            @PathVariable Long id,
            @RequestParam MemberStatus status) {

        return service.changeStatus(id, status);
    }
}