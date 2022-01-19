package jp.csii.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import jp.csii.entity.ClassView;
import jp.csii.entity.SexEntity;
import jp.csii.entity.StudentEntity;
import jp.csii.entity.StudentPaging;
import jp.csii.entity.StudentView;
import jp.csii.service.ClassService;
import jp.csii.service.SexService;
import jp.csii.service.StudentService;

@Controller
public class StudentController {

	@Autowired
	private StudentService studentservice;
	@Autowired
	private SexService sexservice;
	@Autowired
	private ClassService classservice;

	@RequestMapping(value = "/begin", method = RequestMethod.POST)
	@ResponseBody
	public String StudentList(String pageNum) {

		Gson gson = new Gson();
		List<StudentView> listStudent = studentservice.findAll();

		// 学生データが取得できなかった場合は、null値を返す
		if (listStudent == null || listStudent.size() == 0) {
			return null;
		}

		/**
		 * ページング処理開始******************************************************************************
		 *
		 */
		// 当ページの初期値
		int page = 0;

		// 画面から当ページを取得
		String str_page = pageNum;

		StudentPaging paging = new StudentPaging();

		// データ総件数の設定
		paging.setTotal(listStudent.size());

		// ページ毎の表示件数設定
		paging.setPageSize(10);

		// 総ページ数
		paging.setTotalPage((paging.getTotal() % paging.getPageSize() == 0) ? paging.getTotal() / paging.getPageSize()
				: paging.getTotal() / paging.getPageSize() + 1);

		if (str_page != null) {

			// 将页转换整型判断其大小
			int pag = Integer.parseInt(str_page);

			// 当大于零，将传过来的pag值赋给当前页page
			if (pag >= 0) {
				page = pag;
				// 如果小于最大值时则，将其传过来的值减1在赋值给当前页，让其一直在最后一页
				if (pag > (paging.getTotalPage() - 1)) {
					page = pag - 1;
				}
			}
		}
		paging.setCurPage(page);// 最终确认当前页
		List<StudentView> list_page = new ArrayList<>();

		// 将当前页的值传给新的list_page集合中，list集合是全部数据综合，用i调用其中的几条数据给list_page
		for (int i = paging.getCurPage() * paging.getPageSize(); i < (paging.getCurPage() + 1) * paging.getPageSize()
				&& i < listStudent.size(); i++) {
			list_page.add(listStudent.get(i));
		}
		paging.setList(list_page);

		//////////////////////////////////////////
		for (int i = 1; i <= 5; i++) {
			for (int j = 1; j <=i; j++) {
				System.out.print("*");
				//System.out.println("");
			}
		}
		//////////////////////////////////////////

		return gson.toJson(paging);
	}

	@RequestMapping(value = "/del/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void StudentDelete(@PathVariable String id) {

		// @PathVariableアノテーションを指定すると、
		// URLに含まれる動的なパラメータを受け取ることができます。 value = "/del/{id}

		studentservice.deleteById(id);
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String StudentUpdate(@RequestParam(value = "id") String id, Model model) {

		// @RequestParamアノテーションのdefaultValue属性にデフォルト値を指定することで、
		// リクエストパラメータが指定されていない場合は、このデフォルト値が引数に設定されます。 value = "/update"
		List<SexEntity> listSex = sexservice.findAll();
		List<ClassView> listClass = classservice.findAll();
		List<StudentEntity> listStudent = studentservice.findById(id);

		// 学生データが取得できなかった場合は、null値を返す
		if (listStudent == null || listStudent.size() == 0) {
			return null;
		}

		// list → map の切り替え(Sex用)
		Map<Integer, String> mapSex = new HashMap<Integer, String>();
		for (SexEntity s : listSex) {
			mapSex.put(s.getId(), s.getSex());
		}

		// list → map の切り替え(Class用)
		Map<Integer, String> mapClass = new HashMap<Integer, String>();
		for (ClassView s : listClass) {
			mapClass.put(s.getId(), s.getGrade() + s.getName());
		}

		model.addAttribute("studentEntity", listStudent.get(0));
		model.addAttribute("mapSex", mapSex);
		model.addAttribute("mapClass", mapClass);
		return "update";
	}

	@RequestMapping(value = "/update2", method = RequestMethod.POST)
	@ResponseBody
	public void StudentUpdate2(@ModelAttribute StudentEntity studententity) {

		// 引数に@RequestBodyをつけてClassを指定すると、jsonデータを受け取ることができます。このように書きます。
		// ControllerがRestControllerで他のプロジェクトからRESTでClassデータを受け取ることも出来ます。

		// 引数にを@ModelAttributeつけると、HTMLのformからデータを受け取ることができます。このように書きます。
		studententity.setKOUSINNBI(new Timestamp(System.currentTimeMillis()));

		studentservice.update(studententity);
	}

	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String StudentInsert(Model model) {
		List<SexEntity> listSex = sexservice.findAll();
		List<ClassView> listClass = classservice.findAll();
		
		// list → map の切り替え(Sex用)
		Map<Integer, String> mapSex = new HashMap<Integer, String>();
		for (SexEntity s : listSex) {
			mapSex.put(s.getId(), s.getSex());
		}

		// list → map の切り替え(Class用)
		Map<Integer, String> mapClass = new HashMap<Integer, String>();
		for (ClassView s : listClass) {
			mapClass.put(s.getId(), s.getGrade() + s.getName());
		}
		
		model.addAttribute("mapSex", mapSex);
		model.addAttribute("mapClass", mapClass);
		
		return "insert";
	}

	@RequestMapping(value = "/insert2", method = RequestMethod.POST)
	public String StudentInsert2(StudentEntity studententity, Model model) {

		studententity.setTOUROKUBI(new Timestamp(System.currentTimeMillis()));
		studententity.setKOUSINNBI(new Timestamp(System.currentTimeMillis()));
		try {
			studentservice.insert(studententity);
		} catch (Exception e) {
			model.addAttribute("msg", "データ重複");
			return "insert";
		}
		return "index";
	}
}
