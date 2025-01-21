package com.example.board.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.board.dto.BoardDto;
import com.example.board.form.BoardForm;

@Repository
public class BoardDao {
    @Resource(name = "sqlSession")
    private SqlSession sqlSession;
 
    private static final String NAMESPACE = "com.example.board.boardMapper";
 
    public List<BoardDto> getBoardList(BoardForm boardForm) throws Exception {
 
        return sqlSession.selectList(NAMESPACE + ".getBoardList");
    }
}
