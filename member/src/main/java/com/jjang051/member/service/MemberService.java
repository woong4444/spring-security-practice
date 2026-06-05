package com.jjang051.member.service;

import com.jjang051.member.dto.LoginDto;
import com.jjang051.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnailator;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final JdbcTemplate jdbcTemplate;

    //이름을 바꿔야 한다.
    //file이 올라오면 특정 장소(폴더)에 저장한다. 이때 서버내에 두는 건 비추
    public void signup(MemberDto memberDto, MultipartFile profile) {
        String savedFileName = null;
        String thumbnailFilename=null;
        System.out.println("111");
        try {
            if (!profile.isEmpty()) {
                Path uploadPath = Paths.get("C:\\upload");


                //혹시 폴더가 없으면 만들어라...
                Files.createDirectories(uploadPath);
                //원본 파일 이름
                String originalFilename = profile.getOriginalFilename(); //profile.jpg
                //원본 파일이름을 그래도 저장하면 기존 이미지 삭제되므로 이름 바꿔서 저장
                savedFileName = UUID.randomUUID()+"_"+originalFilename;
                //파일의 경로 문제 해결
                Path savedPath = uploadPath.resolve(savedFileName);
                // 파일 카피해서 넣음 (속도가 제일 빠름)
                Files.copy(profile.getInputStream(), savedPath, StandardCopyOption.REPLACE_EXISTING);

                thumbnailFilename = "thumb_"+UUID.randomUUID()+"_"+originalFilename;
                Path thumbnailPath = uploadPath.resolve(thumbnailFilename);
                Thumbnails.of(savedPath.toFile())
                        .size(200,200)
                        .keepAspectRatio(true)
                        .toFile(thumbnailPath.toFile());


            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String sql = """
                INSERT INTO MEMBER VALUES
                (member_seq.nextval,?,?,
                ?,?,?,?,?,?,?,?,sysdate)
                """;
        jdbcTemplate.update(sql,
                memberDto.getUserId(),
                memberDto.getUserName(),
                memberDto.getUserPw(),
                memberDto.getEmail(),
                memberDto.getPhone(),
                memberDto.getAddress(),
                memberDto.getZipcode(),
                memberDto.getDetailAddress(),
                savedFileName,
                thumbnailFilename
        );
    }

    public boolean existsUserId(String userId) {
        String sql = "SELECT count(*) FROM MEMBER WHERE USER_ID= ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, userId);
        return count != null && count > 0;
    }

    public boolean existsEmail(String email) {
        String sql = "SELECT count(*) FROM MEMBER WHERE EMAIL = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, email);
        return count != null && count > 0;
    }

    public boolean loginCheck(LoginDto loginDto) {
        String sql = """
                   SELECT count(*) FROM MEMBER 
                       WHERE user_id=? AND user_pw=?
                """;
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class,
                loginDto.getUserId(), loginDto.getUserPw());
        return count != null && count > 0;
    }

    public MemberDto loginCheckDto(LoginDto loginDto) {
        String sql = """
                   SELECT user_id,user_name,profile FROM MEMBER 
                       WHERE user_id=? AND user_pw=?
                """;
        try {
            return jdbcTemplate.queryForObject(sql, (rs, rownum) ->
                            MemberDto.builder()
                                    .userId(rs.getNString("user_id"))
                                    .userName(rs.getString("user_name"))
                                    .profile(rs.getString("profile"))
                                    .build(),
                    loginDto.getUserId(), loginDto.getUserPw());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public boolean idCheck(String userId) {
        String sql = """
                   SELECT count(*) FROM MEMBER 
                       WHERE user_id = ?
                """;
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, userId);
        return count != null && count > 0;
    }

    public MemberDto getMemberInfo(String userId) {
        String sql = """
                    SELECT user_id,user_name,zipcode,address, detail_address,regdate 
                    FROM MEMBER WHERE user_id=?
                """;
        //RowMapper
        return jdbcTemplate.queryForObject(sql, (rs, rownum) ->
                        MemberDto.builder()
                                .userId(rs.getNString("user_id"))
                                .userName(rs.getString("user_name"))
                                .zipcode(rs.getInt("zipcode"))
                                .address(rs.getString("address"))
                                .detailAddress(rs.getString("detail_address"))
                                .regDate(rs.getTimestamp("regdate").toLocalDateTime())
                                .build()
                , userId);
    }
}
