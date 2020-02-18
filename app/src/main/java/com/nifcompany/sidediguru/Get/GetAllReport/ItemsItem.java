package com.nifcompany.sidediguru.Get.GetAllReport;

import com.google.gson.annotations.SerializedName;

public class ItemsItem{

	@SerializedName("code_refferal")
	private String codeRefferal;

	@SerializedName("img_url")
	private String imgUrl;

	@SerializedName("author")
	private Author author;

	@SerializedName("class_id")
	private int classId;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("students")
	private int students;

	@SerializedName("class_name")
	private String className;

	@SerializedName("student_name")
	private String studentName;

	@SerializedName("NISN")
	private String nISN;

	@SerializedName("gender")
	private String gender;

	@SerializedName("report")
	private Report report;

	@SerializedName("answers")
	private Answers answers;

	@SerializedName("student_id")
	private int studentId;

	@SerializedName("age")
	private int age;

	@SerializedName("religion")
	private String religion;

	@SerializedName("question")
	private Question question;

	@SerializedName("answer_value")
	private int answerValue;

	@SerializedName("answer_id")
	private int answerId;

	public void setCodeRefferal(String codeRefferal){
		this.codeRefferal = codeRefferal;
	}

	public String getCodeRefferal(){
		return codeRefferal;
	}

	public void setImgUrl(String imgUrl){
		this.imgUrl = imgUrl;
	}

	public String getImgUrl(){
		return imgUrl;
	}

	public void setAuthor(Author author){
		this.author = author;
	}

	public Author getAuthor(){
		return author;
	}

	public void setClassId(int classId){
		this.classId = classId;
	}

	public int getClassId(){
		return classId;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setStudents(int students){
		this.students = students;
	}

	public int getStudents(){
		return students;
	}

	public void setClassName(String className){
		this.className = className;
	}

	public String getClassName(){
		return className;
	}

	public void setStudentName(String studentName){
		this.studentName = studentName;
	}

	public String getStudentName(){
		return studentName;
	}

	public void setNISN(String nISN){
		this.nISN = nISN;
	}

	public String getNISN(){
		return nISN;
	}

	public void setGender(String gender){
		this.gender = gender;
	}

	public String getGender(){
		return gender;
	}

	public void setReport(Report report){
		this.report = report;
	}

	public Report getReport(){
		return report;
	}

	public void setAnswers(Answers answers){
		this.answers = answers;
	}

	public Answers getAnswers(){
		return answers;
	}

	public void setStudentId(int studentId){
		this.studentId = studentId;
	}

	public int getStudentId(){
		return studentId;
	}

	public void setAge(int age){
		this.age = age;
	}

	public int getAge(){
		return age;
	}

	public void setReligion(String religion){
		this.religion = religion;
	}

	public String getReligion(){
		return religion;
	}

	public void setQuestion(Question question){
		this.question = question;
	}

	public Question getQuestion(){
		return question;
	}

	public void setAnswerValue(int answerValue){
		this.answerValue = answerValue;
	}

	public int getAnswerValue(){
		return answerValue;
	}

	public void setAnswerId(int answerId){
		this.answerId = answerId;
	}

	public int getAnswerId(){
		return answerId;
	}

	@Override
 	public String toString(){
		return 
			"ItemsItem{" + 
			"code_refferal = '" + codeRefferal + '\'' + 
			",img_url = '" + imgUrl + '\'' + 
			",author = '" + author + '\'' + 
			",class_id = '" + classId + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",students = '" + students + '\'' + 
			",class_name = '" + className + '\'' + 
			",student_name = '" + studentName + '\'' + 
			",nISN = '" + nISN + '\'' + 
			",gender = '" + gender + '\'' + 
			",report = '" + report + '\'' + 
			",answers = '" + answers + '\'' + 
			",student_id = '" + studentId + '\'' + 
			",age = '" + age + '\'' + 
			",religion = '" + religion + '\'' + 
			",question = '" + question + '\'' + 
			",answer_value = '" + answerValue + '\'' + 
			",answer_id = '" + answerId + '\'' + 
			"}";
		}
}