package com.SaeedAndEmre.CS393Project.Mappers;

import com.SaeedAndEmre.CS393Project.DTO.EquipmentDTO;
import com.SaeedAndEmre.CS393Project.DTO.MemberDTO;
import com.SaeedAndEmre.CS393Project.Entities.Equipment;
import com.SaeedAndEmre.CS393Project.Entities.Member;
import org.mapstruct.factory.Mappers;

import java.util.List;

public interface MemberMapper {
    MemberMapper INSTANCE= Mappers.getMapper(MemberMapper.class);

    List<MemberDTO> toMemberDTOS(List<Member> members);
    List<Member> toMembers(List<MemberDTO> memberDTOS);

    Member toMember(MemberDTO memberDTO);
    MemberDTO toMemberDTO(Member member);
}
