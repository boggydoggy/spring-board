package com.example.board.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.board.common.PagingUtil;
import com.example.board.common.ResultUtil;
import com.example.board.dao.BoardDao;
import com.example.board.dto.BoardDto;
import com.example.board.dto.CommonDto;
import com.example.board.form.BoardForm;
import com.example.board.form.CommonForm;

@Service
public class BoardService {
    @Autowired
    private BoardDao boardDao;

    /* 게시판 - 목록 조회 */
    public ResultUtil getBoardList(BoardForm boardForm) throws Exception {
        ResultUtil resultUtil = new ResultUtil();

        CommonDto commonDto = new CommonDto();

        int totalCount = boardDao.getBoardCnt(boardForm);
        if (totalCount != 0) {
            CommonForm commonForm = new CommonForm();
            commonForm.setFunction_name(boardForm.getFunction_name());
            commonForm.setCurrent_page_no(boardForm.getCurrent_page_no());
            commonForm.setCount_per_page(10);
            commonForm.setCount_per_list(10);
            commonForm.setTotal_list_count(totalCount);
            commonDto = PagingUtil.setPageUtil(commonForm);
        }
        
        boardForm.setLimit(commonDto.getLimit());
        boardForm.setOffset(commonDto.getOffset());
        
        List<BoardDto> list = boardDao.getBoardList(boardForm);
        
        HashMap<String, Object> resultMap = new HashMap<>();
        
        resultMap.put("list", list);
        resultMap.put("totalCount", totalCount);
        resultMap.put("pagination", commonDto.getPagination());
        
        resultUtil.setData(resultMap);
        resultUtil.setState("SUCCESS");
        
        return resultUtil;
    }

    /* 게시판 - 상세 조회 */
    public BoardDto getBoardDetail(BoardForm boardForm) throws Exception {
        BoardDto boardDto = new BoardDto();

        String searchType = boardForm.getSearch_type();

        if ("S".equals(searchType)) {
            int updateCnt = boardDao.updateBoardHits(boardForm);

            if (updateCnt > 0) {
                boardDto = boardDao.getBoardDetail(boardForm);
            }
        } else {
            boardDto = boardDao.getBoardDetail(boardForm);
        }
        return boardDto;
    }

    /* 게시판 - 등록 */
    public BoardDto insertBoard(BoardForm boardForm) throws Exception {

        BoardDto boardDto = new BoardDto();

        int insertCnt = 0;

        insertCnt = boardDao.insertBoard(boardForm);
//        insertCnt = boardDao.insertBoardFail(boardForm);

        if (insertCnt > 0) {
            boardDto.setResult("SUCCESS");
        } else {
            boardDto.setResult("FAIL");
        }

        return boardDto;
    }

    /* 게시판 - 삭제 */
    public BoardDto deleteBoard(BoardForm boardForm) throws Exception {

        BoardDto boardDto = new BoardDto();

        int deleteCnt = boardDao.deleteBoard(boardForm);

        if (deleteCnt > 0) {
            boardDto.setResult("SUCCESS");
        } else {
            boardDto.setResult("FAIL");
        }

        return boardDto;
    }

    /* 게시판 - 수정 */
    public BoardDto updateBoard(BoardForm boardForm) throws Exception {

        BoardDto boardDto = new BoardDto();

        int updateCnt = boardDao.updateBoard(boardForm);

        if (updateCnt > 0) {
            boardDto.setResult("SUCCESS");
        } else {
            boardDto.setResult("FAIL");
        }

        return boardDto;
    }
}
