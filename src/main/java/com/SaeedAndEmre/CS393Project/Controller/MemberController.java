package com.SaeedAndEmre.CS393Project.Controller;

import com.SaeedAndEmre.CS393Project.DTO.CreateCarDTO;
import com.SaeedAndEmre.CS393Project.DTO.MemberDTO;
import com.SaeedAndEmre.CS393Project.DTO.ReservationInfoDTO;
import com.SaeedAndEmre.CS393Project.Entities.Member;
import com.SaeedAndEmre.CS393Project.Mappers.MemberMapper;
import com.SaeedAndEmre.CS393Project.Services.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController

public class MemberController {
    @Autowired
    MemberService memberService;


    @Operation(summary = "Create a new member",
            description = "Insert member details below")
    @PostMapping(value = "/Members/new")
    public ResponseEntity<MemberDTO> save(MemberDTO memberDTO){
        try{
            Member member=MemberMapper.INSTANCE.toMember(memberDTO);
            MemberDTO result=MemberMapper.INSTANCE.toMemberDTO(memberService.save(member));
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }
    @Operation(summary = "Find a Member with his/her id ",
            description = "Insert Member id below")
    @GetMapping(value = "/Members/{id}")
    public ResponseEntity<MemberDTO> findById(@PathVariable (value="id") Long id){
        try{
            Member member=memberService.findById(id);
            MemberDTO result=MemberMapper.INSTANCE.toMemberDTO(member);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (EmptyResultDataAccessException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "List All members ")
    @GetMapping(value = "/Members")
    public ResponseEntity<List<MemberDTO>> findAll(){
        try{
            return new ResponseEntity<>(MemberMapper.INSTANCE.toMemberDTOS(memberService.findAll()), HttpStatus.OK);

        }catch (EmptyResultDataAccessException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
