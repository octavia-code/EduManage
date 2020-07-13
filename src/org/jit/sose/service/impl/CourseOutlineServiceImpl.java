package org.jit.sose.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.jit.sose.entity.AssessItem;
import org.jit.sose.entity.Assessment;
import org.jit.sose.entity.CourseOutline;
import org.jit.sose.entity.vo.AssessItemTreeVo;
import org.jit.sose.entity.vo.AssessmentTreeVo;
import org.jit.sose.entity.vo.CourseOutlineTreeVo;
import org.jit.sose.mapper.AssessItemMapper;
import org.jit.sose.mapper.AssessmentMapper;
import org.jit.sose.mapper.CourseOutlineMapper;
import org.jit.sose.mapper.FileInfoMapper;
import org.jit.sose.service.CourseOutlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CourseOutlineServiceImpl implements CourseOutlineService {

	@Autowired
	private CourseOutlineMapper courseOutlineMapper;

	@Autowired
	private AssessItemMapper assessItemMapper;

	@Autowired
	private AssessmentMapper assessmentMapper;

	@Autowired
	private FileInfoMapper fileInfoMapper;// 文件Mapper

	@Override
	public CourseOutlineTreeVo selectCourseOutlineTree(String outlineNo, Integer courseOutlineTypeId,
			Integer courseInfoId) {
		// 查询课程大纲及子集合
		CourseOutline courseOutline = courseOutlineMapper.selectByCourseOutline(outlineNo, courseOutlineTypeId,
				courseInfoId);
		// 大纲树返回对象
		CourseOutlineTreeVo outlineTreeVo = new CourseOutlineTreeVo();
		if (courseOutline == null) {
			return outlineTreeVo;
		}

		outlineTreeVo.setId(courseOutline.getId().toString());
		outlineTreeVo.setLabel(courseOutline.getOutlineName()); // 大纲名称
		outlineTreeVo.setOutlineName(courseOutline.getOutlineName()); // 大纲名称
		outlineTreeVo.setOutlineNo(courseOutline.getOutlineNo()); // 版本号
		outlineTreeVo.setOutlineNoName(courseOutline.getOutlineNoName());// 版本号名称
		outlineTreeVo.setCourseOutlineTypeId(courseOutline.getCourseOutlineTypeId()); // 课程大纲类别标识
		outlineTreeVo.setTypeName(courseOutline.getTypeName());// 课程大纲类别名称
		outlineTreeVo.setCourseInfoId(courseOutline.getCourseInfoId()); // 课程信息标识
		outlineTreeVo.setCourseName(courseOutline.getCourseName());// 课程信息名称
		outlineTreeVo.setFileInfoId(courseOutline.getFileInfoId()); // 文件标识
		outlineTreeVo.setFileName(courseOutline.getFileName()); // 文件名称
		outlineTreeVo.setAccessUrl(courseOutline.getAccessUrl()); // accessUrl
		outlineTreeVo.setRemark(courseOutline.getRemark());
		outlineTreeVo.setTreeLevel(1);

		// 课程考核树集合
		List<AssessmentTreeVo> assessmentTreeVoList = new ArrayList<AssessmentTreeVo>();
		for (Assessment assessment : courseOutline.getAssessmentList()) {
			// 课程考核对象
			AssessmentTreeVo assessmentTreeVo = new AssessmentTreeVo();
			// id = 大纲树返回对象id + assessmentId
			assessmentTreeVo.setId(courseOutline.getId() + "-" + assessment.getId());
			assessmentTreeVo.setLabel(assessment.getAssessName() + "	" + assessment.getAssessRate() + "%");
			assessmentTreeVo.setAssessName(assessment.getAssessName()); // 考核名称
			assessmentTreeVo.setAssessRate(assessment.getAssessRate()); // 设置考核比例
			assessmentTreeVo.setCourseOutlineId(assessment.getCourseOutlineId()); // 大纲标识
			assessmentTreeVo.setTreeLevel(2);

			// 课程考核项树集合
			List<AssessItemTreeVo> assessItemTreeVoList = new ArrayList<AssessItemTreeVo>();
			for (AssessItem assessItem : assessment.getAssessItemList()) {
				// 考核项树对象
				AssessItemTreeVo assessItemTreeVo = new AssessItemTreeVo();
				assessItemTreeVo.setId(courseOutline.getId() + "-" + assessment.getId() + "-" + assessItem.getId());
				assessItemTreeVo.setLabel(assessItem.getContent() + "	" + assessItem.getMaxScore() + "分	"
						+ assessItem.getIndicatorSecContent());
				assessItemTreeVo.setContent(assessItem.getContent());// 考核名称
				assessItemTreeVo.setAssessmentId(assessItem.getAssessmentId());// 课程考核标识
				assessItemTreeVo.setIndicatorSecId(assessItem.getIndicatorSecId()); // 毕业达成度指标点(当前为二级指标)
				assessItemTreeVo.setIndicatorSecContent(assessItem.getIndicatorSecContent());// 二级指标内容
				assessItemTreeVo.setMaxScore(assessItem.getMaxScore()); // 满分
				assessItemTreeVo.setRemark(assessItem.getRemark());
				assessItemTreeVo.setTreeLevel(3);
				// 添加课程考核项树集合
				assessItemTreeVoList.add(assessItemTreeVo);
			}

			// 设置课程考核项集合为课程考核项子列表
			assessmentTreeVo.setChildren(assessItemTreeVoList);
			// 添加课程考核树集合
			assessmentTreeVoList.add(assessmentTreeVo);
		}
		// 设置课程考核集合为大纲子列表
		outlineTreeVo.setChildren(assessmentTreeVoList);
		return outlineTreeVo;
	}

	@Override
	public List<CourseOutline> listOutlineNoByEecstate() {
		return courseOutlineMapper.listOutlineNoByEecstate();
	}

	@Transactional
	@Override
	public void insert(CourseOutline record) {
		courseOutlineMapper.insert(record);
		// 将相关文件记录状态设为可用
		fileInfoMapper.updateStateById(record.getFileInfoId(), "A");
	}

	@Override
	public String checkIsExist(CourseOutline record) {
		// 先查询是否已存在记录
		List<CourseOutline> list = courseOutlineMapper.listByCourseOutline(record);
		if (list.size() > 0) {
			return "exist";
		}
		return null;
	}

	@Override
	public List<CourseOutline> listByCourseOutline(CourseOutline record) {
		return courseOutlineMapper.listByCourseOutline(record);
	}

	@Transactional
	@Override
	public void update(CourseOutline record) {
		courseOutlineMapper.update(record);
		// 将之前文件状态设为“O”
		fileInfoMapper.updateStateById(record.getFileInfoOldId(), "O");
		// 将新文件记录状态设为可用
		fileInfoMapper.updateStateById(record.getFileInfoId(), "A");
	}

	@Transactional
	@Override
	public void delete(Integer id) {
		// 删除大纲信息
		courseOutlineMapper.delete(id);
		// 删除课程考核
		assessmentMapper.deleteByCourseOutlineId(id);
		// 删除课程考核项
		assessItemMapper.deleteByCourseOutlineId(id);
	}

	@Override
	public List<CourseOutline> selectCourseOutlineList() {
		List<CourseOutline> courseOutlineList = courseOutlineMapper.selectCourseOutlineList();
		return courseOutlineList;
	}

}
