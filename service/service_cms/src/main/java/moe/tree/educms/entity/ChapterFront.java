package moe.tree.educms.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ChapterFront implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	private String title;
	private List<VideoFront> children;
}
