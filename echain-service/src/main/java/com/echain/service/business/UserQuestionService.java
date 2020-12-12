package com.echain.service.business;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.echain.dao.business.user.UserQuestionDao;
import com.echain.domain.business.user.UserQuestion;

//@Slf4j
@Service
public class UserQuestionService {
	@Resource
	private UserQuestionDao userQuestionDao;
	
	//@Autowired
	//private RedisUtil redisUtil;
	
	/**
	 * 插入
	 * @param enty
	 * @return
	 */
	public Long add(UserQuestion enty) {
		int nCount = userQuestionDao.insert(enty);
		if (nCount == 1) {
			return enty.getId();
		}
		return null;
	}
	
	/**
	 * 插入
	 * @param enty
	 * @return
	 */
	@Transactional
	public int save(Long userId,Long[] questionId, String[] answer) {
		//先删除再保存
		this.deleteByUserId(userId);
		int nCount = 0;
		for(int i=0;i<questionId.length;i++) {
			UserQuestion enty = new UserQuestion();
			enty.setUserId(userId);
			enty.setQuestionId(questionId[i]);
			enty.setAnswer(answer[i]);
			nCount += userQuestionDao.insert(enty);
		}
		return nCount;
	}
	
	/**
	 * 查询总记录数
	 * @param enty
	 * @return
	 */
	public Integer selectCount(UserQuestion enty) {
		return userQuestionDao.selectCount(toWrapper(enty));
	}
	
	/**
	 * 根据实体查询对象信息
	 * @param enty
	 * @return
	 */
	public List<UserQuestion> select(UserQuestion enty) {
		return userQuestionDao.selectList(limitFiled(toWrapper(enty)));
	}
	
	/**
	 * 更新
	 * 
	 * @param enty
	 * @return
	 */
	public int update(UserQuestion enty) {
		int nRst = userQuestionDao.updateById(enty);
		return nRst;
	}
	
	/**
	 * 根据ID获取
	 * 
	 * @param id
	 * @return
	 */
	public UserQuestion get(Long id) {
		return userQuestionDao.selectById(id);
	}
	
	/**
	 * 根据userId删除
	 * 
	 * @param userId
	 * @return
	 */
	public int deleteByUserId(Long userId) {
		UserQuestion question = new UserQuestion();
		question.setUserId(userId);
		List<UserQuestion> list = userQuestionDao.selectList(limitFiled(toWrapper(question)));
		List<Integer> map = new ArrayList<Integer>();
		list.forEach(m -> {
			int res = userQuestionDao.deleteById(m.getId());
			map.add(res);
		});
		return map.size();
	}
	
	/**
	 * 分页查询
	 * 
	 * @param page
	 * @param enty
	 * @return
	 */
	public IPage<UserQuestion> select(IPage<UserQuestion> page, UserQuestion enty) {
		LambdaQueryWrapper<UserQuestion> query = limitFiled(toWrapper(enty));
		return userQuestionDao.selectPage(page, query);
	}
	
	private LambdaQueryWrapper<UserQuestion> limitFiled(LambdaQueryWrapper<UserQuestion> query) {
		return query.select(UserQuestion::getId,UserQuestion::getUserId,UserQuestion::getQuestionId,UserQuestion::getAnswer,UserQuestion::getCreateTime,UserQuestion::getCreatePin,UserQuestion::getUpdatePin,UserQuestion::getUpdatePin);
	}
	
	/**
	 * 查询条件
	 * @param enty
	 * @return
	 */
	private LambdaQueryWrapper<UserQuestion> toWrapper(UserQuestion enty) {
		return new QueryWrapper<UserQuestion>().lambda()
			// id
			.eq(enty.getId() != null, UserQuestion::getId, enty.getId())
			// userId
			.eq(enty.getUserId() != null, UserQuestion::getUserId, enty.getUserId())
			// questionId
			.eq(enty.getQuestionId() != null, UserQuestion::getQuestionId, enty.getQuestionId())
			// answer
			.eq(StringUtils.isNotBlank(enty.getAnswer()), UserQuestion::getAnswer, enty.getAnswer())
			//ID 倒叙
			.orderByDesc(UserQuestion::getId);
	}
	
}
