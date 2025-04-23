package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 出入库详情
 *
 * @author 
 * @email
 */
@TableName("tushu_churu_inout_list")
public class TushuChuruInoutListEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public TushuChuruInoutListEntity() {

	}

	public TushuChuruInoutListEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @TableField(value = "id")

    private Integer id;


    /**
     * 出入库
     */
    @TableField(value = "tushu_churu_inout_id")

    private Integer tushuChuruInoutId;


    /**
     * 图书
     */
    @TableField(value = "tushu_id")

    private Integer tushuId;


    /**
     * 操作数量
     */
    @TableField(value = "tushu_churu_inout_list_number")

    private Integer tushuChuruInoutListNumber;


    /**
     * 操作时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "create_time",fill = FieldFill.INSERT)

    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：出入库
	 */
    public Integer getTushuChuruInoutId() {
        return tushuChuruInoutId;
    }


    /**
	 * 获取：出入库
	 */

    public void setTushuChuruInoutId(Integer tushuChuruInoutId) {
        this.tushuChuruInoutId = tushuChuruInoutId;
    }
    /**
	 * 设置：图书
	 */
    public Integer getTushuId() {
        return tushuId;
    }


    /**
	 * 获取：图书
	 */

    public void setTushuId(Integer tushuId) {
        this.tushuId = tushuId;
    }
    /**
	 * 设置：操作数量
	 */
    public Integer getTushuChuruInoutListNumber() {
        return tushuChuruInoutListNumber;
    }


    /**
	 * 获取：操作数量
	 */

    public void setTushuChuruInoutListNumber(Integer tushuChuruInoutListNumber) {
        this.tushuChuruInoutListNumber = tushuChuruInoutListNumber;
    }
    /**
	 * 设置：操作时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：操作时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "TushuChuruInoutList{" +
            "id=" + id +
            ", tushuChuruInoutId=" + tushuChuruInoutId +
            ", tushuId=" + tushuId +
            ", tushuChuruInoutListNumber=" + tushuChuruInoutListNumber +
            ", insertTime=" + insertTime +
            ", createTime=" + createTime +
        "}";
    }
}
