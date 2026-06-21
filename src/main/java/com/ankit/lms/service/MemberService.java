package com.ankit.lms.service;

import com.ankit.lms.exception.ResourceNotFoundException;
import com.ankit.lms.model.Member;
import com.ankit.lms.model.enums.MemberStatus;
import com.ankit.lms.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    //DI
    private final MemberRepository repository;

    //constructor injection
    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    // Save Member
    public Member saveMember(Member member) {

        if (repository.findByEmail(member.getEmail()).isPresent()) {
            throw new RuntimeException("Member already registered with email: "
                    + member.getEmail());
        }

        member.setStatus(MemberStatus.ACTIVE);

        return repository.save(member);
    }

    // Get All Members
    public List<Member> getAllMembers() {
        return repository.findAll();
    }

    // Get Member By Id
    public Member getMemberById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Member not found with id: " + id));
    }

    // Update Member
    public Member updateMember(Long id, Member updatedMember) {

        Member member = getMemberById(id);

        member.setName(updatedMember.getName());
        member.setEmail(updatedMember.getEmail());
        member.setPhone(updatedMember.getPhone());

        return repository.save(member);
    }

    // Delete Member
    public void deleteMember(Long id) {

        getMemberById(id);

        repository.deleteById(id);
    }

    // Change Status
    public Member changeStatus(Long id, MemberStatus status) {

        Member member = getMemberById(id);

        member.setStatus(status);

        return repository.save(member);
    }
}
