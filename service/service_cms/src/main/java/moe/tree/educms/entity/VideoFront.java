package moe.tree.educms.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class VideoFront implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	private String title;
	private Byte isFree;
}
