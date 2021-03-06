package edu.muniz.askalien.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="answer")
public class Answer implements Serializable,Model{

	private static final long serialVersionUID = -7271715793384643421L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("number")
	private Integer id;

	@Column(columnDefinition="TEXT")
	@JsonProperty("question")
	private String subject;

	@Basic(fetch=FetchType.LAZY)
	@Column(columnDefinition="TEXT")
	private String content;
	
	@Column(length=200)
	@JsonProperty("link")
	private String url;
	
	@ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="videoNumber")
	private Video video;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	@Transient
	private Integer questionId;

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public String getDate() {
		String date = "";
		if(video!=null){
			SimpleDateFormat dt1 = new SimpleDateFormat("MM/dd/yyyy");
			date = dt1.format(video.getCreationDate());
		}	
		return date;
	}

	
	@Transient
	private Long clicks;
		
	public Long getClicks() {
		return clicks;
	}

	public void setClicks(Long clicks) {
		this.clicks = clicks;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public void populate(Model sourceObject) {
		Answer newObject = (Answer)sourceObject;
		this.content = newObject.getContent();
		this.subject = newObject.getSubject();
		this.video = newObject.getVideo();
		this.url = newObject.getUrl();
	}
	
	public Answer() {
		this.video = new Video();
	}
	
	public Answer(Answer answer) {
		this.content = answer.getContent();
		this.subject = answer.getSubject();
		this.id = answer.getId();
		this.video = answer.getVideo();
		this.url = answer.getUrl();
	}
	
	public Answer(Integer id,String subject,Long clicks){
		this.id = id;
		this.subject = subject;
		this.clicks = clicks;
	}
	
	public Answer(Integer id,String subject){
		this.id = id;
		this.subject = subject;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}


	

}