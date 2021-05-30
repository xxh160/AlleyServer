package com.edu.nju.alley.service.impl;

import com.edu.nju.alley.dao.InvitationCodeMapper;
import com.edu.nju.alley.dao.support.InvitationCodeDSS;
import com.edu.nju.alley.exceptions.NoSuchDataException;
import com.edu.nju.alley.po.InvitationCode;
import com.edu.nju.alley.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final InvitationCodeMapper invitationCodeMapper;

    @Autowired
    public AuthenticationServiceImpl(InvitationCodeMapper invitationCodeMapper) {
        this.invitationCodeMapper = invitationCodeMapper;
    }

    @Override
    public Integer isExist(String code) {
        Optional<InvitationCode> invitationCodeOptional = invitationCodeMapper
                .selectOne(c -> c.where(InvitationCodeDSS.code, isEqualTo(code)));
        return invitationCodeOptional.map(InvitationCode::getId).orElse(null);
    }

    @Override
    public void addUser(Integer userId, Integer codeId) {
        Optional<InvitationCode> invitationCodeOptional = invitationCodeMapper
                .selectByPrimaryKey(codeId);
        if (invitationCodeOptional.isEmpty()) throw new NoSuchDataException("错误的邀请码");
        InvitationCode invitationCode = invitationCodeOptional.get();
        invitationCode.setUserId(userId);
        invitationCodeMapper.updateByPrimaryKeySelective(invitationCode);
    }
}
