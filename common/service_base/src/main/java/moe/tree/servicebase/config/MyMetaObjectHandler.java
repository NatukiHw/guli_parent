package moe.tree.servicebase.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

	@Override
	public void insertFill(MetaObject metaObject) {
		this.strictInsertFill(metaObject, "gmtCreate", LocalDateTime.class, LocalDateTime.now());
		this.strictInsertFill(metaObject, "gmtModified", LocalDateTime.class, LocalDateTime.now());
		this.strictInsertFill(metaObject, "isDeleted", Byte.class, Byte.valueOf((byte)0));
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		this.strictUpdateFill(metaObject, "gmtModified", LocalDateTime.class, LocalDateTime.now()); // 起始版本 3.3.0(推荐)
	}
}