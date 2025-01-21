package com.example.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.board.dao.BoardDao;
import com.example.board.dto.BoardDto;
import com.example.board.form.BoardForm;

@Service
public class BoardService {
    @Autowired
    private BoardDao boardDao;

    public List<BoardDto> getBoardList(BoardForm boardForm) throws Exception {

        return boardDao.getBoardList(boardForm);
    }
}
